<template>
  <div>
    <el-upload
      class="upload-demo"
      ref="upload"
      action=""
      :http-request="upload"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      multiple
      :limit="10"
      :on-exceed="handleExceed"
      :file-list="uploadList">
      <el-button slot="trigger" size="small" type="primary">点击上传</el-button>
      <el-button size="small" type="primary" @click="getVideoList">刷新</el-button>
      <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
    </el-upload>
    <el-table
      v-loading="loading"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :data="fileList.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
      height="700"
      border
      style="width: 100%">
      <el-table-column
        v-for="(item, index) in frameInfoLabel"
        :key="index"
        :prop="item.prop"
        :width="item.width"
        :label="item.label">
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="downloadVideo(scope.row.name)"
            type="text"
            size="small">
            下载
          </el-button>
          <el-button
            @click.native.prevent="deleteVideo(scope.row.name)"
            type="text"
            size="small">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tableTotal">
    </el-pagination>
  </div>
</template>

<script>
import {listDir, put, deleteV, get} from '@/utils/oss.js'

export default {
  name: 'Video',
  data () {
    return {
      uploadList: [],
      fileList: [],
      currentPage: 1,
      tableTotal: 0,
      pageSize: 10,
      loading: false,
      frameInfoLabel: [
        { label: '名称', width: '', prop: 'name' },
        { label: '文件大小', width: '', prop: 'size' },
        { label: '存储类型', width: '', prop: 'storageClass' },
        { label: '实体标签', width: '', prop: 'etag' },
        { label: '最后修改时间', width: '', prop: 'lastModified' }

      ],
      downloadLoading: false
    }
  },
  created () {
    this.getVideoList()
  },
  methods: {
    async upload (option) {
      console.log(option)
      put(option)
      this.getVideoList()
    },
    handleExceed (files, fileList) {
      this.$message.warning(`当前限制选择 10 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    handleRemove (file, uploadList) {
      console.log(file, uploadList)
    },
    handlePreview (file) {
      console.log(file)
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      console.log(this.pageSize)
    },
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
      this.currentPage = val
    },
    getVideoList () {
      this.fileList = []
      let fileList = Promise.resolve(listDir('video/'))
      fileList.then((result) => {
        result.forEach(element => {
          this.fileList.push(element)
        })
        this.tableTotal = this.fileList.length
      })
    },
    downloadVideo (name) {
      let result = Promise.resolve(get(name))
      result.then((ele) => {
        window.open(ele)
      })
    },
    deleteVideo (name) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log(name)
        let result = Promise.resolve(deleteV(name))
        result.then((ele) => {
          console.log(ele)
          console.log(ele.res.status)
          if (ele.res.status === 204) {
            this.$message({
              message: '删除成功！',
              type: 'success'
            })
            this.getVideoList()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<template>
  <div>
    <el-button type="primary" @click="getAllFrameInfo" style="width: 180px;">获取Frame数据</el-button>
    <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
      导出
    </el-button>
    <el-table
      v-loading="loading"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
      height="700"
      border
      style="width: 100%">
      <el-table-column
        v-for="(item, index) in tableLabel"
        :key="index"
        :prop="item.prop"
        :width="item.width"
        :label="item.label">
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[100, 200, 300, 400]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tableTotal">
    </el-pagination>
  </div>
</template>

<script>
// import axios from 'axios'
// import qs from 'qs'
import { parseTime } from '@/utils'

export default {
  name: 'FrameInfo',
  data () {
    return {
      tableData: [],
      // tempData: [],
      tableLabel: [],
      currentPage: 1,
      tableTotal: 0,
      pageSize: 100,
      loading: false,
      frameInfoLabel: [
        { label: 'id', width: '', prop: 'id' },
        { label: '路由器地址', width: '', prop: 'apMac' },
        { label: '移动终端地址', width: '', prop: 'staMac' },
        { label: '信号强度', width: '', prop: 'signaldBm' },
        { label: '距离', width: '', prop: 'distance' },
        { label: '时间', width: '', prop: 'timeStamp' },
      ],
      names: 'frameInfo',
      tableName: '',
      downloadLoading: false
    }
  },
  created () {
  },
  methods: {
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      console.log(this.pageSize)
    },
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
      this.currentPage = val
      // this.currentChangePage(this.tableDataBegin);
    },
    getAllFrameInfo () {
      // 获取所有frameInfo
      this.loading = true
      this.pageSize = 100
      this.currentPage = 1
      this.tableTotal = 0
      console.log(this.CONFIG.wifiURL)
      // axios.get(('http://www.cugcdc.cn:7777/FrameInfo/getAllFrameInfo'), qs.stringify({
      //   headers: {'Content-Type': 'application/x-www-form-urlencoded'}
      // }))
      this.$wifi.get((this.CONFIG.wifiURL + '/FrameInfo/getAllFrameInfo'), {
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
      })
        .then((response) => {
          // 判断是否登录成功
          this.loading = false
          console.log(response.data.length)
          this.tableTotal = response.data.length
          this.tableLabel = this.frameInfoLabel
          this.tableName = this.names[0]
          // this.tempData = response.data
          this.tableData = response.data
          // this.currentChangePage(response.data)
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          console.log(error)
          this.$message({
            message: '网络错误！',
            type: 'error'
          })
        })
    },
    handleDownload () {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        let tHeader = []
        let filterVal = []
        let filename = ''
        console.log(this.tableName)
        tHeader = ['id', '路由器地址', '移动终端地址', '信号强度', '距离', '时间']
        filterVal = ['id', 'apMac', 'staMac', 'signaldBm', 'distance', 'timeStamp']
        filename = 'FrameInfoData'
        const data = this.formatJson(filterVal, this.tableData)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: filename
        })
        this.downloadLoading = false
      })
    },
    formatJson (filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
    // currentChangePage (list) {
    //   let from = (this.currentPage - 1) * this.pageSize
    //   let to = this.currentPage * this.pageSize
    //   this.tableData = []
    //   for (; from < to; from++) {
    //     if (list[from]) {
    //       this.tableData.push(list[from])
    //     }
    //   }
    // }
  }
}
</script>

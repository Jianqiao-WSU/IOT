<template>
  <div>
    <el-button type="primary" @click="getAllBoundaryInfo" style="width: 180px;">获取室内地图边界数据</el-button>
    <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
      导出
    </el-button>
    <el-table
      v-loading="loading"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
      height="750"
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
  name: 'Wifi',
  data () {
    return {
      tableData: [],
      // tempData: [],
      tableLabel: [],
      currentPage: 1,
      tableTotal: 0,
      pageSize: 100,
      loading: false,
      boundaryInfoLabel: [
        { label: 'id', width: '', prop: 'id' },
        { label: 'x', width: '', prop: 'x' },
        { label: 'y', width: '', prop: 'y' }
      ],
      names: ['frameInfo', 'boundaryInfo', 'wallsInfo'],
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
    getAllBoundaryInfo () {
      this.loading = true
      this.pageSize = 100
      this.currentPage = 1
      this.tableTotal = 0
      // 获取所有室内地图边界地图数据
      this.$wifi.get(this.CONFIG.wifiURL + '/BoundaryInfo/getAll')
        .then((response) => {
          // 判断是否登录成功
          this.loading = false
          console.log(response)
          this.tableTotal = response.data.length
          this.tableLabel = this.boundaryInfoLabel
          this.tableName = this.names[1]
          this.tableData = response.data
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          console.log(error)
        })
    },
    handleDownload () {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        let tHeader = []
        let filterVal = []
        let filename = ''
        console.log(this.tableName)
        tHeader = ['id', 'x', 'y']
        filterVal = ['id', 'x', 'y']
        filename = 'BoundaryInfoData'
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
  }
}
</script>

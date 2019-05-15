<template>
  <div>
    <el-button type="primary" @click="getAllBoundaryInfo" style="width: 180px;">获取蓝牙数据</el-button>
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
  name: 'Bluetooth',
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
        { label: 'id', width: '', prop: 'bt_id' },
        { label: 'x坐标', width: '', prop: 'x' },
        { label: 'y坐标', width: '', prop: 'y' },
        { label: 'beacon1设备码', width: '', prop: 'beacon1' },
        { label: 'beacon1信号强度', width: '', prop: 'rssi1' },
        { label: 'beacon2设备码', width: '', prop: 'beacon2' },
        { label: 'beacon2信号强度', width: '', prop: 'rssi2' },
        { label: 'beacon3设备码', width: '', prop: 'beacon3' },
        { label: 'beacon3信号强度', width: '', prop: 'rssi3' }
      ],
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
      this.$http.get(this.CONFIG.baseURL + '/api/bluetooth')
        .then((response) => {
          this.loading = false
          console.log(response.data)
          this.tableTotal = response.data.length
          this.tableLabel = this.boundaryInfoLabel
          response.data.forEach(function (element) {
            element.beacon1 = element.beacon1.padStart(11, '0')
            element.beacon2 = element.beacon2.padStart(11, '0')
            element.beacon3 = element.beacon3.padStart(11, '0')
          })
          console.log(response.data)
          this.tableData = response.data
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
        tHeader = ['id', 'x坐标', 'y坐标', 'beacon1设备码', 'beacon1信号强度', 'beacon2设备码', 'beacon2信号强度', 'beacon3设备码', 'beacon3信号强度']
        filterVal = ['bt_id', 'x', 'y', 'beacon1', 'rssi1', 'beacon2', 'rssi2', 'beacon3', 'rssi3']
        filename = 'BluetoothData'
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

<style scoped>
.el-pagination {
  left: 50px;
}
</style>

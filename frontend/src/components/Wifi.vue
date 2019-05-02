<template>
  <div>
    <el-button type="primary" @click="getAllFrameInfo" style="width: 250px;">frameInfo</el-button>
    <el-button type="primary" @click="getAllBoundaryInfo" style="width: 250px;">获取所有室内地图边界地图数据</el-button>
    <el-button type="primary" @click="getAllWallsInfo" style="width: 250px;">获取所有室内墙壁数据</el-button>
    <el-table
      v-loading="loading"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
      height="550"
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
      frameInfoLabel: [
        { label: 'id', width: '', prop: 'id' },
        { label: 'apMac', width: '', prop: 'apMac' },
        { label: 'timeStamp', width: '', prop: 'timeStamp' },
        { label: 'staMac', width: '', prop: 'staMac' },
        { label: 'signaldBm', width: '', prop: 'signaldBm' },
        { label: 'distance', width: '', prop: 'distance' }
      ],
      boundaryInfoLabel: [
        { label: 'id', width: '', prop: 'id' },
        { label: 'x', width: '', prop: 'x' },
        { label: 'y', width: '', prop: 'y' }
      ],
      wallsInfoLabel: [
        { label: 'id', width: '', prop: 'id' },
        { label: 'x1', width: '', prop: 'x1' },
        { label: 'y1', width: '', prop: 'y1' },
        { label: 'x2', width: '', prop: 'x2' },
        { label: 'y2', width: '', prop: 'y2' }
      ]
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
      this.$wifi.get('/wifi/FrameInfo/getAllFrameInfo')
        .then((response) => {
          // 判断是否登录成功
          this.loading = false
          console.log(response.data.length)
          this.tableTotal = response.data.length
          this.tableLabel = this.frameInfoLabel
          // this.tempData = response.data
          this.tableData = response.data
          // this.currentChangePage(response.data)
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          console.log(error)
        })
    },
    getAllBoundaryInfo () {
      this.loading = true
      this.pageSize = 100
      this.currentPage = 1
      this.tableTotal = 0
      // 获取所有室内地图边界地图数据
      this.$wifi.get('/wifi/BoundaryInfo/getAll')
        .then((response) => {
          // 判断是否登录成功
          this.loading = false
          console.log(response)
          this.tableTotal = response.data.length
          this.tableLabel = this.boundaryInfoLabel
          this.tableData = response.data
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          console.log(error)
        })
    },
    getAllWallsInfo () {
      this.loading = true
      this.pageSize = 100
      this.currentPage = 1
      this.tableTotal = 0
      // 获取所有室内墙壁数据
      this.$wifi.get('/wifi/WallsInfo/getAll')
        .then((response) => {
          // 判断是否登录成功
          this.loading = false
          console.log(response)
          this.tableTotal = response.data.length
          this.tableLabel = this.wallsInfoLabel
          this.tableData = response.data
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          console.log(error)
        })
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

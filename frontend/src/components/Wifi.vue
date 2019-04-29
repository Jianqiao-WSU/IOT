<template>
  <div>
    <el-button type="primary" @click="getAllFrameInfo" style="width: 250px;">frameInfo</el-button>
    <el-button type="primary" @click="getAllBoundaryInfo" style="width: 250px;">获取所有室内地图边界地图数据</el-button>
    <el-button type="primary" @click="getAllWallsInfo" style="width: 250px;">获取所有室内墙壁数据</el-button>
    <el-table
      :data="tableData"
      height="250"
      border
      style="width: 100%">
      <el-table-column
        prop="date"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
// import axios from 'axios'
// import qs from 'qs'

export default {
  name: 'Wifi',
  data () {
    return {
      tableData: [{
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-08',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-06',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-07',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }]
    }
  },
  created () {
  },
  methods: {
    getAllFrameInfo () {
      // 获取所有frameInfo
      this.wifi.get('/FrameInfo/getAllFrameInfo')
        .then((response) => {
          // 判断是否登录成功
          console.log(response)
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          console.log(error)
        })
    },
    getAllBoundaryInfo () {
      // 获取所有室内地图边界地图数据
      this.wifi.get('/BoundaryInfo/getAll')
        .then((response) => {
          // 判断是否登录成功
          console.log(response)
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          console.log(error)
        })
    },
    getAllWallsInfo () {
      // 获取所有室内墙壁数据
      this.wifi.get('/WallsInfo/getAll')
        .then((response) => {
          // 判断是否登录成功
          console.log(response)
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          console.log(error)
        })
    }
  }
}
</script>

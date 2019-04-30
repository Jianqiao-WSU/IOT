<template>
  <div>
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1">主页</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3">WIFI</el-menu-item>
      <el-submenu index="4">
        <template slot="title">{{msg}}，欢迎你！</template>
        <!-- <el-menu-item index="4-1">个人信息</el-menu-item> -->
        <el-menu-item index="4-2">设置</el-menu-item>
        <el-menu-item index="4-3">退出</el-menu-item>
      </el-submenu>
    </el-menu>
    <!-- <div v-show="homeShow">
      <Home/>
    </div> -->
    <component :is="comName"></component>
  </div>
</template>

<script>
import Home from '@/components/Home'
import Wifi from '@/components/Wifi'
import Settings from '@/components/Settings'

export default {
  name: 'Main',
  components: { Home, Wifi, Settings },
  data () {
    return {
      msg: localStorage.getItem('user'),
      activeIndex: '1',
      activeIndex2: '1',
      homeShow: true,
      comName: 'Home'
    }
  },
  created () {
    // if (!localStorage.getItem('token')) {
    //   this.$router.push({
    //     name: 'Login'
    //   })
    // }
  },
  methods: {
    handleSelect (key, keyPath) {
      // console.log(key, keyPath)
      if (key === '1') {
        // this.homeShow = true
        this.comName = 'Home'
      }
      if (key === '3') {
        // this.homeShow = false
        this.comName = 'Wifi'
      }
      if (key === '4-2') {
        this.comName = 'Settings'
      }
      if (key === '4-3') {
        this.signOut()
      }
    },
    signOut () {
      console.log('123')
      this.$message({
        message: '退出成功！',
        type: 'success'
      })
      // this.$store.commit('setToken', null)
      // this.$store.commit('setUser', null)
      window.localStorage['token'] = ''
      window.localStorage['user'] = ''
      this.$router.push({
        name: 'Login'
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>

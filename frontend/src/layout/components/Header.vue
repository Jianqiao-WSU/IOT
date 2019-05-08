<template>
  <el-row>
    <el-col :span="2" class="header-wrap">
      <el-button circle class="no-b" :icon="iconName" @click="toggleMenuOpen"></el-button>
    </el-col>
    <el-col :span="2" :offset="20" class="header-wrap text-r">
      <el-dropdown @command="handleCommand">
        <el-button class="no-b">
          {{user}} <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="a">
            <icon name="user"/>
            查看个人信息</el-dropdown-item>
          <el-dropdown-item command="b">
            <icon name="sign-out-alt"/>
            登出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-col>
  </el-row>
</template>

<script>
import {
  mapState
} from 'vuex'
export default {
  data () {
    return {
      user: localStorage.getItem('user'),
      activeIndex: '1',
      activeIndex2: '1'
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    toggleMenuOpen () {
      this.$store.commit('toggleMenuOpen')
    },
    handleCommand (command) {
      console.log('click on item ' + command)
      if (command === 'b') {
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
  },
  computed: {
    ...mapState(['isNavMenuOpen']),
    iconName () {
      return this.isNavMenuOpen ? 'el-icon-s-fold' : 'el-icon-s-unfold'
    }
  }
}
</script>

<style scoped>
  .no-b{
    border: none!important
  }
  .text-r {
    text-align: right
  }
  .el-icon-s-fold {
    width: 40px;
    height: 40px;
  }
  .menuOpen {
    background-color: white;
    width: 40px;
    height: 40px;
  }
  .head-wrap {
    height: 40px;
    border-bottom: 1px solid #eee;
  }
</style>

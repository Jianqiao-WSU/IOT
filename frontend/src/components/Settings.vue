<template>
  <div>
    <!-- {{msg}} -->
    <el-row class="tac">
      <el-col :span="3">
        <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
          @select="handleSelect">
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-setting"></i>
            <span slot="title">安全</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="12">
        <component :is="comName"></component>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Account from '@/components/Account'
import Profile from '@/components/Profile'

export default {
  name: 'Settings',
  components: { Account, Profile },
  data () {
    return {
      comName: 'Profile'
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      // console.log(key, keyPath)
      if (key === '1') {
        // this.homeShow = true
        this.comName = 'Profile'
        // console.log(this.comName)
      }
      if (key === '2') {
        // this.homeShow = true
        this.comName = 'Account'
        // console.log(this.comName)
      }
    }
  }
}
</script>

<template>
  <el-row class="tac">
  <el-col :span="24" class="h100">
    <!-- <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
      <el-radio-button :label="false">展开</el-radio-button>
      <el-radio-button :label="true">收起</el-radio-button>
    </el-radio-group> -->
    <el-menu
      class="no-boarder el-menu-vertical-demo h100"
      router
      unique-opened
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      :default-active="activeTag"
      ref="mySidemenu"
      :collapse="!isNavMenuOpen"
      active-text-color="#ffd04b">
      <el-menu-item index="/home" class="no-boarder">
        <icon name="home"/>
        <span slot="title">&nbsp;首页</span>
      </el-menu-item>
      <el-menu-item index="/video" class="no-boarder">
        <icon name="video"/>
        <span slot="title">&nbsp;摄像头数据</span>
      </el-menu-item>
      <el-menu-item index="/bluetooth" class="no-boarder">
        <icon name="tooth"/>
        <span slot="title">&nbsp;蓝牙数据</span>
      </el-menu-item>
      <el-menu-item index="/environment" class="no-boarder">
        <icon name="cloud"/>
        <span slot="title">&nbsp;环境传感数据</span>
      </el-menu-item>
      <el-submenu v-for="item in menu" :index="item.name" :key="item.name" class="no-boarder">
        <template slot="title">
          <icon :name="item.meta.icon"/>&nbsp;
          <span v-text="item.meta.title"></span>
        </template>
        <el-menu-item-group class="over-hide" v-for="sub in item.sub" :key="sub.name">
          <el-menu-item :index="sub.meta.index" v-text="sub.meta.title">
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-col>
</el-row>
</template>

<style scoped>
  .h100{
    height: 100%
  }
  .tac{
    position: fixed;
    top: 0px;
    bottom: 0px;
    z-index: 999;
  }
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>

<script>
import { mapGetters, mapState } from 'vuex'
import menu from '@/config/menu-config'

export default {
  data () {
    return {
      menu: menu,
      isCollapse: true
    }
  },
  computed: {
    ...mapGetters(['activeTag']),
    ...mapState(['isNavMenuOpen'])
  },
  watch: {
    activeTag: function (newValue, oldValue) {
      this.$nextTick(() => {
        this.$refs.mySidemenu.activeIndex = this.activeTag
      })
    }
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

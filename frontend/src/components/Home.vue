<template>
  <div>
    {{msg}}
    <el-button type="primary" @click="main" style="width:100%;margin-bottom:30px;">登录</el-button>
  </div>
</template>

<script>

export default {
  name: 'Home',
  data () {
    return {
      msg: 'Home'
    }
  },
  methods: {
    main () {
      this.$router.push({
        name: 'Register'
      })
    }
  }
}
</script>

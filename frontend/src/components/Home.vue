<template>
  <div>
    <h1 align="center">{{msg}}</h1>
  </div>
</template>

<script>

export default {
  name: 'Home',
  data () {
    return {
      msg: '欢迎使用面向室内智能空间感知的物联网数据服务平台'
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

<style>

</style>

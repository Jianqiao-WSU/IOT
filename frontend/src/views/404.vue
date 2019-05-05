<template>
  <div class="pic-404">
    <img class="pic-404__parent" src="@/assets/404.png" alt="404">
  </div>
</template>

<script>
export default {
  name: 'Page404',
  data () {
    return {
      msg: 'The webmaster said that you can not enter this page...'
    }
  },
  created () {
    this.goGrdoupRecor()
  },
  methods: {
    goGrdoupRecor () {
      const TIME_COUNT = 3
      if (!this.timer) {
        this.count = TIME_COUNT
        this.show = false
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= TIME_COUNT) {
            this.count--
          } else {
            this.show = true
            clearInterval(this.timer)
            this.timer = null
            // 跳转的页面写在此处
            this.$router.push({
              name: 'Login'
            })
          }
        }, 1000)
      }
    }
  }
}
</script>

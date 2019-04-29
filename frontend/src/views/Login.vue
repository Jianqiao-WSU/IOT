<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" style="width: 250px;">
      <el-form-item prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')" style="width: 250px;">登录</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="register()" style="width: 250px;">现在注册</el-button>
  </div>
</template>

<script>
// import axios from 'axios'
// import qs from 'qs'

export default {
  name: 'Login',
  data () {
    return {
      form: {
        username: 'admin',
        password: '1234'
      },
      rules: {
        username: [
          { message: '请输入用户名' },
          { min: 4, max: 11, message: '长度在 4 到 11 个字符' }
        ],
        password: [
          { message: '请输入密码' },
          { min: 4, max: 50, message: '长度在 4 到 20 个字符' }
        ]
      }
    }
  },
  created () {
    if (localStorage.getItem('token')) {
      this.$router.push({
        name: 'Main'
      })
    }
  },
  methods: {
    onSubmit (form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          // alert('submit!')
          console.log(this.form)
          this.$http.post('/api/user/login', {
            username: this.form.username,
            password: this.form.password
          })
            .then((response) => {
              // 判断是否登录成功
              console.log(response.data.msg)
              if (response.data.msg === 'ok') {
                this.$message({
                  message: '登录成功！',
                  type: 'success'
                })
                // this.$store.commit('setToken', response.data.token)
                // this.$store.commit('setUser', response.data.username)
                window.localStorage['token'] = JSON.stringify(response.data.token)
                window.localStorage['user'] = response.data.username
                this.$router.push({
                  name: 'Main'
                })
              } else {
                this.$message.error(response.data.msg)
              }
              console.log(response)
            })
            .catch((error) => {
              // 请求失败页面弹出失败框
              console.log(error)
              if (error) {
                this.$message.error('登录出错!!')
              }
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    register () {
      this.$router.push({
        name: 'Register'
      })
    }
  }
}
</script>

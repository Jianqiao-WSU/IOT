<template>
  <div>
    <el-form ref="form" :model="registerForm" :rules="rules" style="width: 250px;">
      <el-form-item prop="username">
        <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item prop="passwordConf">
        <el-input type="password" v-model="registerForm.passwordConf" placeholder="请再次输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')" style="width: 250px;">注册</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="goBack" style="width: 250px;">返回</el-button>
  </div>
</template>

<script>
// import axios from 'axios'
// import qs from 'qs'

export default {
  name: 'Register',
  data () {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.registerForm.passwordConf !== '') {
          // console.log(this.$refs)
          this.$refs.form.validateField('passwordConf')
        }
        callback()
      }
    }
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      registerForm: {
        username: 'admin',
        password: '1234',
        passwordConf: '1234'
      },
      rules: {
        username: [
          { message: '请输入用户名' },
          { min: 4, max: 11, message: '长度在 4 到 11 个字符' }
        ],
        password: [
          // { message: '请输入密码' },
          { min: 4, max: 20, message: '长度在 4 到 20 个字符' },
          { validator: validatePass, trigger: 'blur' }
        ],
        passwordConf: [
          // { message: '请再次输入密码' },
          { min: 4, max: 20, message: '长度在 4 到 20 个字符' },
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onSubmit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!')
          console.log(JSON.stringify(this.registerForm))
          // this.registerForm.username = this.form.username
          // this.registerForm.password = this.form.password
          this.$http.post('/api/user/register', {
            body: (JSON.stringify(this.registerForm))
          })
            .then((response) => {
              // 判断是否登录成功
              console.log(response)
              if (response.data.msg === 'ok') {
                this.$message({
                  message: '注册成功，请登录！',
                  type: 'success'
                })
                // this.$store.commit('setToken', response.data.token)
                // this.$store.commit('setUser', response.data.username)
                this.$router.push({
                  name: 'Login'
                })
              } else {
                this.$message.error(response.data.msg)
              }
              console.log(response)
            })
            .catch((error) => {
              // 请求失败页面弹出失败框
              console.log(error.response)
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
    goBack () {
      this.$router.push({
        name: 'Login'
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>

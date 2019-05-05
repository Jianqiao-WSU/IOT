<template>
  <div>
    <el-form ref="form" :model="resetPSWForm" :rules="rules" style="width: 250px;">
      <el-form-item prop="password">
        <el-input type="password" v-model="resetPSWForm.password" placeholder="请输入原密码"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="resetPSWForm.resetPSW" placeholder="请输入新密码"></el-input>
      </el-form-item>
      <el-form-item prop="passwordConf">
        <el-input type="password" v-model="resetPSWForm.passwordConf" placeholder="请再次输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')" style="width: 250px;">更改密码</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// import axios from 'axios'
// import qs from 'qs'

export default {
  name: 'Account',
  data () {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入新密码'))
      } else {
        if (this.resetPSWForm.passwordConf !== '') {
          // console.log(this.$refs)
          this.$refs.form.validateField('passwordConf')
        }
        callback()
      }
    }
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.resetPSWForm.resetPSW) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      resetPSWForm: {
        username: '',
        password: '',
        resetPSW: '',
        passwordConf: ''
      },
      rules: {
        password: [
          { message: '请输入原密码' },
          { min: 4, max: 20, message: '长度在 4 到 20 个字符' }
        ],
        resetPSW: [
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
      if (localStorage.getItem('token')) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.resetPSWForm.username = localStorage.getItem('user')
            this.$http.post((this.CONFIG.baseURL + '/api/user/resetPsw'), {
              body: (JSON.stringify(this.resetPSWForm))
            })
              .then((response) => {
                // 判断是否登录成功
                console.log(response)
                if (response.data.msg === 'ok') {
                  this.$message({
                    message: '密码重设成功！',
                    type: 'success'
                  })
                  // this.$store.commit('setToken', response.data.token)
                  // this.$store.commit('setUser', response.data.uid)
                  // this.$router.push({
                  //   name: 'Main'
                  // })
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
      }
    }
  }
}
</script>

<template>
  <div>
    <el-form ref="form" :model="deleteAccForm" :rules="rules" style="width: 250px;">
      <el-form-item prop="password">
        <el-input type="password" v-model="deleteAccForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="danger" @click="onSubmit('form')" style="width: 250px;">删除用户</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// import axios from 'axios'
// import qs from 'qs'

export default {
  name: 'DeleteAcc',
  data () {
    return {
      deleteAccForm: {
        username: '',
        password: ''
      },
      rules: {
        password: [
          { message: '请输入密码' },
          { min: 4, max: 20, message: '长度在 4 到 20 个字符' }
        ]
      }
    }
  },
  methods: {
    onSubmit (formName) {
      if (localStorage.getItem('token')) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$confirm('此操作将永久删除该账号, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning',
              center: true
            }).then(() => {
              this.deleteAccForm.username = localStorage.getItem('user')
              this.$http.post((this.CONFIG.baseURL + '/api/user/deleteAcc'), {
                body: (JSON.stringify(this.deleteAccForm))
              })
                .then((response) => {
                  // 判断是否登录成功
                  console.log(response)
                  if (response.data.msg === 'ok') {
                    this.$message({
                      message: '账号删除成功！',
                      type: 'success'
                    })
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
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              })
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

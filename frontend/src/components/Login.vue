<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="用户名：">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码：">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
import qs from 'qs'

export default {
  name: 'Login',
  data () {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    onSubmit () {
      console.log('submit!')
      axios.post('http://localhost:8080/api/login', qs.stringify({
        username: this.form.username,
        password: this.form.password
      }))
        .then((response) => {
          // 判断是否登录成功
          console.log(response)
        })
        .catch((error) => {
          // 请求失败页面弹出失败框
          // console.log(error.response);
          if (error) {
            this.$message.error('error!!')
          }
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

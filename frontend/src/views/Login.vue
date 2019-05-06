<template>
  <div class="login-container">
    <span class="svg-container" @click="goHome">
      <svg class="logo" aria-hidden="true">
        <use xlink:href="#icon-IOT"></use>
      </svg>
    </span>
    <el-form ref="form" :model="form" :rules="rules" class="login-form" auto-complete="on" label-position="left">
      <div class="title-container">
        <h3 class="title">
          用户登录
        </h3>
      </div>
      <el-form-item prop="username">
        <span class="svg-container">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-user"></use>
          </svg>
        </span>
        <el-input v-model="form.username" placeholder="请输入账号" @keyup.enter.native="onSubmit('form')"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-password"></use>
          </svg>
        </span>
        <el-input type="password" v-model="form.password" placeholder="请输入密码" show-password @keyup.enter.native="onSubmit('form')"></el-input>
      </el-form-item>
      <el-button type="primary" @click="onSubmit('form')" style="width:100%;margin-bottom:30px;">登录</el-button>
      <el-button type="primary" @click="register()" style="width:100%;margin-bottom:30px;margin-left:0px;">现在注册</el-button>
    </el-form>
    <remote-js src="http://at.alicdn.com/t/font_1171828_4imltrlgz6g.js"></remote-js>
  </div>
</template>

<script>
// import axios from 'axios'
// import qs from 'qs'
// import SvgIcon from '@/components/SvgIcon'

export default {
  name: 'Login',
  // components: { SvgIcon }
  components: {
    'remote-js': {
      render (createElement) {
        return createElement('script', {attrs: {type: 'text/javascript', src: this.src}})
      },
      props: {
        src: { type: String, required: true }
      }
    }
  },
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
    // if (localStorage.getItem('token')) {
    //   this.$router.push({
    //     name: 'Main'
    //   })
    // }
  },
  methods: {
    onSubmit (form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          // alert('submit!')
          console.log(this.$http)
          this.$http.post((this.CONFIG.baseURL + '/api/user/login'), {
          // this.$http.post('/api/user/login', {
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
    },
    goHome () {
      this.$router.push({
        name: 'Login'
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
body {
  margin: 0px;
}
.icon {
  width: 1.2em; height: 1.2em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
.logo {
  position: absolute;
  left: 5px;
  width: 5em; height: 5em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
  cursor: pointer;//鼠标变小手
}
$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 89%;
    font-size: 17px !important;
    background-color: transparent;
    input {
      background: transparent !important;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;
      font-size: 17px;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 450px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 10px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>

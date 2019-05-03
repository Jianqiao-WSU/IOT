// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import store from './store/store'
import axios from 'axios'
import VueResource from 'vue-resource'
import 'normalize.css/normalize.css' // a modern alternative to CSS resets
import '@/styles/index.scss' // global css
import './styles/element-variables.scss'
// import './icons/iconfont.js'

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(VueResource)
Vue.http.options.emulateJSON = true

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

Vue.prototype.$wifi = axios
// Vue.prototype.$axios = axios
// axios.defaults.baseURL = 'http://www.cugcdc.cn:7777/'
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

// axios.defaults.headers.post['Content-type'] = 'application/json'
// Vue.prototype.$axios = axios

const http = axios.create()
// http request 拦截器
// 每次请求都为http 头增加Authorization字段，其内容为Token
http.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.common['Authorization'] = token
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// // http response 拦截器
// http.interceptors.response.use(response => {
//   let data = response.data
//   if (data.code === 200) {
//     return data.data
//   }
//   if (data.code === 401) {
//     window.location.href = '/login'
//   }
//   return Promise.reject(data)
// }, error => {
//   return Promise.reject(error)
// })

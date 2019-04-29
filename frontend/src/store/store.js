import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: ''
  },
  getters: {

  },
  mutations: {
    // 获取 token
    setToken (state, token) {
      state.token = token
    },
    setUser (state, user) {
      state.user = user
    }
  },
  actions: {

  }
})

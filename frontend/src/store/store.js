import Vue from 'vue'
import Vuex from 'vuex'

import { findIndex } from '../utils'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    navTags: [],
    isNavMenuOpen: true
  },
  getters: {
    activeTag (state) {
      let tag = state.navTags.find((item) => {
        return item.active === true
      })
      return tag ? tag.index : ''
    },
    navTagIndexes (state) {
      return state.navTags.map((item) => {
        return item.index
      })
    }
  },
  mutations: {
    // 获取 token
    setToken (state, token) {
      state.token = token
    },
    setUser (state, user) {
      state.user = user
    },
    toggleMenuOpen (state, payload) {
      state.isNavMenuOpen = !state.isNavMenuOpen
    },
    addNavTags (state, view) {
      let index = findIndex(state.navTags, 'index', view.meta.index)

      state.navTags.forEach((item) => {
        item.active = false
      })

      if (index === -1) {
        state.navTags.push({
          index: view.meta.index,
          title: view.meta.title,
          active: true
        })
      } else {
        state.navTags[index].active = true
      }
    },
    removeOneNavTag (state, payload) {
      let index = state.navTags.findIndex((item) => {
        return item.index === payload.index
      })
      if (index === -1) {
        return
      }
      state.navTags.splice(index, 1)
    }
  },
  actions: {
    addNavTags ({ state, commit }, view) {
      commit('addNavTags', view)
    },
    removeOneNavTag ({ state, commit }, payload) {
      commit('removeOneNavTag', payload)
    }
  }
})

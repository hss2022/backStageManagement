import Vue from 'vue'
import Vuex from 'vuex'
import tab from './tab'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    clientName:'',
    phone:'',
    address:''
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    tab,
  }
})

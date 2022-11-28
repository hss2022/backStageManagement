import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios';
import VueAxios from 'vue-axios';
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false
Vue.use(VueAxios,axios);
Vue.use(ElementUI)
axios.defaults.baseURL='http://127.0.0.1:9090/' 
Vue.prototype.$axios = axios
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

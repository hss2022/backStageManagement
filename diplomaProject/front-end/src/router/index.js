import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '../views/LoginView.vue'
import HomeView from '../views/HomeView.vue'
// import MallView from '../views/MallView.vue'
import PageOne from '../views/PageOne.vue'
import PageTwo from '../views/PageTwo.vue'
import UserView from '../views/UserView.vue'
import MallOne from '../views/MallOne.vue'
import MallTwo from '../views/MallTwo.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/main',
    name: 'main',
    redirect:'/main/home',
  
    component: () => import('../views/MainView.vue'),
    children:[
      {path:'home',component:HomeView},
   
      
        // path:'mall',
        // path:'/mall',
        // component:MallView,
      // component:()=>import('../views/MallView.vue'),
     
      {path:'mall1',component:MallOne},
      {path:'mall2',component:MallTwo},
      {path:'user',component:UserView},
    
      {path:'page1',component:PageOne},
      {path:'page2',component:PageTwo}
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router

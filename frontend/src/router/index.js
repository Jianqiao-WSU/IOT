import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/Login'
import Register from '@/views/Register'
// import Main from '@/views/Main'
import Home from '@/components/Home'
// import Wifi from '@/components/Wifi'
// import Settings from '@/components/Settings'
// import Account from '@/components/Account'
// import Profile from '@/components/Profile'
import Page404 from '@/views/404'
import Layout from '@/layout'
import menus from '@/config/menu-config'

Vue.use(Router)

let WiFiChildren = []
menus.forEach((item) => {
  item.sub.forEach((cell) => {
    // console.log(cell)
    WiFiChildren.push({
      path: cell.name,
      name: cell.name,
      meta: cell.meta,
      component: () => import(`@/components/WiFi/${cell.name}`)
    })
  })
})
// 侧边栏二级菜单路由
let SettingsChildren = []
menus.forEach((item) => {
  item.sub.forEach((cell) => {
    // console.log(cell)
    SettingsChildren.push({
      path: cell.name,
      name: cell.name,
      meta: cell.meta,
      component: () => import(`@/components/Settings/${cell.name}`)
    })
  })
})

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/login',
      meta: {
        title: '物联网数据服务平台'
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        title: '物联网数据服务平台'
      }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register,
      meta: {
        title: '注册'
      }
    },
    {
      path: '/main',
      name: 'Main',
      component: Layout,
      meta: {
        title: '主页'
      },
      children: [
        {
          path: '/home',
          name: 'Home',
          component: Home,
          meta: {
            title: '首页',
            icon: 'home'
          }
        }
      ]
      // children: HomeChildren
      // children: [
      //   { path: 'Home', component: Home },
      //   { path: 'Wifi', component: Wifi },
      //   {
      //     path: 'Settings',
      //     component: Settings,
      //     children: [
      //       {
      //         path: 'Account', component: Account
      //       },
      //       {
      //         path: 'Profile', component: Profile
      //       }
      //     ]
      //   }
      // ]
    },
    {
      path: '/wifi',
      name: 'WiFi',
      redirect: '/WiFi/FrameInfo',
      component: Layout,
      meta: {
        title: 'WiFi数据'
      },
      children: WiFiChildren
    },
    {
      path: '/settings',
      name: 'Settings',
      redirect: '/settings',
      component: Layout,
      meta: {
        title: '设置'
      },
      children: SettingsChildren
    },
    { path: '*', redirect: '/404' },
    {
      path: '/404',
      name: 'Page404',
      component: Page404,
      meta: {
        title: '404'
      }
    }
  ]
})

// 路由跳转之前查看是否存在token
router.beforeEach((to, from, next) => {
  if (to.path !== '/login' && to.path !== '/register' && !localStorage.getItem('token')) {
    return next('/login')
  }
  next()
})

export default router

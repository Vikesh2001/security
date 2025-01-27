import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from '../views/LoginPage.vue'

const routes = [
  {
    path: '/login',
    name: 'home',
    component: LoginPage
  },
  {
    path: '/home',
    name: 'HomePage',
    component: () => import('../pages/HomePage.vue')
  },
  {
    path: '/contact',
    name: 'ContactPage',
    
    component: () => import('../pages/ContactPage.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ExerciseOneView from '../views/ExerciseOneView.vue'
import ExerciseTwoView from '../views/ExerciseTwoView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/one',
    name: 'one',
    component: ExerciseOneView
  },
  {
    path: '/two',
    name: 'two',
    component: ExerciseTwoView
  },
]

const router = new VueRouter({
  routes
})

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EmployeesList from '../views/EmployeesList.vue'
import ProjectList from '../views/ProjectList.vue'
import AsignationList from '../views/AsignationList.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/empleados',
    name: 'empleados',
    component: EmployeesList
  },
  {
    path: '/proyectos',
    name: 'proyectos',
    component: ProjectList
  },
  {
    path: '/asignaciones',
    name: 'asignaciones',
    component: AsignationList
  },
]

const router = new VueRouter({
  routes
})

export default router

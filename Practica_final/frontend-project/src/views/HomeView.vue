<template>
  <v-app>
    <v-container fluid>
      <v-parallax src="https://cdn.vuetifyjs.com/images/parallax/material.jpg">
        <v-container class="mt-6">
      <v-row>
        <v-col cols="12">
          <v-carousel hide-delimiters>
            <v-carousel-item v-for="(slide,index) in slides" :key="index">
              <v-sheet
              height="300px"
              tile
              class="d-flex justify-center align-center"
              :style="`background-image: url(${slide.image}); background-size:cover;`">
                <v-row class="fill-height" align="center">
                  <v-col class="white--text text-center">
                    <h3>{{ slide.title }}</h3>
                    <p>{{ slide.description }}</p>
                    <v-btn color="primary" @click="navigateTo(slide.route)">Ir a {{ slide.title }}</v-btn>
                  </v-col>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-col>
      </v-row>
    </v-container>

      </v-parallax>
      <v-layout
      column
      align-center
      justify-center
      class="white--text">
        <h4>Gestión de empleados, proyectos y asignaciones</h4>
      </v-layout>
    </v-container>
  </v-app>
</template>

<script>
  export default {
    name: 'Home',
    data: () => ({ 
        slides:[
          {
            title: 'Administración de Empleados',
            description: 'Gestión de todos los empleados que no han sido dados de baja',
            image:'',
            route:'/empleados'
          },
          {
            title: 'Administración de Proyectos',
            description: 'Gestión de todos los Proyectos que no han sido dados de baja',
            image:'',
            route:'/proyectos'
          },
          {
            title: 'Asignación de Empleados',
            description: 'Gestión de todas las asignaciones',
            image:'',
            route:'/asignaciones'
          },

        ],
     }),
     methods: {
      navigateTo(route){
        this.$router.push(route);
      }
     }
  }
</script>
<style>
  .v-carousel-item{
    position:relative;
  }
  .v-carousel-item::before{
    content:"";
    position:absolute;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background: rgba(0,0,0,0.4)
  }
  .fill-height{
    height:100%;
  }
</style>


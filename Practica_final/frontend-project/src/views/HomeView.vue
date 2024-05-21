<template>
  <v-parallax
    dark
    src="https://www.educative.io/v2api/collection/10370001/4817064685731840/image/4716623947890688"
  >
    <v-row
      align="center"
      justify="center"
    >
      <v-col
        class="text-center"
        cols="12"
      >
      <v-carousel hide-delimiters :cycle="true" interval="5000">
            <v-carousel-item v-for="(slide,index) in slides" :key="index">
              <v-sheet
              height="875px"
              tile
              class="d-flex justify-center align-center transparent-background"
              :style="`background-image: url(${slide.image}); background-size:cover;`">
                <v-row class="fill-height" align="center">
                  <v-col class="white--text text-center">
                    <h3>{{ slide.title }}</h3>
                    <p>{{ slide.description }}</p>
                    <v-btn color="#18E6B9" @click="navigateTo(slide.route)">Ir a {{ slide.title }}</v-btn>
                  </v-col>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
      </v-col>
    </v-row>
  </v-parallax>
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
  .transparent-background{
    background-color: transparent !important;
  }
  .mt-n10{
    margin-top: 500px !important
  }
</style>


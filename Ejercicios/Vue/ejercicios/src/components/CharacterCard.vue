<template>
    <!--Card con las características de cada personaje-->
    <transition name="fade">
    <v-card
          class="mx-auto "
          max-width="344"
          elevation="10"
          :color="color[house]"
        >
          <v-img
            height="450px"
            :src="image"
            cover
          ></v-img>

          <v-card-title class="title">
            {{name}}
          </v-card-title>

          <v-card-subtitle class="subtitle">
            <ul>
              <li>Especie: {{ species }}</li>
              <li>Casa : {{ house }}</li>
              <li>Fecha de nacimiento : {{ dateOfBirth }}</li>
              <li>Actor / actriz : {{ actor }}</li>
            </ul>
          </v-card-subtitle>
    </v-card>
    </transition>
</template>
<script>
    export default{
        name: 'CharacterCard',
        data:()=>({
            //Array asociativo para poner un color según la casa
            color: {
                "Gryffindor":"red",
                "Slytherin":"teal",
                "Hufflepuff":"yellow",
                "Ravenclaw":"indigo"
            }
        }),
        //Props que provienen del padre
        props:{
            image:String,
            name:String,
            species:String,
            house:String,
            dateOfBirth:String,
            actor:String
        }
    }
</script>
<style scoped>
    .title{
        font-size: 24px;
        font-weight: bold;
    }
    .subtitle{
        font-size: 16px;
    }
    .fade-enter-active, .fade-leave-active{
        transition: opacity 0.5s;
    }
    .fade-enter, .fade-leave-ro{
        opacity: 0;
    }
</style>
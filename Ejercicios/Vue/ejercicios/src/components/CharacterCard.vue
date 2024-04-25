<template>
    <transition name="fade">
    <v-card
          class="mx-auto"
          max-width="344"
          elevation="10"
          :color="color.house"
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
            color: {
                "Gryffindor":"red-darken-4",
                "Slytherin":"teal-darken-3",
                "Hufflepuff":"yellow-darken-1",
                "Ravenclaw":"indigo-accent-1"
            }
        }),
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
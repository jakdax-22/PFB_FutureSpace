<template>
    <div>
      <!--Si se está cargando el contenido de la API muestro cargando, y si hay error muestro el error-->
      <div v-show="loading">Cargando...</div>
      <div v-if="error" v-text="error"></div>
      <!--Grid para estructurar a tres personajes por fila-->
      <v-row class="mt-2">
        <!--Por cada iteración meto un personaje con sus correspondientes características-->
        <v-col cols="4" v-for="character of characters" :key="character.id">
          <CharacterCard :actor="character.actor" :date-of-birth="character.dateOfBirth" :house="character.house"
            :name="character.name" :species="character.species" :image="character.image"/>
        </v-col>

      </v-row>
      </div>
</template>

<script>
import CharacterCard from '@/components/CharacterCard.vue';


  export default {
    name: 'ExerciseTwoView',

    components: {
    CharacterCard
},
    computed: {
      characters() {
        return this.$store.state.characters;
      },
      loading (){
        return this.$store.state.loading;
      },
      error(){
        return this.$store.state.error;
      }
    },
    mounted(){
      this.$store.dispatch('fetchCharacters');
    }
  }
</script>

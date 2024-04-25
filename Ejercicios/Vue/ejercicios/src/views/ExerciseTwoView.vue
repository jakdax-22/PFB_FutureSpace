<template>
    <div>
      <div v-show="loading">Cargando...</div>
      <div v-if="error" v-text="error"></div>
      <v-row class="mt-2">
        <v-col cols="4" v-for="character of characters" :key="character.id">
          <v-card
          class="mx-auto"
          max-width="344"
        >
          <v-img
            height="450px"
            :src="character.image"
            cover
          ></v-img>

          <v-card-title>
            {{character.name}}
          </v-card-title>

          <v-card-subtitle>
            <ul>
              <li>Especie: {{ character.species }}</li>
              <li>Casa : {{ character.house }}</li>
              <li>Fecha de nacimiento : {{ character.dateOfBirth }}</li>
              <li>Actor / actriz : {{ character.actor }}</li>
            </ul>
          </v-card-subtitle>
        </v-card>
        </v-col>
      </v-row>
      </div>
</template>

<script>

  export default {
    name: 'ExerciseTwoView',

    components: {
    
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

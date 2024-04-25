<template>
    <div>
      <div v-show="loading">Cargando...</div>
      <div v-if="error" v-text="error"></div>
      <div v-for="character of characters" :key="character.id">
        <p>{{ character.name }}</p>
      </div>
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
        return this.$store.state.characters;
      },
      error(){
        return this.$store.state.error;
      }
    },
    mount(){
      this.$store.dispatch('fetchCharacters');
    }
  }
</script>

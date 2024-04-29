<!--Componente encargado de mostrar el formulario de los discos con su correspondiente lógica de validación-->
<template>
    <v-sheet class="mx-auto" width="300">
      <v-form fast-fail @submit.prevent>
        <v-text-field
          v-model="name"
          :rules="nameRules"
          label="Nombre"
        ></v-text-field>
  
        <v-text-field
          v-model="year"
          :rules="yearRules"
          label="Año"
        ></v-text-field>
        <v-select
            :items="artists"
            v-model="artist"
            item-text="name"
            density="compact"
            label="Artista"
        ></v-select>
        <v-btn :disabled="!year || !name || !artist" class="mt-2" @click="$store.dispatch('addDisc',{name,artist,year})" block>Insertar</v-btn>
      </v-form>
    </v-sheet>
  </template>
  <script>
  export default {
    name:"FormArtist",
    data: () => ({
      artist:'',
      selected:'',
      name: '',
      nameRules: [
        value => {
          if (value?.length > 3) return true

          return 'El nombre debe de tener como mínimo 3 caracteres'
        },
      ],
      year: '',
      yearRules: [
        value => {
          if (value < new Date().getFullYear && value > 1900) return true

          return 'Introduce un año válido'
        },
      ],
    }),
    computed:{
        artists(){
            return this.$store.state.artists;
        }
    }
  }
</script>
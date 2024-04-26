<template>
    <v-sheet class="mx-auto" width="300">
      <v-form fast-fail @submit.prevent>
        <v-text-field
          v-model="firstName"
          :rules="firstNameRules"
          label="Nombre"
        ></v-text-field>
  
        <v-text-field
          v-model="age"
          :rules="ageRules"
          label="Edad"
        ></v-text-field>
        <v-btn v-show="age && firstName" class="mt-2" @click="$store.dispatch('addArtist',{firstName,age})" block>Insertar</v-btn>
      </v-form>
    </v-sheet>
  </template>
  <script>
  export default {
    name:"FormArtist",
    data: () => ({
      firstName: '',
      firstNameRules: [
        value => {
          if (value?.length > 3) return true

          return 'El nombre debe de tener como mínimo 3 caracteres'
        },
      ],
      age: '',
      ageRules: [
        value => {
          if (value < 120 && value > 10) return true

          return 'Introduce una edad válida'
        },
      ],
    }),
  }
</script>
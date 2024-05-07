<template>
  <v-app>
      <router-view/>
      <home-view/>
  </v-app>
</template>

<script>
import HomeView from './views/HomeView.vue';

export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>

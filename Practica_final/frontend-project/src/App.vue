<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
    >
    <v-list-item subtitle="Vuetify" title="My Application">Ejercicios Enrique Iranzo Martínez</v-list-item>
        <v-divider></v-divider>
        <v-list-item title="Github" href="https://github.com/jakdax-22/PFB_FutureSpace/tree/main/Ejercicios" link>
          Mi Github con el código
        </v-list-item>
          <v-list-item title="Home" to="/">
            Home
          </v-list-item>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title>Ejercicios Enrique Iranzo Martínez</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>


<script>

export default {
  name: 'App',

  data: () => ({
    drawer:null,
  }),
};
</script>

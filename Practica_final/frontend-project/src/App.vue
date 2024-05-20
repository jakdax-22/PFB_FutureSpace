<template>
  <v-app>
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <v-sheet
        color="grey lighten-4"
        class="pa-4"
      >
        <v-avatar
          class="mb-4"
          color="grey darken-1"
          size="64"
        ></v-avatar>

        <div>Enrique Iranzo Martínez</div>
      </v-sheet>

      <v-divider></v-divider>

      <v-list>
        <v-list-item
          to="empleados"
          link
        >
          <v-list-item-icon>
            <v-icon>mdi-account-box</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>Gestión de Empleados</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          to="proyectos"
          link
        >
          <v-list-item-icon>
            <v-icon>mdi-account-network</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>Gestión de Proyectos</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          to="asignaciones"
          link
        >
          <v-list-item-icon>
            <v-icon>mdi-account-multiple-outline</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>Asignaciones</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item
          href="https://github.com/jakdax-22/PFB_FutureSpace/tree/main/Practica_final"
          link
        >
          <v-list-item-icon>
            <v-icon>mdi-github</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>Mi Github con el código</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title>Práctica Final</v-toolbar-title>
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

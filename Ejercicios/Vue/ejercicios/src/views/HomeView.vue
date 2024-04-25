<template>
    <v-main>
      <v-container>
        <v-row>
          <v-col cols="6">
            <v-card
            class="mx-auto"
            color="indigo"
            max-width="344"
            outlined
            mt-10
          >
        <v-list-item three-line>
          <v-list-item-content>
            <v-list-item-title class="text-h5 mb-1">
              Ejercicio 1
            </v-list-item-title>
            <v-list-item-subtitle>CRUD de listados en memoria con Vuex</v-list-item-subtitle>
          </v-list-item-content>

        </v-list-item>

        <v-card-actions>
          <router-link to="/one">
            <v-btn
              outlined
              rounded
              text
            >
              Ir al ejercicio
            </v-btn>
          </router-link>
        </v-card-actions>
      </v-card>
          </v-col>
          <v-col cols="6">
            <v-card
            class="mx-auto"
            color="indigo"
            variant="secondary"
            max-width="344"
            outlined
            mt-10
          >
        <v-list-item three-line>
          <v-list-item-content>
            <v-list-item-title class="text-h5 mb-1">
              Ejercicio 2
            </v-list-item-title>
            <v-list-item-subtitle>Conexión y listado de API con Axios</v-list-item-subtitle>
          </v-list-item-content>

        </v-list-item>

        <v-card-actions>
          <router-link to="/two">
            <v-btn
              outlined
              rounded
              text
            >
              Ir al ejercicio
            </v-btn>
          </router-link>
        </v-card-actions>
      </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
</template>
<script>
    export default {
        name:"HomeView",
        data:()=>({

        })
    }
</script>
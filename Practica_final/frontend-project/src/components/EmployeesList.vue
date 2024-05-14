<template>
    <div>
    <v-data-table
    :headers="headers"
    :items="employees"
    class="elevation-1">
        <template v-slot:top>
            <v-toolbar flat color="white">
                <v-toolbar-title>Usuarios</v-toolbar-title>
                <v-spacer></v-spacer>
                <template v-slot:activator="{ on, attrs }">
                    <v-btn
                    color="primary"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    >
                    Open Dialog
                    </v-btn>
                    <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="600px"
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">User Profile</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="Legal first name*"
                  required
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="Legal middle name"
                  hint="example of helper text only on focus"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="Legal last name*"
                  hint="example of persistent helper text"
                  persistent-hint
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Email*"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Password*"
                  type="password"
                  required
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
              >
                <v-select
                  :items="['0-17', '18-29', '30-54', '54+']"
                  label="Age*"
                  required
                ></v-select>
              </v-col>
              <v-col
                cols="12"
                sm="6"
              >
                <v-autocomplete
                  :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"
                  label="Interests"
                  multiple
                ></v-autocomplete>
              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
                </template>
                <v-spacer></v-spacer>
                <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Buscar"
                    single-line
                    hide-details>

                </v-text-field>
            </v-toolbar>
        </template>
        <template v-slot:item.actions="{ item }">
            <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
        </template>
    </v-data-table>
    </div>
</template>

<script>
import axios from 'axios'
    export default{
        data(){
            return{
                search: '',
                headers: [
                    { text: 'NIF', value: 'nif' },
                    { text: 'Nombre', value: 'name' },
                    { text: 'Primer Apellido', value: 'lastName' },
                    { text: 'Segundo Apellido', value: 'secondLastName' },
                    { text: 'Fecha de nacimiento', value: 'birthDate' },
                    { text: 'Primer teléfono', value: 'firstNumber' },
                    { text: 'Email', value: 'email' },
                    { text: 'Estado civil', value: 'civilStatement' },
                    { text: 'Servicio Militar', value: 'militarService' },
                    { text: 'Acciones', value: 'actions' },
                ],
                employees:[],
            }
        },
        mounted(){
            this.getEmployees();
        },
        methods:{
            //Método para mandar una petición a la API y recoger todos los usuarios 
            async getEmployees(){
                try{
                    const response = await axios.get('http://localhost:8080/employee/active');
                    this.employees = response.data;
                    console.log(this.employees)
                }
                catch(error){
                    console.log(error)
                }
            }
        }
    }
</script>
<style scoped>
    .v-data-table{
        margin: 0 auto;
        width: 80%;
        border-radius: 10px;
        box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
    }
    .v-toolbar {
        border-radius: 10px 10px 0px 0px;
    }
</style>
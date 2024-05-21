<template>
    <div>
    <v-data-table
    :headers="headers"
    :items="filteredProjects"
    class="elevation-1 mt-10">
        <template v-slot:top>
            <v-toolbar flat color="white">
              <v-row>
                <v-col cols="4" class="d-flex align-center">
                  <v-toolbar-title>Proyectos</v-toolbar-title>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Buscar por Descripción"
                    single-line
                    hide-details
                    clearable
                    >
                  </v-text-field>
                </v-col>
                <v-col cols="4" class="d-flex justify-end">
                  <v-btn
                      color="primary"
                      dark
                      @click="dialog = true; insertedProject = {}"
                      >
                      <v-icon>mdi-account-plus</v-icon>
                      Insertar
                  </v-btn>
                </v-col>
              </v-row>
            </v-toolbar>
        </template>
        <template v-slot:item.actions="{ item }">
            <button><span small @click="confirmDeleteProject(item.projectId)">❌</span></button>
        </template>
    </v-data-table>
    <v-row justify="center">
    <v-dialog
      v-model="dialog"
      max-width="600px"
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">Insertar Nuevo Proyecto</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
              >
                <v-text-field
                  label="Descripción"
                  required
                  v-model="insertedProject.description"
                ></v-text-field>
              </v-col>
              <v-col
                cols="6"
              >
              <template>
                  <div>
                    <v-menu
                      ref="menu"
                      v-model="menu"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="insertedProject.startDate"
                          label="Fecha de Alta"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                        >{{ insertedProject.startDate }}</v-text-field>
                      </template>
                      <v-date-picker
                        v-model="insertedProject.startDate"
                        :active-picker.sync="activePicker"
                        :max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substring(0, 10)"
                        min="1950-01-01"
                        @change="save"
                      ></v-date-picker>
                    </v-menu>
                  </div>
                </template>
              </v-col>
              <v-col
                cols="6"
              >
              <template>
                  <div>
                    <v-menu
                      ref="menu2"
                      v-model="menu2"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="insertedProject.endDate"
                          label="Fecha de Finalización"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                        >{{ insertedProject.endDate }}</v-text-field>
                      </template>
                      <v-date-picker
                        v-model="insertedProject.endDate"
                        :active-picker.sync="activePicker"
                        :max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substring(0, 10)"
                        min="1950-01-01"
                        @change="save"
                      ></v-date-picker>
                    </v-menu>
                  </div>
                </template>
              </v-col>
              <v-col
                cols="6"
              >
                <v-text-field
                  label="Lugar"
                  required
                  v-model="insertedProject.place"
                ></v-text-field>
            </v-col>
              <v-col
                cols="6"
              >
                <v-textarea
                  label="Observaciones"
                  required
                  v-model="insertedProject.observations"
                ></v-textarea>
            </v-col>
            </v-row>
          </v-container>
          <small>Todos los campos son obligatorios</small>
        </v-card-text>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Cancelar
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false,addProject()"
          >
            Aceptar
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
    </div>
</template>

<script>
import axios from 'axios'
import Swal from 'sweetalert2'
    export default{
        data(){
            return{
                search: '',
                activePicker: null,
                date: null,
                menu: false,
                menu2: false,
                headers: [
                    { text: 'Descripción', value: 'description' },
                    { text: 'Fecha de Inicio', value: 'startDate' },
                    { text: 'Fecha de Finalización', value: 'endDate' },
                    { text: 'Lugar', value: 'place' },
                    { text: 'Acciones', value: 'actions', sortable:"false" },

                ],
                projects:[],
                dialog:false,
                insertedProject:{
                    description:'',
                    startDate:'',
                    endDate:'',
                    place:'',
                    observations: '',
                }
            }
        },
        mounted(){
            this.getProjects();
        },
        computed: {
          filteredProjects(){
            if (this.search && this.search.length > 0)
              return this.projects.filter(project => project.description.toLowerCase().includes(this.search.toLowerCase()));
            else
              return this.projects;
          }
        },
        methods:{
          save(date) {
            this.$refs.menu.save(date);
          },
            //Método para mandar una petición a la API y recoger todos los usuarios 
            async getProjects(){
                try{
                    const response = await axios.get('http://localhost:8080/project/active');
                    this.projects = response.data;
                    //Comprobación
                    //console.log(this.projects)
                }
                catch(error){
                    console.log(error)
                }
            },
            //Método para insertar un nuevo proyecto
            async addProject(){
                //Convertir valores
                /*this.insertedProject.startDate = this.insertedEmployee.civilState[0];
                this.insertedEmployee.militarService = this.insertedEmployee.militarService[0];*/
                //Probar la solicitud
                try{
                    const response = await axios.post('http://localhost:8080/project',this.insertedProject);

                    //Comprobar el código de estado devuelto
                    if (response.status === 200){
                        //Se ha agregado correctamente
                        Swal.fire('Todo correcto', response.data, 'success');
                        this.getProjects();
                    }
                    else{
                        //Ha habido algún error
                        Swal.fire('Error', response.data,'error');
                    }
                }
                catch (error){
                    //Error en la solicitud
                    Swal.fire('Error', 'Error al realizar la solicitud', 'error');
                }
                finally{
                    //Limpiar el formulario
                    this.insertedProject = {};
                }
            },
            async confirmDeleteProject(id){
                //Mostrar modal de confirmación
                const confirmation = await Swal.fire({
                    title:"¿Estas seguro?",
                    text: "Esta acción no se puede deshacer",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonText: 'Eliminar',
                    cancelButtonText: 'Cancelar',
                });
                if (confirmation.isConfirmed){
                  try{
                    //Enviar solicitud
                    const response = await axios.put(`http://localhost:8080/project/${id}`);
                    //Manejar respuesta 
                    if (response.status === 200){
                      Swal.fire("Eliminado", response.data,'success');
                      this.getProjects();
                    }
                    else if (response.data.status === 500){
                      Swal.fire("Error",response.data.message,'error');
                    }
                }
                  catch (error){
                    //console.log(error.code);
                      //Manejar error
                      if (error.code === "ERR_BAD_REQUEST"){
                        Swal.fire("Error",error.response.data,'error');

                      }
                  }
                  }
            },
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
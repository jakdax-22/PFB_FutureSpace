<template>
    <div>
    <v-data-table
    :headers="headers"
    :items="projects"
    class="elevation-1 mt-10">
        <template v-slot:top>
            <v-toolbar flat color="white">
                <v-row justify="center">
                    <v-toolbar-title class="col-6">Proyectos</v-toolbar-title>
                    <v-btn
                        class="col-4 mt-2"
                        color="primary"
                        dark
                        @click="dialog = true"
                        >
                        <v-icon>mdi-account-plus</v-icon>
                        Insertar Proyectos
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-text-field
                    class="col-8 mb-12"
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Buscar"
                    single-line
                    hide-details>
                    </v-text-field>
                    <v-spacer></v-spacer>
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
      persistent
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
                cols="12"
              >
                <v-text-field
                  label="Fecha de inicio"
                  required
                  v-model="insertedProject.startDate"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
              >
                <v-text-field
                  label="Fecha de finalización"
                  required
                  v-model="insertedProject.endDate"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
              >
                <v-text-field
                  label="Lugar"
                  required
                  v-model="insertedProject.place"
                ></v-text-field>
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
                }
            }
        },
        mounted(){
            this.getProjects();
        },
        methods:{
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
                    Swal.fire("Eliminado", "El proyecto ha sido dado de baja con éxito",'success');
                    this.getProjects();
                }
                  catch (error){
                      //Manejar error
                      Swal.fire("Error","Error al dar de baja el proyecto",'error');
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
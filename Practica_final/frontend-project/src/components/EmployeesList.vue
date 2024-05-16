<template>
    <div>
    <v-data-table
    :headers="headers"
    :items="employees"
    class="elevation-1 mt-10">
        <template v-slot:top>
            <v-toolbar flat color="white">
                <v-row justify="center">
                    <v-toolbar-title class="col-6">Usuarios</v-toolbar-title>
                    <v-btn
                        class="col-4 mt-2"
                        color="primary"
                        dark
                        @click="dialog = true"
                        >
                        <v-icon>mdi-account-plus</v-icon>
                        Insertar Usuario
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
            <button><span small @click="confirmDeleteEmployee(item.employeeId)">❌</span></button>
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
          <span class="text-h5">Insertar Nuevo Usuario</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
              >
                <v-text-field
                  label="NIF"
                  required
                  v-model="insertedEmployee.nif"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="Nombre"
                  required
                  v-model="insertedEmployee.name"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="Primer apellido"
                  required
                  v-model="insertedEmployee.lastName"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="Segundo apellido"
                  required
                  v-model="insertedEmployee.secondLastName"
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  label="Fecha de nacimiento"
                  required
                  v-model="insertedEmployee.birthDate"
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  label="Fecha de alta"
                  required
                  v-model="insertedEmployee.registrationDate"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Primer teléfono"
                  required
                  v-model="insertedEmployee.firstNumber"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
              >
                <v-text-field
                  label="Segundo teléfono"
                  required
                  v-model="insertedEmployee.secondNumber"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
              >
                <v-text-field
                  label="Email"
                  required
                  v-model="insertedEmployee.email"
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
              >
                <v-select
                  :items = "['Soltero','Casado']"
                  label="Estado civil"
                  required
                  v-model="insertedEmployee.civilState"
                ></v-select>
              </v-col>
              <v-col
                cols="12"
                sm="6"
              >
              <v-select
                  :items = "['Sí','No']"
                  label="Servicio militar"
                  required
                  v-model="insertedEmployee.militarService"
                ></v-select>
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
            @click="dialog = false,addEmployee()"
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
                    { text: 'NIF', value: 'nif' },
                    { text: 'Nombre', value: 'name' },
                    { text: 'Primer Apellido', value: 'lastName' },
                    { text: 'Segundo Apellido', value: 'secondLastName' },
                    { text: 'Fecha de nacimiento', value: 'birthDate' },
                    { text: 'Primer teléfono', value: 'firstNumber' },
                    { text: 'Email', value: 'email' },
                    { text: 'Estado civil', value: 'civilState' },
                    { text: 'Servicio Militar', value: 'militarService' },
                    { text: 'Acciones', value: 'actions' },
                ],
                employees:[],
                dialog:false,
                insertedEmployee:{
                    nif:'',
                    name:'',
                    lastName:'',
                    secondLastName:'',
                    birthDate:'',
                    firstNumber:'',
                    secondNumber:'',
                    email:'',
                    registrationDate:'',
                    civilState:'',
                    militarService:''
                }
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
            },
            //Método para insertar un nuevo empleado
            async addEmployee(){
                //Convertir valores
                this.insertedEmployee.civilState = this.insertedEmployee.civilState[0];
                this.insertedEmployee.militarService = this.insertedEmployee.militarService[0];
                //Probar la solicitud
                try{
                    const response = await axios.post('http://localhost:8080/employee',this.insertedEmployee);

                    //Comprobar el código de estado devuelto
                    if (response.status === 200){
                        //Se ha agregado correctamente
                        Swal.fire('Todo correcto', response.data, 'success');
                        this.getEmployees();
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
                    this.insertedEmployee = {};
                }
            },
            async confirmDeleteEmployee(id){
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
                    const response = await axios.put(`http://localhost:8080/employee/${id}`);

                    //Manejar respuesta 
                    Swal.fire("Eliminado", "El empleado ha sido dado de baja con éxito",'success');
                    this.getEmployees();
                }
                  catch (error){
                      //Manejar error
                      Swal.fire("Error","Error al dar de baja el usuario",'error');
                  }
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
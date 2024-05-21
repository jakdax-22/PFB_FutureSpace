


<template>
  <div>
    <v-data-table :headers="headers" :items="filteredEmployees" class="elevation-1 mt-10">
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-row>
            <v-col cols="4" class="d-flex align-center">
              <v-toolbar-title>Empleados</v-toolbar-title>
            </v-col>
            <v-col cols="4">
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Buscar por NIF"
                single-line
                hide-details
                clearable
              ></v-text-field>
            </v-col>
            <v-col cols="4" class="d-flex justify-end">
              <v-btn color="primary" dark @click="dialog = true; insertedEmployee = {}; clearErrors()">
                <v-icon>mdi-account-plus</v-icon>
                Insertar
              </v-btn>
            </v-col>
          </v-row>
        </v-toolbar>
      </template>
      <template v-slot:item.actions="{ item }">
        <button>
          <span small @click="confirmDeleteEmployee(item.employeeId)">❌</span>
        </button>
      </template>
    </v-data-table>
    <v-row justify="center">
      <v-dialog v-model="dialog" max-width="600px">
        <v-card>
          <v-card-title>
            <span class="text-h5">Insertar Nuevo Empleado</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="4">
                  <v-text-field
                    label="NIF"
                    required
                    v-model="insertedEmployee.nif"
                    :error-messages="errors.nif"
                    @blur="validateField('nif')"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Nombre"
                    required
                    v-model="insertedEmployee.name"
                    :error-messages="errors.name"
                    @input="validateField('name')"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Primer apellido"
                    required
                    v-model="insertedEmployee.lastName"
                    :error-messages="errors.lastName"
                    @input="validateField('lastName')"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Segundo apellido"
                    required
                    v-model="insertedEmployee.secondLastName"
                    :error-messages="errors.secondLastName"
                    @input="validateField('secondLastName')"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
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
                            v-model="insertedEmployee.birthDate"
                            label="Fecha de Nacimiento"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            :error-messages="errors.birthDate"
                          >
                            {{ insertedEmployee.birthDate }}
                          </v-text-field>
                        </template>
                        <v-date-picker
                          v-model="insertedEmployee.birthDate"
                          :active-picker.sync="activePicker"
                          :max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substring(0, 10)"
                          min="1950-01-01"
                          @change="validateField('birthDate')"
                        ></v-date-picker>
                      </v-menu>
                    </div>
                  </template>
                </v-col>
                <v-col cols="4">
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
                            v-model="insertedEmployee.registrationDate"
                            label="Fecha de Alta"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            :error-messages="errors.registrationDate"
                          >
                            {{ insertedEmployee.registrationDate }}
                          </v-text-field>
                        </template>
                        <v-date-picker
                          v-model="insertedEmployee.registrationDate"
                          :active-picker.sync="activePicker"
                          :max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substring(0, 10)"
                          min="1950-01-01"
                          @change="validateField('registrationDate')"
                        ></v-date-picker>
                      </v-menu>
                    </div>
                  </template>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Primer teléfono"
                    required
                    v-model="insertedEmployee.firstNumber"
                    :error-messages="errors.firstNumber"
                    @input="validateField('firstNumber')"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Segundo teléfono"
                    required
                    v-model="insertedEmployee.secondNumber"
                    :error-messages="errors.secondNumber"
                    @input="validateField('secondNumber')"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Email"
                    required
                    v-model="insertedEmployee.email"
                    :error-messages="errors.email"
                    @input="validateField('email')"
                  ></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-select
                    :items="['Soltero', 'Casado']"
                    label="Estado civil"
                    required
                    v-model="insertedEmployee.civilState"
                    :error-messages="errors.civilState"
                    @change="validateField('civilState')"
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-select
                    :items="['Sí', 'No']"
                    label="Servicio militar"
                    required
                    v-model="insertedEmployee.militarService"
                    :error-messages="errors.militarService"
                    @change="validateField('militarService')"
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>
            <small>Todos los campos son obligatorios</small>
          </v-card-text>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="dialog = false">
              Cancelar
            </v-btn>
            <v-btn color="blue darken-1" text @click="addEmployee() ; dialog=false" :disabled="!isValidForm">
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
              employees: [],
              dialog: false,
              insertedEmployee: {
                nif: '',
                name: '',
                lastName: '',
                secondLastName: '',
                birthDate: '',
                firstNumber: '',
                secondNumber: '',
                email: '',
                registrationDate: '',
                civilState: '',
                militarService: ''
              },
              errors: {
                nif: [],
                name: [],
                lastName: [],
                secondLastName: [],
                birthDate: [],
                firstNumber: [],
                secondNumber: [],
                email: [],
                registrationDate: [],
                civilState: [],
                militarService: []
              }
            }
        },
        mounted(){
            this.getEmployees();
        },
        watch: {
          menu (val) {
            val && setTimeout(() => (this.activePicker = 'YEAR'))
          },
        },
        computed: {
          filteredEmployees(){
            if (this.search && this.search.length > 0)
              return this.employees.filter(employee => employee.nif.toLowerCase().includes(this.search.toLowerCase()));
            else
              return this.employees;
          },
          isValidForm() {
            this.validateAllFields();
            return Object.values(this.errors).every(errorArray => errorArray.length === 0);
          }
        },
        methods:{
            //Método para mandar una petición a la API y recoger todos los usuarios 
            save (date) {
              this.$refs.menu.save(date)
            },
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
            clearErrors() {
              for (let key in this.errors) {
                this.errors[key] = [];
              }
            },
            validateField(field) {
              this.errors[field] = [];
              if (!this.insertedEmployee[field]) {
                this.errors[field].push('Este campo es obligatorio');
              }

              if (field === 'birthDate') {
                const birthDate = new Date(this.insertedEmployee.birthDate);
                const age = new Date().getFullYear() - birthDate.getFullYear();
                if (age < 16 || age > 65) {
                  this.errors[field].push('La edad debe estar entre 16 y 65 años');
                }
              }
            },
            validateAllFields() {
              const fields = [
                'nif', 'name', 'lastName', 'secondLastName',
                'birthDate', 'firstNumber', 'secondNumber',
                'email', 'registrationDate', 'civilState', 'militarService'
              ];
              fields.forEach(field => this.validateField(field));
            },
            save(date) {
              this.$refs.menu.save(date);
              this.validateField('birthDate');
            },
            //Método para insertar un nuevo empleado
            async addEmployee(){
                if (this.isValidForm){
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
                    if (error.code === "ERR_BAD_REQUEST"){
                        Swal.fire("Error",error.response.data,'error');
                      }
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

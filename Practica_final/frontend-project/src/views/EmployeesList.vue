<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="filteredEmployees"
      class="elevation-1 mt-10">
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
                clearable>
              </v-text-field>
            </v-col>
            <v-col cols="4" class="d-flex justify-end">
              <v-btn
                color="primary"
                dark
                @click="dialog = true; resetForm()">
                <v-icon>mdi-account-plus</v-icon>
                Insertar
              </v-btn>
            </v-col>
          </v-row>
        </v-toolbar>
      </template>
      <template v-slot:item.actions="{ item }">
        <button><span small @click="confirmDeleteEmployee(item.employeeId)">❌</span></button>
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
                    v-model="insertedEmployee.nif"
                    :rules="rules['nif']"
                    required>
                  </v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Nombre"
                    v-model="insertedEmployee.name"
                    :rules="rules['name']"
                    required>
                  </v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Primer apellido"
                    v-model="insertedEmployee.lastName"
                    :rules="rules['lastName']"
                    required>
                  </v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Segundo apellido"
                    v-model="insertedEmployee.secondLastName"
                    :rules="rules['secondLastName']"
                    required>
                  </v-text-field>
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
                        min-width="auto">
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="insertedEmployee.birthDate"
                            label="Fecha de Nacimiento"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            :rules="rules['birthDate']"
                            >
                          </v-text-field>
                        </template>
                        <v-date-picker
                          v-model="insertedEmployee.birthDate"
                          :active-picker.sync="activePicker"
                          :max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substring(0, 10)"
                          min="1950-01-01"
                          @change="save">
                        </v-date-picker>
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
                        min-width="auto">
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            v-model="insertedEmployee.registrationDate"
                            label="Fecha de Alta"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                            :rules="rules['registrationDate']"
                            >
                          </v-text-field>
                        </template>
                        <v-date-picker
                          v-model="insertedEmployee.registrationDate"
                          :active-picker.sync="activePicker"
                          :max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substring(0, 10)"
                          min="1950-01-01"
                          @change="save">
                        </v-date-picker>
                      </v-menu>
                    </div>
                  </template>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Primer teléfono"
                    v-model="insertedEmployee.firstNumber"
                    :rules="rules['firstNumber']"
                    required>
                  </v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Segundo teléfono"
                    v-model="insertedEmployee.secondNumber"
                    :rules="rules['secondNumber']"
                    required>
                  </v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                    label="Email"
                    v-model="insertedEmployee.email"
                    :rules="rules['email']"
                    required>
                  </v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-select
                    :items="['Soltero', 'Casado']"
                    label="Estado civil"
                    v-model="insertedEmployee.civilState"
                    :rules="rules['civilState']"
                    required>
                  </v-select>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-select
                    :items="['Sí', 'No']"
                    label="Servicio militar"
                    v-model="insertedEmployee.militarService"
                    :rules="rules['militarService']"
                    required>
                  </v-select>
                </v-col>
              </v-row>
            </v-container>
            <small>Todos los campos son obligatorios</small>
          </v-card-text>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="dialog = false">
              Cancelar
            </v-btn>
            <v-btn :disabled="!isValidForm" color="blue darken-1" text @click="dialog = false; addEmployee()">
              Aceptar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
  data() {
    return {
      search: '',
      activePicker: null,
      date: null,
      menu: false,
      menu2: false,
      dialog: false,
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
      insertedEmployee: {},
      errorMessages: [],
      initialRules:{},
      rules: {
        nif: [
          v => !!v || 'NIF es requerido',
          v => /^[0-9]{8}[a-zA-Z]$/.test(v) || 'Formato de NIF no válido'
        ],
        name: [
          v => !!v || 'Nombre es requerido',
          v => (v && v.length <= 30) || 'Nombre demasiado largo'
        ],
        lastName: [
          v => !!v || 'Primer apellido es requerido',
          v => (v && v.length <= 30) || 'Primer apellido demasiado largo'
        ],
        secondLastName: [
          v => !!v || 'Segundo apellido es requerido',
          v => (v && v.length <= 30) || 'Segundo apellido demasiado largo'
        ],
        birthDate: [
          v => !!v || 'Fecha de nacimiento es obligatoria',
          v => {
            const age = this.calculateAge(v);
            return (age >= 16 && age <= 65) || 'La edad debe estar entre 16 y 65 años';
          }
        ],
        firstNumber: [
          v => !!v || 'Primer teléfono es requerido',
          v => /^[1-9][0-9]{8}$/.test(v) || 'Formato de teléfono no válido'
        ],
        secondNumber: [
          v => /^[1-9][0-9]{8}$/.test(v) || 'Formato de teléfono no válido'
        ],
        email: [
          v => !!v || 'Email es requerido',
          v => /.+@.+\..+/.test(v) || 'Email debe ser válido'
        ],
        registrationDate: [
          v => !!v || 'Fecha de alta es obligatorio'
        ],
        civilState: [
          v => !!v || 'Estado civil es obligatorio'
        ],
        militarService: [
          v => !!v || 'Servicio militar es obligatorio'
        ]
      },
    };
  },
  computed: {
    filteredEmployees() {
      if (!this.search) {
        return this.employees;
      }
      return this.employees.filter(employee => 
        employee.nif.toLowerCase().includes(this.search.toLowerCase())
      );
    },
    isValidForm() {
      if (this.dialog){
        
      }
      let valid = true;

      const fields = Object.keys(this.rules);
      fields.forEach(field => {
        if (!Array.isArray(this.rules[field])) {
          return; // No hay reglas definidas para el campo, se considera válido
        }

        const fieldValue = this.insertedEmployee[field];
        const fieldRules = this.rules[field];

        // Inicializar el mensaje de error como vacío
        this.errorMessages[field] = '';

        // Verificar cada regla de validación para el campo
        for (const rule of fieldRules) {
          const errorMessage = rule(fieldValue);
          if (errorMessage !== true) {
            this.errorMessages[field] = errorMessage;
            valid = false;
            break;
          }
        }
      });
      return valid;
      }
  },


  methods: {
    resetEmployee() {
      return {
        nif: '',
        name: '',
        lastName: '',
        secondLastName: '',
        birthDate: '',
        registrationDate: '',
        firstNumber: '',
        secondNumber: '',
        email: '',
        civilState: '',
        militarService: '',
      };
    },
    resetForm() {
      this.insertedEmployee = this.resetEmployee();
      this.errorMessages = {};
      for (const key in this.insertedEmployee){
        this.rules[key] = this.initialRules[key];
      }
    },
    calculateAge(birthDate) {
      const today = new Date();
      const birth = new Date(birthDate);
      let age = today.getFullYear() - birth.getFullYear();
      const monthDifference = today.getMonth() - birth.getMonth();
      if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < birth.getDate())) {
        age--;
      }
      return age;
    },
    save(date) {
      this.$refs.menu.save(date);
      this.validateField('birthDate');
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
        console.log(this.rules);
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
},
  mounted(){
      this.getEmployees();
      this.initialRules = {...this.rules};
  },
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
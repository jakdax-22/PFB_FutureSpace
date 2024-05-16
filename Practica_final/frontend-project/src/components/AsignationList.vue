<template>
    <div>
        <h1>Asignación de Empleados a Proyectos</h1>
        <v-container>
            <v-row>
                <v-col cols="12" sm="6">
                    <v-expansion-panels>
                        <v-expansion-panel
                        v-for="project in projects"
                        :key="project.projectId"
                        >
                        <v-expansion-panel-header>
                            {{ project.description }}
                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
                            <v-data-table
                            :headers="employeeHeaders"
                            :items="employees"
                            item-key="id"
                            class="elevation-1"
                        >
                            <template v-slot:items="props">
                                <td>{{ props.item.nif }}</td>
                                <td>{{ props.item.name + props.item.lastName + props.item.secondLastName }}</td>
                            <td>
                                <v-checkbox 
                                    v-model="props.item.assigned">
                                </v-checkbox>
                            </td>
                        </template>
                            </v-data-table>
                        </v-expansion-panel-content>
                        </v-expansion-panel>
                    </v-expansion-panels>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-btn @click="saveChanges">Guardar Cambios</v-btn>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import axios from 'axios'

    export default{
        data(){
            return{
                checkbox:false,
                projects:[],
                assignedEmployees:[],
                employees: [],
                employeeHeaders: [
                    {text: 'NIF', value:'nif'},
                    {text: 'Nombre', value:'name'},
                    {text: 'Asignado', value: 'assigned'},
                ],

            }
        },
        mounted(){
            this.fetchProjects();
            this.getEmployees();
        },
        methods: {
            async fetchProjects(){
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
            async getEmployees(){
                try{
                    const response = await axios.get('http://localhost:8080/employee/active');
                    let employees = response.data;
                    //console.log(this.employees)
                    //Añadir nuevo campo
                    this.employees = employees.map(employee => {
                        return {...employee, assigned: true}
                    })
                    console.log(this.employees);
                }
                catch(error){
                    console.log(error)
                }
            },
            saveChanges(){

            }
        }
    }
</script>
<template>
    <div>
        <v-container class="d-flex flex-column align-center justify-center">
            <v-row>
                <v-card class="mt-6 customcardwidth">
                    <v-card-title>
                        <v-row>
                            <v-col cols="12">
                                Asignación de Empleados a Proyectos
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="12">
                            <v-text-field
                            v-model="search"
                            label="Buscar proyecto por descripción"
                            clearable
                            append-icon="mdi-magnify"
                            @input="searchProjects"
                            >

                            </v-text-field>
                            </v-col>
                        </v-row>
                    </v-card-title>
                    <v-card-text>
                        <v-col cols="12">
                    <v-expansion-panels v-model="activePanel">
                        <v-expansion-panel
                        v-for="project in paginatedProjects"
                        :key="project.projectId"
                        >
                        <v-expansion-panel-header @click="getAsignations(project.projectId)">
                            {{ project.description }}
                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
                            <v-data-table
                            :headers="employeeHeaders"
                            :items="assignedEmployees"
                            item-key="id"
                            class="elevation-1"
                        >
                            <template v-slot:item.assigned="{item}">
                                <v-simple-checkbox 
                                    v-model="item.assigned">
                                </v-simple-checkbox>
                            </template>
                            </v-data-table>
                        </v-expansion-panel-content>
                        </v-expansion-panel>
                    </v-expansion-panels>
                </v-col>
                    </v-card-text>
                    <v-card-actions>
                        <v-pagination
                        v-model="projectPage"
                        :length="projectPageCount">
                            
                        </v-pagination>
                    </v-card-actions>
                </v-card>
            </v-row>
            <v-row>
                <v-col>
                    <v-btn @click="saveChanges()">Guardar Cambios</v-btn>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import axios from 'axios'
    import Swal from 'sweetalert2';

    export default{
        data(){
            return{
                activePanel : null,
                checkbox:false,
                projects:[],
                assignedEmployees:[],
                employees: [],
                asignations: [],
                employeeHeaders: [
                    {text: 'Nombre', value:'name'},
                    {text: 'Asignado', value: 'assigned'},
                ],
                projectPage:1,
                itemsPerPage:5,
                filteredProjects:[],
                search:'',

            }
        },
        mounted(){
            this.fetchProjects();
        },
        computed:{
            paginatedProjects(){
                const start = (this.projectPage - 1) * this.itemsPerPage;
                const end = start + this.itemsPerPage;
                return this.filteredProjects.slice(start,end);
            },
            projectPageCount(){
                return Math.ceil(this.filteredProjects.length / this.itemsPerPage);
            }
        },
        methods: {
            async fetchProjects(){
                try{
                    const response = await axios.get('http://localhost:8080/project/active');
                    this.projects = response.data;
                    this.filteredProjects = this.projects;
                }
                catch(error){
                    console.log(error)
                }
            },
            async getAsignations(projectId){
                try{
                    const response = await axios.get(`http://localhost:8080/employee/assigned/${projectId}`);
                    this.assignedEmployees = response.data;
                    this.assignedEmployees = this.assignedEmployees.map(employee => {
                        return {
                            ...employee,
                            projectId: projectId,
                            originalAssigned:employee.assigned
                        }
                    });
                    console.log(this.assignedEmployees);
                }
                catch(error){
                    console.log(error)
                }
            },
            async saveChanges(){
                const insertAssinged = this.assignedEmployees.reduce((result,employee) => {
                    if (employee.assigned !== employee.originalAssigned && employee.assigned){
                        result.push({employeeId : employee.employeeId,
                                    projectId: employee.projectId})
                    }
                    return result;
                }, [])
                const deleteAssigned = this.assignedEmployees.reduce ((result,employee) => {
                    if (employee.assigned !== employee.originalAssigned && !employee.assigned){
                        result.push({employeeId : employee.employeeId,
                                    projectId: employee.projectId})
                    }
                    return result;
                }, []);
                console.log(deleteAssigned);

                if (insertAssinged.length > 0 || deleteAssigned.length > 0){
                    const confirmation = await Swal.fire({
                    title:"¿Estas seguro?",
                    text: "¿Estas seguro de que deseas continuar?",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonText: 'Continuar',
                    cancelButtonText: 'Cancelar',
                    });
                    if (confirmation.isConfirmed){
                        //Probar la solicitud
                        try{
                            const response = await axios.post('http://localhost:8080/asignations',insertAssinged);
                            const response2 = await axios.delete('http://localhost:8080/asignations',{data : deleteAssigned});
                            //Comprobar el código de estado devuelto
                            if (response2.status === 200 && response.status === 200){
                                //Se ha agregado correctamente
                                Swal.fire('Todo correcto', response2.data, 'success');
                                this.activePanel = null;
                                this.fetchProjects();
                            }
                            else{
                                //Ha habido algún error
                                Swal.fire('Error', response2.data,'error');
                            }
                        }
                        catch (error){
                            //Error en la solicitud
                            Swal.fire('Error', 'Error al realizar la solicitud', 'error');
                        }
                    }
                }

            },
            searchProjects(){
                if (this.search === ''){
                    this.filteredProjects = this.projects;
                }
                else {
                    this.filteredProjects = this.projects.filter(project => {
                        project.description.toLowerCase().includes(this.search.toLowerCase());
                    })
                }
            }
        }


    }
</script>
<style>
    .customcardwidth{
        width:100%;
    }
</style>
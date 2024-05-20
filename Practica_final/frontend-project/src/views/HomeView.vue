<template>
  <EmployeesList/>
</template>

<script>
import AsignationList from '@/components/AsignationList.vue';
import EmployeesList from '@/components/EmployeesList.vue';
import ProjectList from '@/components/ProjectList.vue';


  export default {
    name: 'Home',
    components: { EmployeesList, ProjectList, AsignationList }
}
</script>

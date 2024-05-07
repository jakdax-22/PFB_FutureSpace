<template>
    <div>
        <h1>Computed Properties</h1>
        <ComputedOne/>
        <ComputedTwo/>
    </div>
</template>
<script>
import ComputedOne from './ComputedOne.vue';
import ComputedTwo from './ComputedTwo.vue';

    export default{
    name: 'ComputerProperties',
    data: () => ({

    }),
    components: { ComputedOne, ComputedTwo }
}
</script>
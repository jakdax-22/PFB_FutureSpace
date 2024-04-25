<template>
    <div>
        Hola 1
    </div>
</template>

<script>
  export default {
    name: 'ExerciseOneView',

    components: {
    
    },
    computed:{
        characters() {
            return this.$store.state.characters;
        }
    }
  }
</script>

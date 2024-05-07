<template>
  <div>
    <hello-world />
    <vue-directives/>
  </div>
</template>

<script>
import VueDirectives from '@/components/VueDirectives.vue';
import HelloWorld from '../components/HelloWorld'

  export default {
    name: 'Home',

    components: {
    HelloWorld,
    VueDirectives,
},
  }
</script>

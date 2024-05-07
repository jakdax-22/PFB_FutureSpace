<template>
  <header>
    <h1>{{ title }}</h1>
    <p>{{ message }}</p>
  </header>
</template>

<script>
  export default {
    name: 'HelloWorld',

    data: () => ({
      title: 'Hola Mundo',
      message: 'Bienvenido al curso de Vue.js'
    }),
  }
</script>

<template>
    <div>
        <h1>{{ title }}</h1>
        <div v-html="message"></div>
    </div>
</template>
<script>
    export default{
        name: 'DirectiveHtml',

        data:()=>({
            title: "Directiva v-html",
            message: '<strong>Hola desde directive html</strong>',
        })
    }
</script>
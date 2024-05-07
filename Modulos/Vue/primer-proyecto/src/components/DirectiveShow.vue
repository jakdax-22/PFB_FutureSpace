<template>
    <div>
        <h1>{{ title }}</h1>
        <div v-html="message" v-show="mostrar"></div>
        <p v-show="user.permission">El usuario tiene permisos para ver esto</p>
        <p v-show="!user.permission">El usuario no tiene permisos para ver esto</p>
    </div>
</template>
<script>
    export default{
        name: 'DirectiveHtml',

        data:()=>({
            title: "Directiva v-show",
            message: '<strong>Hola desde directive v-show</strong>',
            mostrar:true,
            user:{
                permission: true
            }
        })
    }
</script>
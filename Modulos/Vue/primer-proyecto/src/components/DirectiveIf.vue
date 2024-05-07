<template>
    <div>
        <h1>{{ title }}</h1>
        <div v-html="message" v-if="mostrar"></div>
        <h1>Uso de v-if / v-else</h1>
        <p v-if="user.permission && user.vip">El usuario tiene permisos y es VIP</p>
        <p v-else-if="!user.permission">El usuario no tiene permisos para ver esto</p>
        <p v-else>El usuario no tiene permisos para ver esto ni es VIP</p>
    </div>
</template>
<script>
    export default{
        name: 'DirectiveHtml',

        data:()=>({
            title: "Directiva v-if",
            message: '<strong>Hola desde directive v-if</strong>',
            mostrar:true,
            user:{
                permission: true,
                vip:true
            }
        })
    }
</script>
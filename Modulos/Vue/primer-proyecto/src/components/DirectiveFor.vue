<template>
    <div>
        <h1>{{ title }}</h1>
        <p v-html="message"></p>
        <h3>Lista de un array</h3>
        <ul>
            <li v-for="(color,index) of list" :key="index">{{ color }}</li>
        </ul>
        <h3>Lista de un objeto</h3>
        <ul>
            <li v-for="(item,key,index) in object_list" :key="index">{{ key }} : {{ item }}</li>
        </ul>
        <h3>Lista de un array de objetos</h3>
        <ul>
            <li v-for="(item,index) in other_list" :key="index">
                Nombre: {{ item.name }}<br>
                Apellido: {{ item.last_name }}<br>
                Nick: {{ item.nick }}
            </li>
        </ul>

    </div>
</template>
<script>
    export default{
        name: 'DirectiveFor',

        data:()=>({
            title: "Directiva v-for",
            message: '<strong>Hola desde directive v-for</strong>',
            list: ['rojo','amarillo','prueba','azul'],
            object_list: {
                name:"Enrique",
                last_name:"Iranzo",
                nick:"Jakdax_22"
            },
            other_list: [
                {
                    name:"Enrique",
                    last_name:"Iranzo",
                    nick:"Jakdax_22"
                },
                {
                    name:"Jorge",
                    last_name:"Martínez",
                    nick:"jormar"
                },
                {
                    name:"Ismael",
                    last_name:"Iranzo",
                    nick:"isma"
                }
            ]
        })
    }
</script>
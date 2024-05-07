<template>
    <div @click="upps">
        <h1>{{ title }}</h1>
        <button @click.stop="hi">Saludar</button>

    </div>
</template>
<script>
    export default{
        name: 'DirectiveComp',

        data:()=>({
            title: "Saludo para ti",
            name: '<strong>Enrique Iranzo Martínez</strong>',
            country: "Spain",
            outClick: 'No hagas clic en el botón'
        }),
        methods: {
            upps(){
                alert(this.outClick);
            },
            hi(){
                alert(`Hola mi nombre es ${this.name} y soy de ${this.country}`)
            }
        }
    }
</script>
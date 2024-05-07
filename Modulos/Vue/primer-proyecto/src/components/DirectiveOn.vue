<template>
    <div>
        <h1>{{ title }}</h1>
        <p v-html="message"></p>
        <p>{{ pelicula }}</p>
        <input @keydown="decirHola" type="text">
        <button @click.stop="comprarEntrada">Comprar entradas</button>
        <div>
            <h1>Modificando vista y modelo</h1>
            <input type="text" :value="textInput" @keyup="updateDataTextInput">
        </div>
    </div>
</template>
<script>
    export default{
        name: 'DirectiveOn',

        data:()=>({
            title: "Directiva v-on",
            message: '<strong>Hola desde directive v-on</strong>',
            welcome_text: "Bienvenido desde la data",
            entradas:5,
            pelicula: "Dora la Exploradora",
            textInput: "Valor por defecto del input text"
        }),
        methods: {
            decirHola(){
                alert(this.welcome_text);
            },
            comprarEntrada(){
                if (this.entradas > 0){
                    this.entradas--;
                    return alert ("Entrada para "+this.pelicula+" comprada")
                }
                else{
                    return alert("Ya no quedan entradas");
                }
            },
            updateDataTextInput(ev){
                this.textInput = ev.target.value;
            }

        }
    }
</script>
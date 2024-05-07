<template>
    <div>
        <h1>{{ title }}</h1>
        <div v-html="message"></div>
        <input type="text" v-model="inputText">
        <p>{{ inputText }}</p>

        <h2>Tipo checkbox</h2>
        <label>
            <input type="checkbox" v-model="checked">
            Activado
        </label>
        <h2>Películas</h2>
        <label v-for="(movie,index) in movies" :key="index">
            <input :value="movie" type="checkbox" v-model="favoritasMovies">
            {{ movie }}
        </label>
        <ul v-show="favoritasMovies.length > 0">
            <li v-for="(movie,index) of favoritasMovies" :key="index">{{ movie }}</li>
        </ul>
    </div>
</template>
<script>
    export default{
        name: 'DirectiveModel',

        data:()=>({
            title: "Directiva v-model",
            message: '<strong>Hola desde directive v-model</strong>',
            inputText: "Data del input",
            checked:false,
            movies:["Buscando a Nemo","Titanic","Toy Story"],
            favoritasMovies:[],
        })
    }
</script>
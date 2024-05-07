<template>
    <div class="container">
        <h5>Bienvenido {{ user }}</h5>
        <h1>Películas Props</h1>
        <div class="row">
            <div class="col-12 col-md-6 col-lg-4 py-3" v-for="(movie,index) of movies" :key="index">
                <MovieCom 
                :id="movie.id" :title="movie.title" :synopsis="movie.synopsis"
                :cover="movie.cover" :like="movie.like" @toggleLike="onToggleLike"/>
            </div>
        </div>
        <label>Cambiar nombre</label>
            <input :value="user.name" type="text" @change="setNameUser">
            <input :value="user.lastName" type="text" @change="setLastNameUser">
        <label for=""></label>
        {{ oldUser }}
    </div>
</template>
<script>
import MovieCom from './MovieCom.vue';
import MovieFav from './MovieFav.vue';

    export default{
        name: "PropsCom",
        
        data:() =>({
            user: {
                name:"Enrique",
                lastName:"Iranzo"
            },
            oldUser:null,
            movies:[
                {
                    id: 1,
                    title: 'Titanic',
                    synopsis: 'Durante las labores de recuperación de los restos del famoso trasatlántico Titanic, una anciana norteamericana se pone en contacto con la expedición para acudir…',
                    cover: 'https://image.tmdb.org/t/p/w185_and_h278_bestv2/zraTDtulFw2wrpyuYf646k95MNq.jpg',
                    like:false
                },
                {
                    id: 2,
                    title: 'El Rey León',
                    synopsis: 'Un remake del clásico animado de Disney de 1994 El rey león que estará dirigido por Jon Favreu. Simba (Donald Glover) es el hijo del rey de los leones, Mufasa…',
                    cover: 'https://image.tmdb.org/t/p/w185_and_h278_bestv2/3A8ca8WOBacCRujSKJ2tCVKsieQ.jpg',
                    like:false
                },
                {
                    id: 3,
                    title: 'Toy Story',
                    cover: 'https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/73DEEEC5C4139514C5E579B02CE910D148A5CD21E6583F2E42071DDA3FC3ACBE/scale?width=506&amp;aspectRatio=2.00&amp;format=webp',
                    like:false
                }
            ],
            showFav : false
        }),
        watch: {
            user:{
                handler: function (newVal, oldVal){
                    console.log(newVal,oldVal);
                },
                deep: true
            },
            'user.name':{
                handler: function (newVal, oldVal){
                    console.log(newVal,oldVal);
                },
                deep: true
            },
            'user.lastName':{
                handler: function (newVal, oldVal){
                    console.log(newVal,oldVal);
                },
                deep: true
            }
        },
        components:{
            MovieCom,
            MovieFav
        },
        methods:{
            onToggleLike(data){
                let movieLike = this.movies.find(movie => movie.id == data.id);

                movieLike.like = data.like;
                this.showFav = data.like;
            },
            sayHello(){
                alert ("Hola");
            },
            setNameUser(ev){
                this.user.name = ev.target.value;
            },
            setLastNameUser(ev){
                this.user.lastName = ev.target.value;
            }
        }
    }
</script>
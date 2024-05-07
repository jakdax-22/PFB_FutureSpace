<template>
    <div class="container">
        <h5>Bienvenido {{ user.name }} {{ user.lastName }}</h5>
        <h1>Películas App</h1>
        <div class="row">
            <div class="col-12 col-md-6 col-lg-4" v-for="(movie,index) of movies" :key="index">
                <MovieCom 
                :id="movie.id" :title="movie.title" :synopsis="movie.overview"
                :cover="movie.poster_path" :like="movie.like" @toggleLike="onToggleLike"/>
            </div>
        </div>
        <div class="row">
            <button @click="setPage(index)" class="btn m-1 col" :class="{
                'btn-light': n != page + 1,
                'btn-primary' : n = page + 1
            }" v-if="index > 0" v-for="(n,index) in total_pages" :key="index">{{ index  }}</button>
        </div>
    </div>
</template>
<script>
import MovieCom from './MovieCom.vue';
import MovieFav from './MovieFav.vue';

    export default{
        name: "MovieApp",
        
        data:() =>({
            APIKEY : "91c9577256560e66c947e762e8572d46",
            BASE_URL: "https://api.themoviedb.org/3/",
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
            showFav : false,
            page: 3,
            total_pages: 500
        }),
        components:{
    MovieCom,
    MovieFav,
},
        mounted(){
            let locationURL = new URL (window.location.href);
            this.page = locationURL.searchParams.get('page');
            this.getPopularMovies();
        },
        methods:{
            onToggleLike(data){
                let movieLike = this.movies.find(movie => movie.id == data.id);

                movieLike.like = data.like;
                this.showFav = data.like;
            },
            getPopularMovies(){
                const URL = `${this.BASE_URL}discover/movie?sort_by=popularity.desc&api_key=${this.APIKEY}&page=${this.page}`
                fetch(URL)
                    .then(response => response.json())
                    .then(({results,page})=>{
                        this.movies = results.map(m=>{
                            m.poster_path = `https://image.tmdb.org/t/p/w185_and_h278_bestv2${m.poster_path}`
                            m.like = false;
                            return m;
                        });
                    })
            },
            setPage(page){
                this.page = page;
                this.getPopularMovies();
            }
        }
    }
</script>
<template>

    <div :id="id | formatId" class="card" style="width: 18rem;">
        <img :src="cover" width="185px" height="278px" class="card-img-top">
        <div class="card-body">
            <h2 class="card title">{{ title | uppercase }}</h2>
            <p class="card-text">{{ synopsis | excerb }}</p>
            <button class="btn" :class="[className]" @click="toggleLike" v-text="like ? 'Favorito' : 'Añadir a favoritos'"></button>

        </div>
    </div>
</template>
<script>
    export default{
        name: "MovieCom",
        data:()=>({
            className: {
                'btn-like':true,
                'btn-light':false,
                myClass:true
            }
        }),
        watch:{
            like(newVal,oldVal){

            }
        },
        props:{
            id:{
                type:Number,
                required: true,
            },
            title:{
                type:String,
                required: true,
            },
            synopsis:{
                type:String,
                default:"No posee synopsis"
            },
            cover:{
                type:String,
                required:true
            },
            like:{
                type:Boolean,
                required:true,
                default () {
                    return false
                }
            }

        },
        methods:{
            toggleLike(){
                this.$emit('toggleLike',{like:!this.like,id:this.id})
                this.$parent.sayHello();

            }
        },
        filters:{
            formatId(value){
                return `movieCard-${value}`
            },
            uppercase(value){
                return value.toUpperCase();
            },
            reverse (value){
                let word = value.split('');
                return word.reverse().join('');
            },
            excerb (value){
                return value.substring(0,100) + '...'
            }
        }
    }
</script>
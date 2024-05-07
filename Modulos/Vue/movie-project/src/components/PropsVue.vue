<template>
    <div>
        <props-com/>
        <MovieCom/>
    </div>
</template>
<script>
    import MovieCom from "./MovieCom.vue";
import PropsCom from "./PropsCom.vue"
    export default{
        name: "PropsVue",

        data: ()=>({
            message:"Props"
        }),
        components: {
    PropsCom,
    MovieCom
}
    }
</script>
<template>
    <div>
        <v-row class="mt-2">
          <v-col cols="6">
            <ArtistsList/>
          </v-col>
          <v-col cols="6">
            <DiscsList/>
          </v-col>
          <v-col v-if="formArtists" cols="6">
            <FormArtist/>
          </v-col>
        </v-row>
    </div>
</template>

<script>
import ArtistsList from '@/components/ArtistsList.vue';
import DiscsList from '@/components/DiscsList.vue';
import FormArtist from '@/components/FormArtist.vue';

  export default {
    name: 'ExerciseOneView',

    components: {
    ArtistsList,
    FormArtist,
    DiscsList
},
    data: ()=>({

    }),
    computed:{
      formArtists(){
        return this.$store.state.formArtists;
      }
    }
  }
</script>

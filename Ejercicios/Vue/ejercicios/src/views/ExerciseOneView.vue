<template>
    <div>
        <v-row class="mt-2">
          <v-col cols="6">
            <ArtistsList/>
          </v-col>
          <v-col cols="6">
            <DiscsList/>
          </v-col>
          <v-col>
            <FormArtist v-if="formArtists" cols="6"/>
          </v-col>
          <v-col>
            <FormDisc v-if="formDiscs" cols="6"/>
          </v-col>
        </v-row>
    </div>
</template>

<script>
import ArtistsList from '@/components/ArtistsList.vue';
import DiscsList from '@/components/DiscsList.vue';
import FormArtist from '@/components/FormArtist.vue';
import FormDisc from '@/components/FormDisc.vue';

  export default {
    name: 'ExerciseOneView',

    components: {
    ArtistsList,
    FormArtist,
    DiscsList,
    FormDisc
},
    data: ()=>({

    }),
    computed:{
      formArtists(){
        return this.$store.state.formArtists;
      },
      formDiscs(){
        return this.$store.state.formDiscs;
      }
    }
  }
</script>

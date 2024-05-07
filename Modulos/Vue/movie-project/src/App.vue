<template>
    <router-view></router-view>
</template>

<script>
import HomeView from './views/HomeView.vue';

export default {
  name: 'App',

  data: () => ({
    //
  }),
  components:{
    HomeView,
}
};
</script>

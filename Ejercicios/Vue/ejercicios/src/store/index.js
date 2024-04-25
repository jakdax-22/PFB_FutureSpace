import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //Array de personajes
    characters:[],
    //Si está cargando o no
    loading:false,
    //Si hay error
    error:null
  },
  getters: {
  },
  mutations: {
    SET_CHARACTERS(state,characters){
      state.characters = characters;
    },
    SET_LOADING(state,loading){
      state.loading = loading;
    },
    SET_ERROR(state,error){
      state.error = error;
    }
  },
  actions: {
    //Función para hacer fetch con axios
    async fetchCharacters({ commit },query){
      //Dejo cargando en true
      commit('SET_LOADING',true);
      //Hago la petición y espero resultados
      try{
        const response = await axios.get('https://hp-api.onrender.com/api/characters');
        //Cojo 12 personajes solo
        const limitedCharacters = response.data.slice(0,12);
        //Seteo el array a lo que he sacado
        commit('SET_CHARACTERS',limitedCharacters);
      }
      //Cojo el error 
      catch(error){
        //Le doy el valor a mi error de lo que he recogido
        commit ('SET_ERROR', error.message)
      }
      //Pase lo que pase lo quito de estar cargando
      finally{
        commit('SET_LOADING', false);
      }
    }
  },
  modules: {
  }
})

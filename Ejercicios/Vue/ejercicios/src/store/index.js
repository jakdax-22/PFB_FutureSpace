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
    error:null,
    //Array de artistas
    artists:[],
    //mostrar o no mostrar form
    formArtists : false,
    //Array de discos
    discs: [],
    //Mostrar o no form de discos
    formDiscs: false,
  },
  getters: {
    //Getter de artistas
    getArtists(state){
      return state.artists;
    },
    //Getter de discos
    getDiscs(state){
      return state.discs
    }
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
    },
    SET_ARTISTS(state,artists){
      state.artists = artists;
    },
    SWITCH_FORM_ARTISTS(state){
      state.formArtists = !state.formArtists
    },
    SET_DISCS(state,discs){
      state.discs = discs;
    },
    SWITCH_FORM_DISCS(state){
      state.formDiscs = !state.formDiscs
    },

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
    },
    //Función para añadir un nuevo artista
    addArtist({commit,getters},newArtist){
      const artist = {
        name: newArtist.firstName,
        age:newArtist.age
      }
      //Consigo una copia
      const artistsCopy = getters.getArtists;
      //Meto al final lo insertado
      artistsCopy.push(artist);
      //Cambio el valor del atributo con setter
      commit('SET_ARTISTS',artistsCopy);
      //Quito el formulario
      commit('SWITCH_FORM_ARTISTS');
    },
    //Función para borrar un artista del listado 
    removeArtist({commit,getters},id){
      //Consigo una copia
      const artistsCopy = getters.getArtists;
      //Borro la posición
      artistsCopy.splice(id,1);
      //Cambio el valor del atributo con setter
      commit('SET_ARTISTS',artistsCopy);
    },
    //Función para editar artista
    editArtist({commit,getters},editedArtist){
      const artist = {
        name: editedArtist.name,
        age:editedArtist.age
      };
      //Consigo una copia
      const artistsCopy = getters.getArtists;
      //Edito la posición donde estaba
      artistsCopy[editedArtist.id] = artist;
      //Guardo los cambios
      commit('SET_ARTISTS',artistsCopy);
    },
    //Función para cambiar la visibilidad del formulario
    showFormArtists({ commit }){
      commit('SWITCH_FORM_ARTISTS');
    },
    showFormDiscs({ commit }){
      commit('SWITCH_FORM_DISCS');
    },
    //Función para añadir un nuevo disco
    addDisc({commit,getters},newDisc){
      const disc = {
        name: newDisc.name,
        artist: newDisc.artist,
        year:newDisc.year,
      }
      //Consigo una copia
      const discsCopy = getters.getDiscs;
      //Meto al final lo insertado
      discsCopy.push(disc);
      //Cambio el valor del atributo con setter
      commit('SET_DISCS',discsCopy);
      //Quito el formulario
      commit('SWITCH_FORM_DISCS');
    },
    editDisc({commit,getters},editedDisc){
      const disc = {
        name: editedDisc.name,
        artist:editedDisc.artist,
        year:editedDisc.year,
      };
      //Consigo una copia
      const discsCopy = getters.getDiscs;
      //Edito la posición donde estaba
      discsCopy[editedDisc.id] = disc;
      //Guardo los cambios
      commit('SET_DISCS',discsCopy);
    },
    removeDisc({commit,getters},id){
      //Consigo una copia
      const discsCopy = getters.getDiscs;
      //Borro la posición
      discsCopy.splice(id,1);
      //Cambio el valor del atributo con setter
      commit('SET_DISCS',discsCopy);
    },
  },
  modules: {
  },
  /*
  No es necesario hacerlo
  watch: {
    'state.artists'(newArtists) {
      const discs = this.state.discs;
      const updatedDiscs = discs.map(disc => {
        if (newArtists.find(artist => artist.name == disc.artist)){
          return disc;
        }
        else {
          return {
            ...disc,
            artist: 'Artista no encontrado'
          };
        }
      });
      this.commit('SET_DISCS',updatedDiscs)
    }
  }*/
})

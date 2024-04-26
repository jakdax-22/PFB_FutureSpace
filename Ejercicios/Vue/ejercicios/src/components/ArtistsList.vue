<template>
    <div>
        <v-card
            class="mx-auto"
            max-width="500"
        >
        <v-card-title class="d-flex justify-space-between align-center">
            <h2 class="mb-0">Listado de Artistas</h2>
            <v-btn color="primary" @click="$store.dispatch('showFormArtists')">Añadir</v-btn>
        </v-card-title>
        <v-card-text>
            <v-list>
            <v-list-item v-if="artists.length > 0">
                <v-list-item-content >
                    <div class="row justify-content-center">
                        <v-list-item-subtitle class="col cols-4">Nombre </v-list-item-subtitle>
                        <v-list-item-subtitle class="col cols-4">Edad </v-list-item-subtitle>
                        <v-list-item-subtitle class="col cols-4">Acciones </v-list-item-subtitle>
                    </div>
                </v-list-item-content>
            </v-list-item>
            <v-list-item v-else>
                <div class="d-flex justify-space-between align-center">
                    <v-list-item-subtitle>Añade un artista para empezar :) </v-list-item-subtitle>
                </div>
            </v-list-item>
            <v-list-item  v-for="(item,index) of artists" :key="index">
                <v-list-item-content >
                    <div class="row justify-content-center">
                        <v-list-item-subtitle class="col cols-4">{{ item.name }} </v-list-item-subtitle>
                        <v-list-item-subtitle class="col cols-4">{{ item.age }} </v-list-item-subtitle>
                    </div>
                </v-list-item-content>
                <v-list-item-action>
                    <div class="d-flex justify-space-between align-center col cols-4">
                        <v-btn icon @click="passParameters(item,index)">
                            <v-icon>mdi-pencil</v-icon>
                        </v-btn>
                        <v-btn icon @click="$store.dispatch('removeArtist',index)">
                            <v-icon>mdi-delete</v-icon>
                        </v-btn>
                    </div>
                </v-list-item-action>
            </v-list-item>


            </v-list>
        </v-card-text>
        </v-card>

        <v-dialog
        v-model="dialog"
        width="500px"
        >
        <v-card>
            <v-card-title>Editar Artista</v-card-title>
            <v-card-text>
                <v-form>
                    <v-text-field v-model="editedArtist.name" label="Nombre" :placeholder="editedArtist.name"></v-text-field>
                    <v-text-field v-model="editedArtist.age" label="Edad" :placeholder="editedArtist.age"></v-text-field>
                </v-form>
                <div class="d-flex justify-space-between align-center">
                    <v-btn color="primary" @click="$store.dispatch('editArtist',editedArtist);dialog=false" type="submit">Guardar</v-btn>
                    <v-btn @click="dialog=false" >Cancelar</v-btn>
                </div>
            </v-card-text>

        </v-card>
        </v-dialog>
    </div>
    
</template>
<script>
    export default{
        name:"ArtistList",
        data:()=>({
            dialog:false,
            editedArtist:{
                id:22,
                name:"plantilla",
                age:22
            }
        }),
        computed:{
            artists(){
                return this.$store.state.artists;
            },
        },
        methods:{
            passParameters(artist,id){
                this.editedArtist = {
                    id,
                    name:artist.name,
                    age:artist.age
                }
                this.dialog = true;
            }
        }
    }
</script>
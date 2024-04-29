<template>
    <div>
        <v-card
            class="mx-auto"
            max-width="500"
            color="indigo"
        >
        <v-card-title class="d-flex justify-space-between align-center">
            <h2 class="mb-0">Listado de Artistas</h2>
            <v-btn color="primary" @click="$store.dispatch('showFormArtists')">Añadir</v-btn>
        </v-card-title>
        <v-card-text>
            <v-list>
                <!--Si el array no tiene datos que haya un mensaje que te diga que pongas datos y si no, que muestre los registros-->
            <v-list-item v-if="artists.length > 0">
                <v-list-item-content >
                    <!--Cabeceras-->
                    <div class="d-flex justify-space-between align-center">
                        <v-list-item-subtitle class="col-4 font-weight-bold">Nombre </v-list-item-subtitle>
                        <v-list-item-subtitle class="col-4 font-weight-bold">Edad </v-list-item-subtitle>
                        <v-list-item-subtitle class="col-4 font-weight-bold">Acciones </v-list-item-subtitle>
                    </div>
                </v-list-item-content>
            </v-list-item>
            <v-list-item v-else>
                <div class="d-flex justify-space-between align-center">
                    <v-list-item-subtitle>Añade un artista para empezar :) </v-list-item-subtitle>
                </div>
            </v-list-item>
            <!--Por cada elemento pongo sus registros y los botones de editar y borrar-->
            <v-list-item  v-for="(item,index) of artists" :key="index">
                <v-list-item-content >
                    <div class="d-flex justify-space-between align-center">
                        <v-list-item-subtitle class="col col-4">{{ item.name }} </v-list-item-subtitle>
                        <v-list-item-subtitle class="col col-4">{{ item.age }} </v-list-item-subtitle>
                    </div>
                </v-list-item-content>
                <v-list-item-action>
                    <!--Cada botón llama a un método del store-->
                    <div class="d-flex justify-space-between align-center col cols-4">
                        <v-btn icon @click="passParameters(item,index)">
                            <v-icon color="yellow">mdi-pencil</v-icon>
                        </v-btn>
                        <v-btn icon @click="$store.dispatch('removeArtist',index)">
                            <v-icon color="red">mdi-delete</v-icon>
                        </v-btn>
                    </div>
                </v-list-item-action>
            </v-list-item>


            </v-list>
        </v-card-text>
        </v-card>

        <!--Modal de editar artista-->
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
            //Cojo el array de artistas del store
            artists(){
                return this.$store.state.artists;
            },
        },
        methods:{
            //Método para que el modal de editar tenga los parámetros del artista al que se le ha pulsado
            passParameters(artist,id){
                this.editedArtist = {
                    id,
                    name:artist.name,
                    age:artist.age
                }
                this.dialog = true;
            }
        },
    }
</script>
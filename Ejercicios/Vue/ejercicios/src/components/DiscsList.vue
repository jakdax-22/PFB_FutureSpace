<template>
    <div>
        <v-card
            class="mx-auto"
            max-width="500"
        >
        <v-card-title class="d-flex justify-space-between align-center">
            <h2 class="mb-0">Listado de Discos</h2>
            <v-btn color="primary" @click="$store.dispatch('showFormDiscs')">Añadir</v-btn>
        </v-card-title>
        <v-card-text>
            <v-list>
            <v-list-item v-if="discs.length > 0">
                <v-list-item-content >
                    <div class="d-flex justify-space-between align-center">
                        <v-list-item-subtitle class="font-weight-bold">Nombre </v-list-item-subtitle>
                        <v-list-item-subtitle class="font-weight-bold">Año </v-list-item-subtitle>
                        <v-list-item-subtitle class="font-weight-bold">Artista </v-list-item-subtitle>
                        <v-list-item-subtitle class="font-weight-bold">Acciones </v-list-item-subtitle>
                    </div>
                </v-list-item-content>
            </v-list-item>
            <v-list-item v-else>
                <div class="d-flex justify-space-between align-center">
                    <v-list-item-subtitle>Añade un disco para empezar :) </v-list-item-subtitle>
                </div>
            </v-list-item>
            <v-list-item  v-for="(item,index) of discs" :key="index">
                <v-list-item-content >
                    <div class="d-flex justify-space-between align-center">
                        <v-list-item-subtitle>{{ item.name }} </v-list-item-subtitle>
                        <v-list-item-subtitle>{{ item.year }} </v-list-item-subtitle>
                        <v-list-item-subtitle>{{ item.artist }} </v-list-item-subtitle>
                    </div>
                </v-list-item-content>
                <v-list-item-action>
                    <div class="d-flex justify-space-between align-center">
                        <v-btn icon @click="passParameters(item,index)">
                            <v-icon>mdi-pencil</v-icon>
                        </v-btn>
                        <v-btn icon @click="$store.dispatch('removeDisc',index)">
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
            <v-card-title>Editar Disco</v-card-title>
            <v-card-text>
                <v-form>
                    <v-text-field v-model="editedDisc.name" label="Nombre" :placeholder="editedDisc.name"></v-text-field>
                    <v-text-field v-model="editedDisc.year" label="Año" :placeholder="editedDisc.year"></v-text-field>
                    <v-select
                    :items="artists"
                    v-model="editedDisc.artist"
                    item-text="name"
                    density="compact"
                    label="Artista"
                    ></v-select>
                </v-form>
                <div class="d-flex justify-space-between align-center">
                    <v-btn color="primary" @click="$store.dispatch('editDisc',editedDisc);dialog=false" type="submit">Guardar</v-btn>
                    <v-btn @click="dialog=false" >Cancelar</v-btn>
                </div>
            </v-card-text>

        </v-card>
        </v-dialog>
    </div>
    
</template>
<script>
    export default{
        name:"DiscsList",
        data:()=>({
            dialog:false,
            editedDisc:{
                id:22,
                name:"plantilla",
                artist:"",
                year:22
            }
        }),
        computed:{
            discs(){
                return this.$store.state.discs;
            },
            artists(){
                return this.$store.state.artists;
            }
        },
        methods:{
            passParameters(disc,id){
                this.editedDisc = {
                    id,
                    name:disc.name,
                    year:disc.year,
                    artist:disc.artist
                }
                this.dialog = true;
            }
        },
    }
</script>
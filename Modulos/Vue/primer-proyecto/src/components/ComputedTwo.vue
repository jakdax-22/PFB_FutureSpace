<template>
    <div>
        <h2>Computed Get-Set</h2>
        {{ fullName }}
    </div>
</template>
<script>
    export default{
        name: 'ComputedOne',
        data:()=>({
            user:{
                name:"Enrique",
                lastName:"Iranzo",
                year:2001,
            }
        }),
        computed: {
            fullName:{
                get(){
                    return `${this.user.name} ${this.user.lastName}`
                },
                set(nuevoValue){
                    let[name,lastName] = nuevoValue.split("");

                    this.user.name = name
                    this.user.lastName = lastName;
                }
            },
            userAge (){
                let date = new Date();
                let currentYear = date.getFullYear();

                return currentYear - Number(this.user.year)
            }
        }
    }
</script>

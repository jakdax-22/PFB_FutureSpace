<template>
    <div>
        <p>{{ fullName }}</p>
        <p>{{ userAge }} años</p>
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
            fullName(){
                return `${this.user.name} ${this.user.lastName}`
            },
            userAge (){
                let date = new Date();
                let currentYear = date.getFullYear();

                return currentYear - Number(this.user.year)
            }
        }
    }
</script>

<template>
    <div>
        <h1>{{ title }}</h1>
        <ButtonComp>
            <template #action>
                Hola
            </template>
            <template #element>
                Probando
            </template>
        </ButtonComp>
    </div>
</template>
<script>
    import ButtonComp from '../slots/ButtonComp.vue';
    export default{
    name: 'DirectiveSlot',
    data: () => ({
        title: "Directiva slot",
        message: '<strong>Hola desde directive slot</strong>',
    }),
    components: { ButtonComp }
}
</script>
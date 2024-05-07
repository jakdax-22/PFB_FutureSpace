<template>
    <div>
        <button>
            <slot name="action">Abrir</slot>
            <slot name="element">Ventana</slot>
        </button>
    </div>
</template>
<script>
    export default{
        name: 'ButtonComp',
    }
</script>
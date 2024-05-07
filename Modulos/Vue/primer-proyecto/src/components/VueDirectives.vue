<template>
    <div>
        <h1>{{ title }}</h1>
        <div>{{ text }}</div>
        <a :href="link.href" :title="link.title">{{  link.text }}</a>
        <directive-html/>
        <DirectiveShow/>
        <DirectiveIf/>
        <DirectiveFor/>
        <DirectiveOn/>
        <DirectiveModel/>
        <DirectiveSlots/>
    </div>
</template>
<script>
import DirectiveFor from './DirectiveFor.vue';
import DirectiveHtml from './DirectiveHtml.vue';
import DirectiveIf from './DirectiveIf.vue';
import DirectiveShow from './DirectiveShow.vue'
import DirectiveOn from './DirectiveOn.vue'
import DirectiveModel from './DirectiveModel.vue';
import DirectiveSlots from './DirectiveSlots.vue';

    export default{
    name: 'VueDirectives',
    data: () => ({
        title: "Directivas de Vue",
        text: 'Texto de prueba v-text',
        link: {
            text: 'VueJS Home',
            href: 'https://vuejs.org/',
            title: 'Documentación Vue JS'
        }
    }),
    components: {
    DirectiveHtml,
    DirectiveShow,
    DirectiveIf,
    DirectiveFor,
    DirectiveOn,
    DirectiveModel,
    DirectiveSlots
}
}
</script>
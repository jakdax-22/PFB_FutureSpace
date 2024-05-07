<template>
    <div>
        <form class="form-inline md-form form-sm mt-0">
            <div class="input-group md-form form-sm form-2 pl-0 w-100">
                <div class="input-group append">
                    <span class="btn btn-danger" id="basic-text1">
                        <i class="fas fa-times"></i>
                    </span>
                </div>
                <input class="form-control my-0 py-1 green-border"
                    type="text"
                    placeholder="Buscar"
                    aria-label="Buscar" v-model="query">
                <div class="input-group-append">
                    <button class="input-group-text green">
                        <i class="fas fa-search text-grey"
                            aria-hidden="true">
                        </i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</template>
<script>
    export default{
        name: SearchComponent,
        data:()=>({
            query:''
        })
    }
</script>
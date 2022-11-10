<template>
  <div class="console">
    {{ message }}
  </div>
</template>
<script lang="ts" setup>
import {ref} from 'vue'
const message = ref('console')
</script>

<style lang="less" scoped></style>

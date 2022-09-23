---
outline: deep
---

# 引入外部模块

> 除了自带组件以外，有时我们还需要引入其他外部模块。我们以 `ant-design-vue` 为例：

## 安装

安装 `ant-design-vue`

```shell
$ pnpm install --save-dev ant-design-vue
```

## 使用

### 全局使用

```typescript
import { createApp } from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
const app = createApp(App)
app.use(Antd)
app.mount('#app')
```

### 局部使用

```vue
<script>
import { defineComponent } from 'vue'
import { Button } from 'ant-design-vue'
export default defineComponent({
  components: {
    Button,
  },
})
</script>

<template>
  <Button>text</Button>
</template>
```

:::warning 注意
如果组件有依赖样式，则需要再引入样式文件
:::

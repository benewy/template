---
outline: deep
---

# 箭头组件

> 带动画的箭头组件

## 使用

```vue
<script>
import { BasicArrow } from '/@/components/Basic/index'
import { defineComponent } from 'vue'
export default defineComponent({
  components: { BasicArrow },
})
</script>
<template>
  <div>
    <BasicArrow :expand="false" />
  </div>
</template>
```

## 参数

| 属性     | 类型        | 默认值     | 说明                     |
|--------|-----------|---------|------------------------|
| expand | `boolean` | `false` | 箭头展开状态                 |
| top    | `boolean` | `false` | 箭头默认向上                 |
| bottom | `boolean` | `false` | 箭头默认向下                 |
| inset  | `boolean` | `false` | 取消 padding/margin，用于内嵌 |


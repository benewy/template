---
outline: deep
---

# 帮助按钮组件

> 帮助按钮组件

## 使用

```vue
<script>
import { BasicHelp } from '/@/components/Basic/index'
import { defineComponent } from 'vue'
export default defineComponent({
  components: { BasicHelp },
})
</script>
<template>
  <div>
    <BasicHelp :text="['提示1', '提示2']" />
    <BasicHelp text="提示" />
  </div>
</template>
```

## 参数

| 属性        | 类型                | 默认值     | 可选值 | 说明                            |
|-----------|-------------------|---------|-----|-------------------------------|
| fontSize  | `string`          | `14px`  | -   | 字体大小                          |
| color     | `string`          | #fff    | -   | 颜色                            |
| text      | `string｜string[]` | -       | -   | 文本列表                          |
| showIndex | `boolean`         | true    | -   | 是否显示序号,在 text 为 string[]情况下生效 |
| maxWidth  | `string`          | `600px` | -   | 最大宽度                          |
| placement | `string`          | `right` | -   | 显示方向，参考 Tooltip 组件            |

## 插槽

| 名称      | 说明   |
|---------|------|
| default | 默认图标 |

---
outline: deep
---

# 权限组件

> 用于项目权限的组件，一般用于按钮级等细粒度权限管理

## 使用

```vue
<script>
import Authority from '/@/components/Authority'
import { defineComponent } from 'vue'
export default defineComponent({
  components: { Authority },
})
</script>
<template>
  <div>
    <Authority :value="RoleEnum.ADMIN">
      <a-button type="primary" block> 只有admin角色可见 </a-button>
    </Authority>
  </div>
</template>
```

## 参数

| 属性    | 类型                                    | 默认值 | 说明                   |
|-------|---------------------------------------|-----|----------------------|
| value | `RoleEnum,RoleEnum[],string,string[]` | -   | 角色信息或者权限编码。会自动区分权限模式 |

---
outline: deep
---

# 开始

## 总览

### 服务端模块的变化

在之前的开发时间紧，任务大，敏捷开发主要讲究一个效率，所以导致在 Spring 版本突飞猛进的情况下，我们的项目还是使用了 SpringBoot 2.1.x 系列，
在 SpringBoot3 的发布前夕，我们还是抽出了时间将版本进行了升级，主要考量的特点有，SpringBoot 3 一从RC到正式版，那么我们的2.7.0之前的所有版本将结束生命周期。
在那个时候，跨版本升级所带来的难度是每一个开发者不想承受的。

### 前端模块的变化

无论是 [Vue](https://cn.vuejs.org/) 还是 [Vite](https://cn.vitejs.dev/) 已经是一个非常成熟的前端开发模式，在敏捷开发中，组合式API 所带来的快乐是没有办法言语的。
 Vue 这一次和 TypeScript 的完美融合也是保证了我们可以享受到 ts 的快乐。当然从 Vue 2.6.x 升级到 Vue 3.2.x 的核心考量还是生命周期的问题，在明年的年底 Vue 2.x 将会结束生命周期，那么到时候也是一次跨版本升级。

## 版本的比较

### Spring 2.1.x 与 SpringBoot 2.7.x 之间的区别是什么？

SpringBoot 2.7.x 优化了更多开发的时候经常需要注意的小细节，将很多依赖进行了升级，修复了更多的Bug，完善了新的@AutoConfiguration 注解。它是由常用的新自动配置文件中的类`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`，
替换应该像新类中的或导入的类新注释@Configuration。

@Configuration 为方便起见，还通过、和进行@AutoConfiguration自动配置示例。这可以制作和制作的替代品。

### Vue2.6.x 与 Vue3.2.x 之间的区别是什么？

Vue 3 是 Vue 当前的最新版本。它包含了 Vue 2 的最新版本。文档请参考[ Vue 3 迁移指南](https://v3-migration.vuejs.org/)。

其他的，但大多数 Vue API 之间的大版本是共享的，所以你的 Vue 2 知识将继续在 Vue 3 中发挥作用。独有的特性，但目前已兼容至 Vue 2 且在 [Vue 2.7](https://github.com/vuejs/vue/blob/main/CHANGELOG.md#270-2022-07-01) 中可用。

一个新的性能，Vue 3 提供了更当前的包体积、更好、更好的可扩展性和更好的 TypeScript/IDE 支持。

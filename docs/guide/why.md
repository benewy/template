---
outline: deep
---

# 为什么会有这个项目?

::: tip 前言
编写项目的时候，我们更喜欢增加功能，而不是删减代码，这就导致了，
如果在公司内部的敏捷开发持续的是拉取老项目进行开发，那么我们未来可能会遇到完全无法解决的问题，
这是使用了不再维护的框架所带来的致命问题。
:::

技术总是日新月异的，每天都有新技术在web这个技术海洋中产生，所以我们要挑选出适合公司的技术来进行产生的打磨。

## 项目的设计方案

### 服务端

服务端依照了原有项目的设计。按照 [maven](https://maven.apache.org/index.html) 自带的 [聚合和继承](https://segmentfault.com/a/1190000021366568) 的架构。
只是将 [SpringBoot](https://spring.io/) 的版本进行了迁移。

Maven 的 继承和聚合 使我们可以简单的控制多模块的maven仓库。以及每一个模块其实都是单独可拆分的，那我们就可以将每一个模块进行发版并统一维护。

![maven super](/maven-super.png)

### 前端

***[monorepo](https://pnpm.io/zh/workspaces)*** 是一个对单一存储库（也称为多包存储库、多项目存储库或单体存储库）支持的前端概念。

基于这个概念，我们可以将我们在项目中基于成品UI库二次开发的组件进行统一的管理和发布，在之后的项目就可以使用npm下载这个库进行开发。

::: tip 提示
如果你正在思考如何管理 monorepo 项目，你可能还想看一下 [Bit](https://bit.dev/)。 Bit 在后台使用 pnpm，但将许多当前在由 pnpm/npm/Yarn 管理的传统工作区中手动完成的事情自动化。 有一篇关于 bit install 的文章谈到了它： Painless Monorepo Dependency Management with Bit。
:::

我非常喜欢 bit 的首页，他用一种巧妙的方式向所有人描述了什么是monorepo。

<Embed src="https://bit.dev/" width="678" height="339" />

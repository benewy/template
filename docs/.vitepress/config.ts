import { defineConfig } from 'vitepress'
import { description, discord, font, github, name, releases, twitter,} from './meta'

const version = '0.0.0'

export default defineConfig({
  lang: 'zh-CN',
  title: name,
  description,
  head: [
    ['meta', { name: 'theme-color', content: '#ffffff' }],
    ['link', { rel: 'icon', href: '/logo.svg', type: 'image/svg+xml' }],
    ['meta', { property: 'og:title', content: name }],
    ['meta', { property: 'og:description', content: description }],
    ['meta', { name: 'twitter:title', content: name }],
    ['meta', { name: 'twitter:description', content: description }],
    ['meta', { name: 'twitter:card', content: 'summary_large_image' }],
    ['link', { href: font, rel: 'stylesheet' }],
    ['link', { rel: 'mask-icon', href: '/logo.svg', color: '#ffffff' }],
  ],
  lastUpdated: true,
  markdown: {
    theme: {
      light: 'vitesse-light',
      dark: 'vitesse-dark',
    },
  },
  themeConfig: {
    logo: '/logo.svg',
    editLink: {
      pattern: 'https://github.com/elonehoo/tl-serve-list/tree/main/docs/:path',
      text: '为此页提供修改建议',
    },
    socialLinks: [
      { icon: 'twitter', link: twitter },
      { icon: 'discord', link: discord },
      { icon: 'github', link: github },
    ],
    footer: {
      message: 'Released under the GNU Lesser General Public License.',
      copyright: 'Copyright © 2022-PRESENT 杭州融惠数据科技有限公司',
    },
    nav: [
      { text: '指引', link: '/guide/' },
      { text: '前端', link: '/web/' },
      { text: '前端组件', link: '/components/' },
      { text: '后端', link: '/app/' },
      { text: '后端工具', link: '/utils/' },
      { text: '常见问题', link: '/faq/' },
      {
        text: `v${version}`,
        items: [
          {
            text: '版本发布',
            link: releases,
          },
        ],
      },
    ],
    sidebar: {
      '/':[
        {
          text:'指引',
          items:[
            {
              text:'为什么会有这个项目？',
              link:'/guide/why'
            },
            {
              text:'开始',
              link:'/guide/'
            },
            {
              text:'特点',
              link:'/guide/features'
            },
          ]
        },
        {
          text:'前端',
          items:[
            {
              text: '前端介绍',
              link: '/web/'
            },
            {
              text: 'npm script',
              link: '/web/npm-script'
            },
            {
              text: '目录说明',
              link: '/web/folder'
            },
            {
              text: '环境配置',
              link: '/web/settings'
            },
            {
              text: '路由配置',
              link: '/web/router'
            },
            {
              text: '菜单配置',
              link: '/web/menu'
            },
            {
              text: '权限配置',
              link: '/web/role'
            },
            {
              text: '联调配置',
              link: '/web/ajax'
            },
            {
              text: '组件注册',
              link: '/web/component'
            },
            {
              text: '样式',
              link: '/web/design'
            },
            {
              text: '外部模块',
              link: '/web/external'
            },
            {
              text: '构建&部署',
              link: '/web/deploy'
            },
          ]
        },
        {
          text: '深入前端',
          items:[
            {
              text: '跨域处理',
              link: '/web/cors'
            },
            {
              text: '图标',
              link: '/web/icon'
            },
          ]
        },
        {
          text: '前端组件',
          items:[
            {
              text: '组件介绍',
              link: '/components/'
            },
            {
              text: '全局组件',
              items:[
                {
                  text: 'Button 按钮',
                  link: '/components/button'
                }
              ]
            },
            {
              text:'常用组件',
              items:[
                {
                  text: 'title 标题',
                  link: '/components/basic-title'
                },
                {
                  text: 'arrow 箭头',
                  link: '/components/basic-arrow'
                },
                {
                  text: 'help 帮助按钮',
                  link: '/components/basic-help'
                },
                {
                  text: 'PageWrapper 页面包裹组件',
                  link: '/components/page-wrapper'
                },
                {
                  text: 'PageFooter 页面底部组件',
                  link: '/components/page-footer'
                },
                {
                  text: 'icon 图标',
                  link: '/components/icon'
                },
                {
                  text: 'authority 权限',
                  link: '/components/authority'
                },
                {
                  text: 'form 表单',
                  link: '/components/form'
                },
                {
                  text: 'table 表格',
                  link: '/components/table'
                },
                {
                  text: 'PopConfirmButton 按钮',
                  link: '/components/pop-confirm-button'
                },
                {
                  text: 'CollapseContainer 区域折叠卡片',
                  link: '/components/collapse-container'
                },
                {
                  text: 'ScrollContainer 滚动容器',
                  link: '/components/scroll-container'
                },
                {
                  text: 'LazyContainer 懒加载',
                  link: '/components/lazy-container'
                },
                {
                  text: 'CodeEditor 代码编辑器',
                  link: '/components/code-editor'
                },
                {
                  text: 'JsonPreview JSON预览',
                  link: '/components/json-preview'
                },
                {
                  text: 'CountDown 倒计时',
                  link: '/components/count-down'
                },
                {
                  text: 'ClickOutSide 包裹监听',
                  link: '/components/click-out-side'
                },
                {
                  text: 'CountTo 数字动画',
                  link: '/components/count-to'
                },
                {
                  text: 'Cropper 图片裁剪',
                  link: '/components/cropper'
                },
                {
                  text: 'Description 详情',
                  link: '/components/description'
                },
                {
                  text: 'Drawer 抽屉',
                  link: '/components/drawer'
                },
                {
                  text: 'Modal 弹窗',
                  link: '/components/modal'
                },
                {
                  text: 'FlowChart 流程图',
                  link: '/components/flow-chart'
                },
                {
                  text: 'Upload 文件上传',
                  link: '/components/upload'
                },
                {
                  text: 'Tree 树',
                  link: '/components/tree'
                },
                {
                  text: 'Excel Excel组件',
                  link: '/components/excel'
                },
                {
                  text: 'QrCode 二维码',
                  link: '/components/qrcode'
                },
                {
                  text: 'Markdown',
                  link: '/components/markdown'
                },
                {
                  text: 'Loading 基础加载',
                  link: '/components/basic-loading'
                },
                {
                  text: 'Tinymce 富文本',
                  link: '/components/tinymce'
                },
                {
                  text: 'Time 时间',
                  link: '/components/time'
                },
                {
                  text: 'StrengthMeter 密码校验',
                  link: '/components/strength-meter'
                },
                {
                  text: 'Verify 校验',
                  link: '/components/verify'
                },
                {
                  text: 'Transition 切换动画',
                  link: '/components/transition'
                },
                {
                  text: 'VirtualScroll 虚拟滚动',
                  link: '/components/virtual-scroll'
                },
              ]
            },
            {
              text: '函数组件',
              items:[
                {
                  text:'ContextMenu 右键菜单',
                  link: '/components/context-menu'
                },
                {
                  text:'Loading 函数式加载',
                  link: '/components/function-loading'
                },
                {
                  text:'Preview 函数式图片预览',
                  link: '/components/function-preview'
                },
              ]
            },
          ]
        },
        {
          text:'后端',
          items:[
            {
              text: '后端介绍',
              link: '/app/'
            }
          ]
        }
      ]
    }
  },
})

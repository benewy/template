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
      { text: '工具', link: '/composable/event/mouse' },
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
          ]
        }
      ]
    }
  },
})

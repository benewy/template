export interface SocialEntry {
  icon: string
  link: string
}

export interface CoreTeam {
  avatar: string
  name: string
  // required to download avatars from GitHub
  github: string
  twitter: string
  sponsor?: string
  title?: string
  org?: string
  desc?: string
  links?: SocialEntry[]
}

const createLinks = (tm: CoreTeam): CoreTeam => {
  tm.links = [
    { icon: 'github', link: `https://github.com/${tm.github}` },
    { icon: 'twitter', link: `https://twitter.com/${tm.twitter}` },
  ]
  return tm
}

const plainTeamMembers: CoreTeam[] = [
  {
    avatar: 'https://github.com/elonehoo.png',
    name: 'Elone Hoo',
    github: 'elonehoo',
    twitter: 'huchengye',
    sponsor: 'https://opencollective.com/elonehoo',
    title: '一个狂热的开源爱好者',
    desc: 'Vite的狂热追随者',
  },
  {
    avatar: 'https://github.com/wuaqing.png',
    name: 'wuaqing',
    github: 'wuaqing',
    twitter: 'wushiqingya',
    title: '左膀右臂',
    desc: '创造了很棒的协作工具',
  },
  {
    avatar: 'https://github.com/LiChen233.png',
    name: '李晨',
    github: 'LiChen233',
    twitter: 'unknown',
    title: '技术负责人，全栈开发人员',
    desc: 'simple-robot 和 generator 的作者',
  },
  {

    avatar: 'https://github.com/antfu.png',
    name: 'Anthony Fu',
    github: 'antfu',
    twitter: 'antfu7',
    sponsor: 'https://github.com/sponsors/antfu',
    title: '狂热的开源者，在 NuxtLabs 工作',
    desc: 'Vite 和 Vue 的核心团队成员',
  },
]

const teamMembers = plainTeamMembers.map(tm => createLinks(tm))

export { teamMembers }

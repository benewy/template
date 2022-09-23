# npm script

```json
"scripts": {
  // 安装依赖
  "bootstrap": "yarn install",
  // 运行项目
  "serve": "npm run dev",
  // 运行项目
  "dev": "vite",
  // 构建项目
  "build": "vite build && esno ./build/script/postBuild.ts",
  // 清空缓存后构建项目
  "build:no-cache": "yarn clean:cache && npm run build",
  // 生成打包分析，在 `Mac OS` 电脑上执行完成后会自动打开界面，在 `Window` 电脑上执行完成后需要打开 `./build/.cache/stats.html` 查看
  "report": "cross-env REPORT=true npm run build",
  // 类型检查
  "type:check": "vue-tsc --noEmit --skipLibCheck",
  // 预览打包后的内容（先打包在进行预览）
  "preview": "npm run build && vite preview",
  // 直接预览本地 dist 文件目录
  "preview:dist": "vite preview",
  // 生成 ChangeLog
  "log": "conventional-changelog -p angular -i CHANGELOG.md -s",
  // 删除缓存
  "clean:cache": "rimraf node_modules/.cache/ && rimraf node_modules/.vite",
  // 删除 node_modules (`window` 系统手动删除该目录较慢，可以使用该命令来进行删除)
  "clean:lib": "rimraf node_modules",
  // 执行 eslint 校验，并修复部分问题
  "lint:eslint": "eslint \"{src,mock}/**/*.{vue,ts,tsx}\" --fix",
  // 执行 prettier 格式化（该命令会对项目所有代码进行 prettier 格式化，请谨慎执行）
  "lint:prettier": "prettier --write --loglevel warn \"src/**/*.{js,json,tsx,css,less,scss,vue,html,md}\"",
  // 执行 stylelint 格式化
  "lint:stylelint": "stylelint --fix \"**/*.{vue,less,postcss,css,scss}\" --cache --cache-location node_modules/.cache/stylelint/",
  "lint:lint-staged": "lint-staged -c ./.husky/lintstagedrc.js",
  "lint:pretty": "pretty-quick --staged",
  // 对打包结果进行 gzip 测试
  "test:gzip": "http-server dist --cors --gzip -c-1",
  // 对打包目录进行 brotli 测试
  "test:br": "http-server dist --cors --brotli -c-1",
  // 重新安装依赖，见下方说明
  "reinstall": "rimraf pnpm-lock.yaml && rimraf package.lock.json && rimraf node_modules && npm run bootstrap",
  "install:husky": "is-ci || husky install",
  // 生成图标集，见下方说明
  "gen:icon": "esno ./build/generate/icon/index.ts",
  "postinstall": "npm run install:husky"
},
```

## 重新安装依赖

该命令会先删除 `node_modules`、`pnpm-lock.yaml`、`package.lock.json` 后再进行依赖重新安装（安装速度会明显变慢）。

## 生成图标集

该命令会生成所选择的图标集，提供给图标选择器使用。具体使用方式请查看 [图标集生成](icon)

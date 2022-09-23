---
outline: deep
---

# 联调配置

## 开发环境

如果前端应用和后端接口服务器没有运行在同一个主机上，你需要在开发环境下将接口请求代理到接口服务器。

如果是同一个主机，可以直接请求具体的接口地址。

### 配置

开发环境时候，接口地址在项目根目录下

[.env.development](https://github.com/elonehoo/tl-serve-list/blob/main/web/.env.development) 文件配置

```shell
# vite 本地跨域代理
VITE_PROXY=[["/basic-api","http://localhost:3000"]]
# 接口地址
VITE_GLOB_API_URL=/api
```

:::tip 提示
- .env 文件中的字段如果是字符串，则无需加引号，默认全部为字符串
- VITE_PROXY 不能换行
:::

### 跨域处理

如果你在 `src/api/` 下面的接口为下方代码，且 `.env.development` 文件配置如下注释，则在控制台看到的地址为 `http://localhost:3100/basic-api/login`。

由于 `/basic-api` 匹配到了设置的 `VITE_PROXY`，所以上方实际是请求 `http://localhost:3000/login`，这样同时也解决了跨域问题。（3100为项目端口号，`http://localhost:3000` 为PROXYΩ代理的目标地址）

```typescript
// .env.development
// VITE_PROXY=[["/basic-api","http://localhost:3000"]]
// VITE_GLOB_API_URL=/basic-api

enum Api {
  Login = '/login',
}

/**
 * @description: 用户登陆
 */
export function loginApi(params: LoginParams) {
  return http.request<LoginResultModel>({
    url: Api.Login,
    method: 'POST',
    params,
  })
}
```

### 没有跨域时的配置

如果没有跨域问题，可以直接忽略 **VITE_PROXY** 配置，直接将接口地址设置在 **VITE_GLOB_API_URL**

```shell
# 例如接口地址为 http://localhost:3000 则
VITE_GLOB_API_URL=http://localhost:3000
```

如果有跨域问题，将 **VITE_GLOB_API_URL** 设置为跟 **VITE_PROXY** 内其中一个数组的第一个项一致的值即可。

下方的接口地址设置为 `/basic-api`，当请求发出的时候会经过 Vite 的 proxy 代理，匹配到了我们设置的 **VITE_PROXY** 规则，将 `/basic-api` 转化为 `http://localhost:3000` 进行请求

```shell
# 例如接口地址为 http://localhost:3000 则
VITE_PROXY=[["/basic-api","http://localhost:3000"]]
# 接口地址
VITE_GLOB_API_URL=/basic-api
```

### 跨域原理解析

在 [vite.config.ts](https://github.com/elonehoo/tl-serve-list/blob/main/web/vite.config.ts) 配置文件中，提供了 server 的 proxy 功能，用于代理 API 请求。

```typescript
server: {
  proxy: {
    "/basic-api":{
      target: 'http://localhost:3000',
      changeOrigin: true,
      ws: true,
      rewrite: (path) => path.replace(new RegExp(`^/basic-api`), ''),
    }
  },
},
```

:::tip 提示
从浏览器控制台的 Network 看，请求是 `http://localhost:3000/basic-api/xxx`，这是因为 proxy 配置不会改变本地请求的 url。
:::

## 生产环境

生产环境接口地址在项目根目录下 [.env.production](https://github.com/elonehoo/tl-serve-list/blob/main/web/.env.production) 文件配置。

生产环境接口地址值需要修改 `VITE_GLOB_API_URL`，如果出现跨域问题，可以使用 `nginx` 或者后台开启 `cors` 进行处理

:::tip 打包后如何进行地址修改?

**VITE_GLOB_*** 开头的变量会在打包的时候注入 **_app.config.js** 文件内。

在 **dist/_app.config.js** 修改相应的接口地址后刷新页面即可，不需要在根据不同环境打包多次，一次打包可以用于多个不同接口环境的部署。
:::

## 接口请求

在系统中:

1. 页面交互操作；
2. 调用统一管理的 api 请求函数；
3. 使用封装的 axios.ts 发送请求；
4. 获取服务端返回数据
5. 更新 data；

接口统一存放于 [src/api/](https://github.com/elonehoo/tl-serve-list/tree/main/web/src/api) 下面管理

以登陆接口为例:

在 src/api/ 内新建模块文件，其中参数与返回值最好定义一下类型，方便校验。虽然麻烦，但是后续维护字段很方便。

:::tip 提示
类型定义文件可以抽取出去统一管理，具体参考项目
:::

```typescript
import { defHttp } from '/@/utils/http/axios'
import { LoginParams, LoginResultModel } from './model/userModel'

enum Api {
  Login = '/login',
}

export function loginApi(params: LoginParams) {
  return defHttp.request<LoginResultModel>({
    url: Api.Login,
    method: 'POST',
    params,
  })
}
```

## axios 配置

axios 请求封装存放于 [src/utils/http/axios](https://github.com/elonehoo/tl-serve-list/tree/main/web/src/utils/http/axios) 文件夹内部

除 `index.ts` 文件内容需要根据项目自行修改外，其余文件无需修改

```shell
├── Axios.ts // axios实例
├── axiosCancel.ts // axiosCancel实例，取消重复请求
├── axiosTransform.ts // 数据转换类
├── checkStatus.ts // 返回状态值校验
├── index.ts // 接口返回统一处理
```

### index.ts 配置说明

```ts
const axios = new VAxios({
  // 认证方案，例如: Bearer
  // https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication#authentication_schemes
  authenticationScheme: '',
  // 接口超时时间 单位毫秒
  timeout: 10 * 1000,
  // 接口可能会有通用的地址部分，可以统一抽取出来
  prefixUrl: prefix,
  headers: { 'Content-Type': ContentTypeEnum.JSON },
  // 数据处理方式，见下方说明
  transform,
  // 配置项，下面的选项都可以在独立的接口请求中覆盖
  requestOptions: {
    // 默认将prefix 添加到url
    joinPrefix: true,
    // 是否返回原生响应头 比如：需要获取响应头时使用该属性
    isReturnNativeResponse: false,
    // 需要对返回数据进行处理
    isTransformRequestResult: true,
    // post请求的时候添加参数到url
    joinParamsToUrl: false,
    // 格式化提交参数时间
    formatDate: true,
    // 消息提示类型
    errorMessageMode: 'message',
    // 接口地址
    apiUrl: globSetting.apiUrl,
    //  是否加入时间戳
    joinTime: true,
    // 忽略重复请求
    ignoreCancelToken: true,
  },
})
```

**transform 数据处理说明**

类型定义，见 [axiosTransform.ts](https://github.com/elonehoo/tl-serve-list/blob/main/web/src/utils/http/axios/axiosTransform.ts) 文件

```typescript
export abstract class AxiosTransform {
  /**
   * @description: 请求之前处理配置
   */
  beforeRequestHook?: (config: AxiosRequestConfig, options: RequestOptions) => AxiosRequestConfig

  /**
   * @description: 请求成功处理
   */
  transformRequestData?: (res: AxiosResponse<Result>, options: RequestOptions) => any

  /**
   * @description: 请求失败处理
   */
  requestCatch?: (e: Error) => Promise<any>

  /**
   * @description: 请求之前的拦截器
   */
  requestInterceptors?: (config: AxiosRequestConfig) => AxiosRequestConfig

  /**
   * @description: 请求之后的拦截器
   */
  responseInterceptors?: (res: AxiosResponse<any>) => AxiosResponse<any>

  /**
   * @description: 请求之前的拦截器错误处理
   */
  requestInterceptorsCatch?: (error: Error) => void

  /**
   * @description: 请求之后的拦截器错误处理
   */
  responseInterceptorsCatch?: (error: Error) => void
}
```

项目默认 transform 处理逻辑，可以根据各自项目进行处理。一般需要更改的部分为下方代码，见代码注释说明

```typescript
/**
 * @description: 数据处理，方便区分多种处理方式
 */
const transform: AxiosTransform = {
  /**
   * @description: 处理请求数据。如果数据不是预期格式，可直接抛出错误
   */
  transformRequestHook: (res: AxiosResponse<Result>, options: RequestOptions) => {
    const { t } = useI18n()
    const { isTransformResponse, isReturnNativeResponse } = options
    // 是否返回原生响应头 比如：需要获取响应头时使用该属性
    if (isReturnNativeResponse) {
      return res
    }
    // 不进行任何处理，直接返回
    // 用于页面代码可能需要直接获取code，data，message这些信息时开启
    if (!isTransformResponse) {
      return res.data
    }
    // 错误的时候返回

    const { data } = res
    if (!data) {
      // return '[HTTP] Request has no return value'
      throw new Error(t('sys.api.apiRequestFailed'))
    }
    //  这里 code，result，message为 后台统一的字段，需要在 types.ts内修改为项目自己的接口返回格式
    const { code, result, message } = data

    // 这里逻辑可以根据项目进行修改
    const hasSuccess = data && Reflect.has(data, 'code') && code === ResultEnum.SUCCESS
    if (hasSuccess) {
      return result
    }

    // 在此处根据自己项目的实际情况对不同的code执行不同的操作
    // 如果不希望中断当前请求，请return数据，否则直接抛出异常即可
    let timeoutMsg = ''
    switch (code) {
      case ResultEnum.TIMEOUT:
        timeoutMsg = t('sys.api.timeoutMessage')
      default:
        if (message) {
          timeoutMsg = message
        }
    }

    // errorMessageMode=‘modal’的时候会显示modal错误弹窗，而不是消息提示，用于一些比较重要的错误
    // errorMessageMode='none' 一般是调用时明确表示不希望自动弹出错误提示
    if (options.errorMessageMode === 'modal') {
      createErrorModal({ title: t('sys.api.errorTip'), content: timeoutMsg })
    } else if (options.errorMessageMode === 'message') {
      createMessage.error(timeoutMsg)
    }

    throw new Error(timeoutMsg || t('sys.api.apiRequestFailed'))
  },

  // 请求之前处理config
  beforeRequestHook: (config, options) => {
    const { apiUrl, joinPrefix, joinParamsToUrl, formatDate, joinTime = true } = options

    if (joinPrefix) {
      config.url = `${urlPrefix}${config.url}`
    }

    if (apiUrl && isString(apiUrl)) {
      config.url = `${apiUrl}${config.url}`
    }
    const params = config.params || {}
    if (config.method?.toUpperCase() === RequestEnum.GET) {
      if (!isString(params)) {
        // 给 get 请求加上时间戳参数，避免从缓存中拿数据。
        config.params = Object.assign(params || {}, joinTimestamp(joinTime, false))
      } else {
        // 兼容restful风格
        config.url = config.url + params + `${joinTimestamp(joinTime, true)}`
        config.params = undefined
      }
    } else {
      if (!isString(params)) {
        formatDate && formatRequestDate(params)
        config.data = params
        config.params = undefined
        if (joinParamsToUrl) {
          config.url = setObjToUrlParams(config.url as string, config.data)
        }
      } else {
        // 兼容restful风格
        config.url = config.url + params
        config.params = undefined
      }
    }
    return config
  },

  /**
   * @description: 请求拦截器处理
   */
  requestInterceptors: (config, options) => {
    // 请求之前处理config
    const token = getToken()
    if (token) {
      // jwt token
      config.headers.Authorization = options.authenticationScheme
        ? `${options.authenticationScheme} ${token}`
        : token
    }
    return config
  },

  /**
   * @description: 响应拦截器处理
   */
  responseInterceptors: (res: AxiosResponse<any>) => {
    return res
  },

  /**
   * @description: 响应错误处理
   */
  responseInterceptorsCatch: (error: any) => {
    const { t } = useI18n()
    const errorLogStore = useErrorLogStoreWithOut()
    errorLogStore.addAjaxErrorInfo(error)
    const { response, code, message, config } = error || {}
    const errorMessageMode = config?.requestOptions?.errorMessageMode || 'none'
    const msg: string = response?.data?.error?.message ?? ''
    const err: string = error?.toString?.() ?? ''
    let errMessage = ''

    try {
      if (code === 'ECONNABORTED' && message.indexOf('timeout') !== -1) {
        errMessage = t('sys.api.apiTimeoutMessage')
      }
      if (err?.includes('Network Error')) {
        errMessage = t('sys.api.networkExceptionMsg')
      }

      if (errMessage) {
        if (errorMessageMode === 'modal') {
          createErrorModal({ title: t('sys.api.errorTip'), content: errMessage })
        } else if (errorMessageMode === 'message') {
          createMessage.error(errMessage)
        }
        return Promise.reject(error)
      }
    } catch (error) {
      throw new Error(error)
    }

    checkStatus(error?.response?.status, msg, errorMessageMode)
    return Promise.reject(error)
  },
}
```

### 更改参数格式

项目接口默认为 `Json` 参数格式，即 `headers: { 'Content-Type': ContentTypeEnum.JSON }`,

如果需要更改为 `form-data` 格式，更改 headers 的 `Content-Type` 为 `ContentTypeEnum.FORM_URLENCODED` 即可

### 多个接口地址

当项目中需要用到多个接口地址时, 可以在 [src/utils/http/axios/index.ts](https://github.com/elonehoo/tl-serve-list/blob/main/web/src/utils/http/axios/index.ts) 导出多个 axios 实例

```typescript
// 目前只导出一个默认实例，接口地址对应的是环境变量中的 VITE_GLOB_API_URL 接口地址
export const defHttp = createAxios()

// 需要有其他接口地址的可以在后面添加

// other api url
export const otherHttp = createAxios({
  requestOptions: {
    apiUrl: 'xxx',
  },
})
```

### 删除请求 URL 携带的时间戳参数

如果不需要 url 上面默认携带的时间戳参数 ?_t=xxx

```typescript
const axios = new VAxios({
  requestOptions: {
    // 是否加入时间戳
    joinTime: false,
  },
})
```

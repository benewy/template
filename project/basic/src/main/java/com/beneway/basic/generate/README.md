# MyBatis-Plus 代码自动生成器

- 注意

该生成器基于 `basic` 模块中的 `application-basic-dev.yml` 文件，将读取的是 `druid` 的配置信息，用于生成`controller,entity,service,serviceImpl,mapper,xml`等包及相关文件。

已过滤表前缀为`access`,`log`,`sys`的数据库表

## 生成器

### Using

> 该生成器位于`basic`模块的`generate`包下，只需要执行`InteractionGenerate`类的`main`方法，然后在控制台输入作者名和需要生成的表名，若需要生成所有表，则输入 all 即可

```java
public static void main(String[] args) {
  // 生成所有代码模板
  //在控制台中输入作者和即将要生成的表名
  generatorAll();
}
```

### Out

> Controller：默认生成到web模块的`controller`包下
>
> Entity,Service,ServiceImpl,Mapper,XML：默认生成到basic模块的`basic`包下

## 生成目录

### web模块

```shell
.
├── web # web模块
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── com.beneway.web
│   │   │   │   │   ├── controller
│   │   │   │   │   │   ├── 表名 # 存放对该表名的前端控制器
...
```

### basic模块

```shell
.
├── basic # 基础模块相关
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── com.beneway.basic
│   │   │   │   │   ├── 表名
│   │   │   │   │   │   ├── dao # 数据访问
│   │   │   │   │   │   ├── entity #实体类
│   │   │   │   │   │   │   └── po # 持久对象
│   │   │   │   │   │   ├── mapper # 对象持久化映射层
│   │   │   │   │   │   ├── service # 服务层
│   │   │   │   │   │   │   └── impl # 服务实现层
...
```

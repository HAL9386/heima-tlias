# 1 继承

## 1.1 pom配置

![](https://internal-api-drive-stream.feishu.cn/space/api/box/stream/download/preview/VvpibdCGoo1I9txRxLQc0QSbn8g/?preview_type=16)

1. 父工程`tlias-parent`的 `pom.xml` 文件配置如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.8</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.itheima</groupId>
    <artifactId>tlias-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

**Maven打包方式：**

- jar：普通模块打包，springboot项目基本都是jar包（内嵌tomcat运行）

- war：普通web程序打包，需要部署在外部的tomcat服务器中运行

- pom：父工程或聚合工程，该模块不写代码，仅进行依赖管理
2. **在子工程（**`tlias-pojo`****、****`tlias-utils`****、****`tlias-web-management`**）的pom.xml文件中，配置继承关系。**

```xml
<parent>
    <groupId>com.itheima</groupId>
    <artifactId>tlias-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <relativePath>../tlias-parent/pom.xml</relativePath>
</parent>

<artifactId>tlias-utils</artifactId>
<version>1.0-SNAPSHOT</version>
```

**注意：**

- 在子工程中，配置了继承关系之后，坐标中的groupId是可以省略的，因为会自动继承父工程的 。

- relativePath指定父工程的pom文件的相对位置（如果不指定，将从本地仓库/远程仓库查找该工程）。
  
  - ../ 代表的上一级目录
3. **在父工程中配置各个工程共有的依赖（子工程会自动继承父工程的依赖）。**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.8</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.itheima</groupId>
    <artifactId>tlias-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <version>3.2.8</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
        </dependency>
    </dependencies>

</project>
```

> **工程结构说明:**
> 
> - 我们当前的项目结构为：
>   
>   - `tlias-parent`
>   
>   - `tlias-pojo`
>   
>   - `tlias-utils`
>   
>   - `tlias-web-management`
> 
> 因为我们是项目开发完毕之后，给大家基于现有项目拆分的各个模块，`tlias-web-management` 已经存在了，然后再创建各个模块与父工程，所以父工程与模块之间是平级的。

> **工程结构说明:**
> 
> - 而实际项目中，可能还会见到下面的工程结构：
>   
>   - `tlias-parent`
>     
>     - `tlias-pojo`
>     
>     - `tlias-utils`
>     
>     - `tlias-web-management`
> 
> 而在真实的企业开发中，都是先设计好模块之后，再开始创建模块，开发项目。 那此时呢，一般都会先创建父工程 tlias-parent，然后将创建的各个子模块，都放在父工程parent下面。 这样层级结构会更加清晰一些。

## 1.2 版本锁定

**问题：如果项目拆分的模块比较多，每一次更换版本，我们都得找到这个项目中的每一个模块，一个一个的更改。 很容易就会出现，遗漏掉一个模块，忘记更换版本的情况。**

那我们又该如何来解决这个问题，如何来统一管理各个依赖的版本呢？

- 答案：Maven的版本锁定功能。

### 1.2.1 pom配置

在maven中，可以在父工程的pom文件中通过 `<dependencyManagement>` 来统一管理依赖版本。

- 父工程：

```xml
<!--统一管理依赖版本-->
<dependencyManagement>
    <dependencies>
        <!--JWT令牌-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

- 子工程：

```xml
<dependencies>
    <!--JWT令牌-->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
    </dependency>
</dependencies>
```

> **注意：**
> 
> - 在父工程中所配置的 `<dependencyManagement>` 只能统一管理依赖版本，并不会将这个依赖直接引入进来。 这点和 `<dependencies>` 是不同的。
> 
> - 子工程要使用这个依赖，还是需要引入的，只是此时就无需指定 `<version>` 版本号了，父工程统一管理。变更依赖版本，只需在父工程中统一变更。

### 1.2.2 属性配置

![](https://internal-api-drive-stream.feishu.cn/space/api/box/stream/download/preview/IILSbiOlfoGXvgxEgoUcxYbqnWy/?preview_type=16)

具体语法为：

1). 自定义属性

```xml
<properties>
    <lombok.version>1.18.34</lombok.version>
</properties>
```

2). 引用属性

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>${lombok.version}</version>
</dependency>
```

> **面试题：** **`<dependencyManagement>`** **与** **`<dependencies>`** **的区别是什么?**
> 
> - `<dependencies>` 是直接依赖，在父工程配置了依赖，子工程会直接继承下来。
> 
> - `<dependencyManagement>` 是统一管理依赖版本，不会直接依赖，还需要在子工程中引入所需依赖(无需指定版本)

# 2 聚合

此时，`tlias-web-management` 模块的父工程是 `tlias-parent`，该模块又依赖了 `tlias-pojo`、`tlias-utils` 模块。 那此时，我们要想将 `tlias-web-management` 模块打包，是比较繁琐的。因为在进行项目打包时，maven会从本地仓库中来查找 `tlias-parent` 父工程，以及它所依赖的模块 `tlias-pojo`、`tlias-utils`，而本地仓库目前是没有这几个依赖的。

所以，我们再打包`tlias-web-management` 模块前，需要将 `tlias-parent`、`tlias-pojo`、`tlias-utils` 分别执行install生命周期安装到maven的本地仓库，然后再针对于 `tlias-web-management` 模块执行package进行打包操作。

那此时，大家试想一下，如果开发一个大型项目，拆分的模块很多，模块之间的依赖关系错综复杂，那此时要进行项目的打包、安装操作，是非常繁琐的。 而我们接下来，要讲解的maven的聚合就是来解决这个问题的，通过maven的聚合就可以轻松实现项目的一键构建（清理、编译、测试、打包、安装等）。

- **聚合**：将多个模块组织成一个整体，同时进行项目的构建。

- **聚合工程**：一个不具有业务功能的“空”工程（有且仅有一个pom文件） 【PS：一般来说，继承关系中的父工程与聚合关系中的聚合工程是同一个】

- **作用**：快速构建项目（无需根据依赖关系手动构建，直接在聚合工程上构建即可）

在maven中，我们可以在聚合工程中通过 `<moudules>` 设置当前聚合工程所包含的子模块的名称。我们可以在 `tlias-parent`中，添加如下配置，来指定当前聚合工程，需要聚合的模块：

```xml
<!-- 聚合其他模块 -->
<modules>
    <module>../tlias-pojo</module>
    <module>../tlias-utils</module>
    <module>../tlias-web-management</module>
</modules>
```

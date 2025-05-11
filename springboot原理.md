# 1 配置优先级

**五种配置方式的优先级：** 命令行参数（program arguments） > 系统属性参数（VM options） > properties参数 > yml参数 > yaml参数

```java
java -Dserver.port=9000 -jar XXXXX.jar --server.port=10010
```

# 2 bean

## 2.1 bean作用域

- IOC容器中的bean默认使用的作用域：singleton (单例)

- 默认singleton的bean，在容器启动时被创建，可以使用@Lazy注解来延迟初始化(延迟到第一次使用时)

| 作用域         | 说明                        |
| ----------- | ------------------------- |
| singleton   | 容器内同名称的bean只有一个实例（单例）（默认） |
| prototype   | 每次使用该bean时会创建新的实例（非单例）    |
| request     | 每个请求范围内会创建新的实例（web环境中，了解） |
| session     | 每个会话范围内会创建新的实例（web环境中，了解） |
| application | 每个应用范围内会创建新的实例（web环境中，了解） |

## 2.2 第三方bean

无法使用 `@Component` 及其衍生注解来声明bean，此时就需要使用`@Bean`注解来声明bean 。

1. 在启动类中直接声明这个Bean。比如：我们可以将我们之前使用的阿里云OSS操作的工具类，基于@Bean注解的方式来声明Bean。

```java
import com.itheima.utils.AliyunOSSOperator;
import com.itheima.utils.AliyunOSSProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableScheduling
@SpringBootApplication
public class TliasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

    @Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties ossProperties) {
        return new AliyunOSSOperator(ossProperties);
    }
}
```

2. 若要管理的第三方 bean 对象，建议对这些bean进行集中分类配置，可以通过 `@Configuration` 注解声明一个配置类。【推荐】

```java
package com.itheima.config;

import com.itheima.utils.AliyunOSSOperator;
import com.itheima.utils.AliyunOSSProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSConfig {
    @Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties ossProperties) {
        return new AliyunOSSOperator(ossProperties);
    }
}
```

# 3 SpringBoot原理

## 3.1 起步依赖

当我们引入了 spring-boot-starter-web 之后，maven会通过**依赖传递**特性，将web开发所需的常见依赖都传递下来。

## 3.2 自动配置

SpringBoot项目中的`@SpringBootApplication`注解，具有包扫描的作用，但是它只会扫描启动类所在的当前包以及子包。

**那么如何解决以上问题的呢？**

- 方案1：`@ComponentScan` 组件扫描

- 方案2：`@Import` 导入（使用`@Import`导入的类会被Spring加载到IOC容器中）

### 3.2.1 方案一

`@ComponentScan`组件扫描

```java
@SpringBootApplication
@ComponentScan({"com.itheima","com.example"}) //指定要扫描的包
public class SpringbootWebConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebConfigApplication.class, args);
    }
}
```

**SpringBoot中并没有采用以上这种方案。**

### 3.2.2 方案二

@Import导入

导入形式主要有以下几种：

1. 导入普通类

2. 导入配置类

3. 导入ImportSelector接口实现类

#### 3.2.2.1 使用@Import导入普通类

```java
@Import(TokenParser.class) //导入的类会被Spring加载到IOC容器中
@SpringBootApplication
public class SpringbootWebConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebConfigApplication.class, args);
    }
}
```

#### 3.2.2.2 使用@Import导入配置类

- 配置类

```java
@Configuration
public class HeaderConfig {
    @Bean
    public HeaderParser headerParser(){
        return new HeaderParser();
    }

    @Bean
    public HeaderGenerator headerGenerator(){
        return new HeaderGenerator();
    }
}
```

- 启动类

```java
@Import(HeaderConfig.class) //导入配置类
@SpringBootApplication
public class SpringbootWebConfig2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebConfig2Application.class, args);
    }
}
```

#### 3.2.2.3 使用@Import导入ImportSelector接口实现类

- ImportSelector接口实现类

```java
public class MyImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //返回值字符串数组（数组中封装了全限定名称的类）
        return new String[]{"com.example.HeaderConfig"};
    }
}
```

- 启动类

```java
@Import(MyImportSelector.class) //导入ImportSelector接口实现类
@SpringBootApplication
public class SpringbootWebConfig2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebConfig2Application.class, args);
    }
}
```

#### 3.2.2.4 使用第三方依赖提供的 @EnableXxxxx注解

> 思考：如果基于以上方式完成自动配置，当要引入一个第三方依赖时，是不是还要知道第三方依赖中有哪些配置类和哪些Bean对象？
> 
> - 答案：是的。 （对程序员来讲，很不友好，而且比较繁琐）
> 
> 思考：当我们要使用第三方依赖，依赖中到底有哪些bean和配置类，谁最清楚？
> 
> - 答案：第三方依赖自身最清楚。
> 
> **结论：我们不用自己指定要导入哪些bean对象和配置类了，让第三方依赖它自己来指定。**
> 
> 怎么让第三方依赖自己指定bean对象和配置类？
> 
> - 比较常见的方案就是第三方依赖给我们提供一个注解，这个注解一般都以@EnableXxxx开头的注解，注解中封装的就是@Import注解

- 第三方依赖中提供的注解

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyImportSelector.class)//指定要导入哪些bean对象或配置类
public @interface EnableHeaderConfig { 
}
```

- 在使用时只需在启动类上加上@EnableXxxxx注解即可

```java
@EnableHeaderConfig  //使用第三方依赖提供的Enable开头的注解
@SpringBootApplication
public class SpringbootWebConfig2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebConfig2Application.class, args);
    }
}
```

## 3.3 自动配置的核心

自动配置的核心就在@SpringBootApplication注解上，SpringBootApplication这个注解底层包含了3个注解，分别是：

- @SpringBootConfiguration

- @ComponentScan

- @EnableAutoConfiguration

@EnableAutoConfiguration这个注解才是自动配置的核心。

- 它封装了一个@Import注解，Import注解里面指定了一个ImportSelector接口的实现类。

- 在这个实现类中，重写了ImportSelector接口中的selectImports()方法。

- 而selectImports()方法中会去读取两份配置文件，并将配置文件中定义的配置类做为selectImports()方法的返回值返回，返回值代表的就是需要将哪些类交给Spring的IOC容器进行管理。

- 那么所有自动配置类的中声明的bean都会加载到Spring的IOC容器中吗? 其实并不会，因为这些配置类中在声明bean时，通常都会添加@Conditional开头的注解，这个注解就是进行条件装配。而Spring会根据Conditional注解有选择性的进行bean的创建。

- @Enable 开头的注解底层，它就封装了一个注解 import 注解，它里面指定了一个类，是 ImportSelector 接口的实现类。在实现类当中，我们需要去实现 ImportSelector 接口当中的一个方法 selectImports 这个方法。这个方法的返回值代表的就是我需要将哪些类交给 spring 的 IOC容器进行管理。

- 此时它会去读取两份配置文件，一份儿是 spring.factories（spring版本3.0以下），另外一份儿是 autoConfiguration.imports（spring版本3.0以上）。而在 autoConfiguration.imports 这份儿文件当中，它就会去配置大量的自动配置的类。

- 而前面我们也提到过这些所有的自动配置类当中，所有的 bean都会加载到 spring 的 IOC 容器当中吗？其实并不会，因为这些配置类当中，在声明 bean 的时候，通常会加上这么一类@Conditional 开头的注解。这个注解就是进行条件装配。所以SpringBoot非常的智能，它会根据 @Conditional 注解来进行条件装配。只有条件成立，它才会声明这个bean，才会将这个 bean 交给 IOC 容器管理。

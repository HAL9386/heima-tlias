# 项目名称：TLIAS Web Management

## 项目简介
TLIAS Web Management 是一个基于 Spring Boot + MyBatis 的教务管理系统，支持班级、部门、员工、学生等多维度数据的管理，具备完善的权限认证、日志审计、文件上传、分页查询等功能，适合教学机构或企业内部管理使用。

## 主要功能
- 员工、部门、班级、学生等基础信息的增删改查
- 登录认证与 JWT 无状态鉴权
- 操作日志自动记录与查询
- 文件上传与下载
- 分页与条件查询
- 事务管理与异常处理
- 支持多种会话管理方式（Session、Token、JWT）

## 项目架构设计
- **分层结构**：
  - Controller 层：处理前端请求，参数校验与响应封装
  - Service 层：业务逻辑处理，事务控制
  - Mapper 层：MyBatis 持久层，动态 SQL 支持
  - Pojo 层：实体类与参数对象
  - Utils 层：通用工具类（如 JWT 工具、当前用户工具）
- **模块划分**：
  - tlias-parent：父工程，统一依赖与版本管理
  - tlias-pojo：实体类与参数对象
  - tlias-utils：工具类模块
  - tlias-web-management：核心业务模块

## 技术栈
- Spring Boot 3.x
- MyBatis + PageHelper
- Lombok
- JWT（jjwt）
- Logback（日志）
- Maven 多模块聚合
- 前端（可扩展）：Vue + Vue Router

## 项目亮点
- **自动化日志记录**：基于 AOP 注解自动记录操作日志，便于审计与追踪。
- **JWT 无状态认证**：支持分布式、前后端分离场景，安全性高。
- **动态 SQL 与分页查询**：MyBatis 动态 SQL，支持复杂条件组合与高效分页。
- **事务管理**：Spring 声明式事务，保证数据一致性。
- **模块化设计**：Maven 多模块聚合，便于扩展与维护。
- **异常全局处理**：统一异常捕获与友好响应。

## 项目难点
- AOP 日志切面与注解的灵活扩展
- JWT 的安全性设计与失效处理
- 复杂业务下的事务一致性与回滚
- MyBatis 动态 SQL 的高效与可维护性
- 多模块依赖与版本统一管理

## 快速启动
1. 克隆项目并导入 IDEA
2. 配置数据库连接与相关参数
3. 执行 `mvn clean install` 构建所有模块
4. 启动 `tlias-web-management` 主模块
5. 访问接口文档或前端页面进行功能体验

## 目录结构
- tlias-parent：父工程
- tlias-pojo：实体类
- tlias-utils：工具类
- tlias-web-management：业务主模块

## 其他说明
- 日志配置详见 `logback.xml`
- 详细 SQL 与表结构见 `后端.md`
- 支持扩展前端（Vue）与更多业务模块

---
如需二次开发或部署，建议详细阅读各模块下的 README 及注释。

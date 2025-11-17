# 跨鲸电商ERP 电商ERP系统 电商 ERP

## 项目简介

CrossWhale ERP(跨鲸电商ERP)是一个基于SpringCloud Alibaba技术栈构建的现代化电商ERP系统，提供完整的商品管理、采购销售、库存管理、系统管理等功能模块。系统采用微服务架构，支持高并发、高可用、可扩展的业务场景。

## 技术栈

### 核心技术
- **框架**: Spring Boot 3.2.10
- **微服务**: Spring Cloud 2023.0.0
- **服务治理**: Spring Cloud Alibaba 2023.0.1.0
- **服务注册与发现**: Nacos
- **服务调用**: OpenFeign
- **负载均衡**: Spring Cloud LoadBalancer
- **配置中心**: Nacos Config
- **API网关**: Spring Cloud Gateway
- **数据库**: MySQL
- **ORM框架**: MyBatis-Plus
- **缓存**: Redis
- **文件存储**: MinIO

### 开发工具
- **构建工具**: Maven
- **JDK版本**: JDK 17
- **工具库**: Hutool、Apache Commons Lang

## 系统架构

```
┌─────────────────────────────────────────────────────────────┐
│                        前端应用                             │
└─────────────────────┬───────────────────────────────────────┘
                      │
┌─────────────────────▼───────────────────────────────────────┐
│                    API网关 (Gateway)                        │
└─────────────────────┬───────────────────────────────────────┘
                      │
┌─────────────────────▼───────────────────────────────────────┐
│                    微服务集群                               │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │  商品服务    │  │  采购销售服务 │  │   系统管理服务       │ │
│  │   Goods     │  │Purchase-Sales│  │     System         │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
│                                                             │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │  认证授权服务│  │   通用模块   │  │    Web服务           │ │
│  │    OAuth    │  │   Common    │  │      Web           │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────┬───────────────────────────────────────┘
                      │
┌─────────────────────▼───────────────────────────────────────┐
│                    基础设施                                 │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │   Nacos     │  │    MySQL    │  │      Redis          │ │
│  │ (注册/配置)  │  │   (数据库)   │  │     (缓存)          │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
│                                                             │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │                      MinIO                              │ │
│  │                    (文件存储)                             │ │
│  └─────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

## 模块说明

### 1. smp-visualization-common (通用模块)
- **smp-visualization-common-commonality**: 通用工具类、常量、异常处理等
- **smp-visualization-common-interceptor**: 拦截器相关功能
- **smp-visualization-common-minio**: MinIO文件存储相关功能
- **smp-visualization-common-mybatis**: MyBatis-Plus通用配置和BaseMapper
- **smp-visualization-common-serializer**: 序列化相关功能

### 2. smp-visualization-gateway (网关服务)
- 提供API网关功能
- 实现路由转发、负载均衡、权限验证等

### 3. smp-visualization-goods (商品服务)
- 商品信息管理
- 商品分类管理
- 商品属性管理
- 商品库存管理

### 4. smp-visualization-oauth (认证授权服务)
- 用户认证与授权
- JWT令牌管理
- 权限控制

### 5. smp-visualization-system (系统管理服务)
- 用户管理
- 角色管理
- 菜单管理
- 部门管理

### 6. smp-visualization-purchase-sales (采购销售服务)
- 请购单管理
- 采购单管理
- 销售单管理
- 供应商管理

### 7. smp-visualization-web (Web服务)
- 提供Web界面访问
- 整合各微服务功能

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+
- Nacos 2.0+

### 本地开发环境搭建

1. **克隆项目**
   ```bash
   git clone [https://gitee.com/zhang-1098416956/spring-cloud-alibaba-v2023.git]
   cd smp-visualization-cloud
   ```

2. **安装依赖**
   ```bash
   mvn clean install
   ```

3. **配置Nacos**
   - 启动Nacos服务器
   - 在Nacos中创建必要的配置文件

4. **配置数据库**
   - 创建MySQL数据库
   - 执行SQL脚本初始化表结构

5. **启动服务**
   - 按照依赖关系依次启动各个微服务
   - 启动顺序：Common → Nacos → OAuth → System → Goods → Purchase-Sales → Gateway → Web

### 服务端口配置

| 服务名称 | 端口 | 说明 |
|---------|------|------|
| Gateway | 8080 | API网关 |
| OAuth | 8081 | 认证授权服务 |
| System | 8082 | 系统管理服务 |
| Goods | 8083 | 商品服务 |
| Purchase-Sales | 8084 | 采购销售服务 |
| Web | 8085 | Web服务 |

## 项目特性

- **微服务架构**: 基于SpringCloud Alibaba构建的分布式系统
- **服务治理**: 使用Nacos实现服务注册、发现和配置管理
- **统一认证**: 基于JWT的认证授权机制
- **数据一致性**: 分布式事务解决方案
- **高性能**: Redis缓存提升系统性能
- **高可用**: 服务熔断、限流、降级机制
- **易扩展**: 模块化设计，便于功能扩展

## 开发规范

### 代码规范
- 遵循阿里巴巴Java开发规范
- 使用统一的代码格式化配置
- 提交代码前必须通过代码检查

### API设计规范
- RESTful API设计风格
- 统一的响应格式
- 完善的API文档

### 数据库设计规范
- 统一的命名规范
- 必要的索引优化
- 完善的注释说明

## 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 许可证

本项目采用 [MIT](LICENSE) 许可证。

## 联系方式

- 项目维护者: [TheSunshine]
- 邮箱: [1098416956@qq.com]


## 更新日志

### v1.0.0 (2025-11-11)
- 初始版本发布
- 实现基础的商品管理功能
- 实现系统管理功能
- 完成微服务架构搭建

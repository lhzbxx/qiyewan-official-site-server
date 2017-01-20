# qiyewan-site-server

> 基于 SpringBoot 框架搭建，企业湾·官网的 RESTful 接口的服务。

### 基本信息

__端口号__ 8081

### 运行环境

基本的服务依赖是 MariaDB + Redis + RabbitMQ。

MariaDB 作为持久化关系型数据库，存储了基本所有的数据，其中所有的表结构都可以在 */domain 中查看。

Redis 作为键值型缓存数据库，主要作中间层的缓存，例如产品目录和用户令牌等数据。

RabbitMQ 作为消息队列，主要用于短信发送、订单处理等异步逻辑的操作。

### 启动

1. 在以下两种方法中根据运行的操作系统**任选一个**启动基础服务：
   1.    在 Mac 或 *nix 系统中，可以直接使用 docker-compose 工具在根目录下运行：

         ```bash
         docker-compose up -d
         ```

         即可。

   2.    在 Windows 系统下，可以单一拉取所有的默认镜像，也可以配置一份额外的 docker-compose.yaml 文件。

            我常用的一份配置是：

         ```yaml
            version: '2'

            services:

              mariadb:
                build: ./maria
                volumes:
         - ./maria/data:/var/lib/mysql
         ```
          ports:
              - "3306:3306"
          environment:
              MYSQL_DATABASE: db
              MYSQL_USER: lhzbxx
              MYSQL_PASSWORD: 5120309188
              MYSQL_ROOT_PASSWORD: F1203005_5120309188_LuHao
          container_name:
              qiyewan_MariaDB

        redis:
          build: ./redis
          volumes:
              - ./redis/data:/data
          ports:
              - "6379:6379"
          container_name:
              qiyewan_Redis
       
        rabbitmq:
          build: ./rabbit
          ports:
              - "5672:5672"
              - "15671:15671"
              - "15672:15672"
          privileged: true
          environment:
              RABBITMQ_DEFAULT_USER: guest
              RABBITMQ_DEFAULT_PASS: guest
          container_name:
              qiyewan_RabbitMQ
      ```

      ```

2. 接下来就是运行主服务，首先要明确的一点是，整个服务分为两种环境，一个是 **dev** 开发环境，另一种是 **prod** 生产环境。在运行本地服务或者是进行测试时候请务必使用 **dev** 开发环境（默认为开发环境）。

   1. 如果在 IDE 环境中运行，在 IDE 自动导入 pom.xml 中的所有依赖后，在 **active profile** 中填写 **dev** 即可。

   2. 如果在非 IDE 环境中运行，则安装好 **Maven3** 之后，直接运行下述命令：

      ```bash
      mvn run spring-boot:run -Drun.profiles=dev
      ```

      即可。
# mongotest
springmvc+maven+mongodb
这个demo项目演示了springMVC中集成MongoDB的基本操作，以及利用MongoDB做文件的存取操作

配置步骤如下：
一、搭建springmvc项目框架（常用操作，故此省略）
二、引入MongoDB的jar包
    <!--MongoDB驱动包-->
    <dependency>
      <groupId>org.mongodb</groupId>
      <artifactId>mongo-java-driver</artifactId>
      <version>3.1.0</version>
      </dependency>
      <!--MongoDB核心包-->
      <dependency>
      <groupId>org.springframework.data</groupId>
      <artifactId>spring-data-mongodb</artifactId>
      <version>1.8.2.RELEASE</version>
      </dependency>

三、配置xml和mongodb.properties
四、编写实体映射类和相关的接口类

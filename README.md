# workflow
### springboot2 + acticiti6 + 流程设计器 + thymeleaf3 + mysql5.7 + jdk1.8-x64 + druid
- 这是一个单个maven的demo工程

- 步骤：
1. 下载源码
2. 修改```application.yml```中的数据库连接配置
3. 修改```resources/logback-spring.xml```中第11行的保存日志文件的路径
4. 项目中引入的*-boot2的jar包均是定制开发的，jar文件在项目data目录下，自己弄到自己仓库去。
5. 项目访问路径为localhost:8999/wf, 也可以自己去配置，自己配置需要修改applica.yml，pom.xml，webapp\scripts\app-cfg.js这三个对应的地方
6. ```mvn clean install -Dmaven.test.skip=true```
7. 启动main方法
8. 浏览器访问```localhost:8999/wf```

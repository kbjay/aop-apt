# aop-apt
利用apt实现bindView功能（参考butterKnife）

项目介绍：<br><br>
  App：测试module<br>
  apt-annotation:java lib module , 声明bindView注解<br>
  apt-processor:java lib module ，继承AbstractProcessor，根据注解利用javaPoet生成.java文件<br>
  apt-api:android lib ，对外暴露的bind方法：利用反射找到生成的类的bind方法并调用<br>
<br><br>
关键点：<br><br>
  apt概念,参考https://juejin.im/entry/57ad3fa47db2a200540c9251<br>
  注解基础知识<br>
  反射基础知识<br>
  Processor相关api<br>
  javaPoet：方块公司开源的用于生成.java文件，具体api可以参考https://juejin.im/post/584d4b5b0ce463005c5dc444<br>
  autoService:google开源的用于自动生成apt扫描注解时需要的meta文件<br>
  gradle中的annotationProcessor闭包：打包apk的时候不会把使用annotationProcessor声明的project打进去<br>
  
  

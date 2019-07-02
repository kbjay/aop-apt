# aop-apt
利用apt实现bindView功能（参考butterKnife）

项目介绍：
  App：测试module
  apt-annotation:java lib module , 声明bindView注解
  apt-processor:java lib module ，继承AbstractProcessor，根据注解利用javaPoet生成.java文件
  apt-api:android lib ，对外暴露的bind方法：利用反射找到生成的类的bind方法并调用

关键点：
  注解基础知识
  反射基础知识
  Processor相关api
  javaPoet：方块公司开源的用于生成.java文件
  autoService:google开源的用于自动生成apt扫描注解时需要的meta文件
  gradle中的annotationProcessor闭包：打包apk的时候不会把使用annotationProcessor声明的project打进去
  
  

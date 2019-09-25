# Java模板项目
这是一个基于Maven的基础项目, 主要用于练习Java 单元测试，TDD 等使用。

## 克隆和运行
* 克隆本项目
* 在命令行下执行 `mvn test`，或者导入到 IDE，运行测试，看到测试运行通过
* 在命令行下执行 `mvn clean compile assembly:single` ,可以在`${basedir}/target/`生成jar文件，名为`demo-1.0-SNAPSHOT-jar-with-dependencies.jar`
* 在`${basedir}/target/`目录下，运行命令`java -jar demo-1.0-SNAPSHOT-jar-with-dependencies.jar`可以打印`this is the main method`.

## 已存在代码说明
* 已经存在的代码主要讲解如何使用测试,我们看到HelloWorldTest下有两个测试,第一个测试演示了如何进行集成测试和使用Assert。
* 第二个测试演示了如何进行mock。
* 可以修改pom.xml上的属性`mainClass`。
/*
 Navicat Premium Data Transfer

 Source Server         : localhost[3306]
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : imcoder

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 21/01/2020 17:14:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `html` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 1100,
  `original` tinyint(1) NULL DEFAULT NULL COMMENT '是否原创',
  `views` int(11) NULL DEFAULT 0 COMMENT '阅读量',
  `likes` int(11) NULL DEFAULT 0 COMMENT '喜欢量',
  `comments` int(255) NULL DEFAULT 0 COMMENT '评论量',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `top` tinyint(1) NULL DEFAULT 0,
  `recommend` tinyint(1) NULL DEFAULT 0,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (15, '深入JAVA注解(Annotation)以及自定义注解', 11, NULL, '<p><span class=\"ql-lineHeight-200\">Java 注解（Annotation）又称 Java 标注，是 JDK5.0 引入的一种注释机制。Java 语言中的类、方法、变量、参数和包等都可以被标注。注解可以看作是一种特殊的标记，在程序在编译或者运行时可以检测到这些标记而进行一些特殊的处理。本文对 Annotation 进行了整理带你一步一步解开Java注解的神秘面纱并实现自己的自定义注解。</span></p><p><br></p><h2><span class=\"ql-lineHeight-200\">元注解</span></h2><p><span class=\"ql-lineHeight-200\">元注解的作用就是负责注解其他注解。Java5.0定义了4个标准的meta-annotation类型，它们被用来提供对其它 annotation类型作说明。他们位于java.lang.annotation包中。</span></p><p><span class=\"ql-lineHeight-200\">元注解下</span></p><ul><li><span class=\"ql-lineHeight-200\">@Target</span></li><li><span class=\"ql-lineHeight-200\">@Retention</span></li><li><span class=\"ql-lineHeight-200\">@Documented</span></li><li><span class=\"ql-lineHeight-200\">@Inherited</span></li></ul><p><br></p><p><span class=\"ql-lineHeight-200\">@Target：</span></p><p><span class=\"ql-lineHeight-200\">源码如下</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-variable\">@Documented</span>\n<span class=\"hljs-variable\">@Retention</span>(RetentionPolicy.RUNTIME)\n<span class=\"hljs-variable\">@Target</span>(ElementType.ANNOTATION_TYPE)\npublic <span class=\"hljs-variable\">@interface</span> Target {\n    <span class=\"hljs-comment\">/**\n     * Returns an array of the kinds of elements an annotation type\n     * can be applied to.\n     * @return an array of the kinds of elements an annotation type\n     * can be applied to\n     */</span>\n    ElementType[] value();\n}\n\n</pre><p><span class=\"ql-lineHeight-200\">它表明该注解可以应用的java元素类型</span></p><ul><li><span class=\"ql-lineHeight-200\">ElementType.TYPE：应用于类、接口（包括注解类型）、枚举</span></li><li><span class=\"ql-lineHeight-200\">ElementType.FIELD：应用于属性（包括枚举中的常量）</span></li><li><span class=\"ql-lineHeight-200\">ElementType.METHOD：应用于方法</span></li><li><span class=\"ql-lineHeight-200\">ElementType.PARAMETER：应用于方法的形参</span></li><li><span class=\"ql-lineHeight-200\">ElementType.CONSTRUCTOR：应用于构造函数</span></li><li><span class=\"ql-lineHeight-200\">ElementType.LOCAL_VARIABLE：应用于局部变量</span></li><li><span class=\"ql-lineHeight-200\">ElementType.ANNOTATION_TYPE：应用于注解类型</span></li><li><span class=\"ql-lineHeight-200\">ElementType.PACKAGE：应用于包</span></li><li><span class=\"ql-lineHeight-200\">ElementType.TYPE_PARAMETER：1.8版本新增，应用于类型变量）</span></li><li><span class=\"ql-lineHeight-200\">ElementType.TYPE_USE：1.8版本新增，应用于任何使用类型的语句中（例如声明语句、泛型和强制转换语句中的类型）</span></li></ul><p><br></p><p><span class=\"ql-lineHeight-200\">@Retention：源码如下</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-variable\">@Documented</span>\n<span class=\"hljs-variable\">@Retention</span>(RetentionPolicy.RUNTIME)\n<span class=\"hljs-variable\">@Target</span>(ElementType.ANNOTATION_TYPE)\npublic <span class=\"hljs-variable\">@interface</span> Retention {\n    <span class=\"hljs-comment\">/**\n     * Returns the retention policy.\n     * @return the retention policy\n     */</span>\n    RetentionPolicy value();\n}\n</pre><p><span class=\"ql-lineHeight-200\">它需要在什么级别保存该注释信息，用于描述注解的生命周期</span></p><ul><li><span class=\"ql-lineHeight-200\">RetentionPolicy.SOURCE：在源文件中有效（即源文件保留），编译时被丢弃，不包含在类文件中</span></li><li><span class=\"ql-lineHeight-200\">RetentionPolicy.CLASS：在class文件中有效（即class保留），JVM加载时被丢弃，包含在类文件中，默认值</span></li><li><span class=\"ql-lineHeight-200\">RetentionPolicy.RUNTIME：在运行时有效（即运行时保留），由JVM 加载，包含在类文件中，在运行时可以被获取到</span></li></ul><p><br></p><p><span class=\"ql-lineHeight-200\">@Documented：</span></p><p><span class=\"ql-lineHeight-200\">源码如下</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-variable\">@Documented</span>\n<span class=\"hljs-variable\">@Retention</span>(RetentionPolicy.RUNTIME)\n<span class=\"hljs-variable\">@Target</span>(ElementType.ANNOTATION_TYPE)\npublic <span class=\"hljs-variable\">@interface</span> Documented {\n}\n</pre><p><span class=\"ql-lineHeight-200\">它表明该注解标记的元素可以被Javadoc或类似的工具文档化，@Documented是一个标记注解，没有成员。</span></p><p><br></p><p><span class=\"ql-lineHeight-200\">@Inherited源码如下</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-variable\">@Documented</span>\n<span class=\"hljs-variable\">@Retention</span>(RetentionPolicy.RUNTIME)\n<span class=\"hljs-variable\">@Target</span>(ElementType.ANNOTATION_TYPE)\npublic <span class=\"hljs-variable\">@interface</span> Inherited {\n}\n\n</pre><p><span class=\"ql-lineHeight-200\">表明使用了@Inherited注解的注解，所标记的类的子类也会拥有这个注解是不是有点难以理解，我们举个</span></p><p><br></p><p><strong class=\"ql-lineHeight-200\">例子</strong></p><p><span class=\"ql-lineHeight-200\">自定义注解 @InheritedTest</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">package com.example.demo.<span class=\"hljs-keyword\">annotation</span>;\n\nimport java.lang.<span class=\"hljs-keyword\">annotation</span>.*;\n\n@Target(ElementType.TYPE)\n@Retention(RetentionPolicy.RUNTIME)\n@Inherited\npublic @interface InheritedTest {\n}\n\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">新建父类 Parent</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">package com.example.demo.test;\n\nimport com.example.demo.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">InheritedTest</span>;\n\n@InheritedTest\npublic <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">Parent</span> {</span>\n\n    public void testMethod(){\n        System.<span class=\"hljs-keyword\">out</span>.println(<span class=\"hljs-string\">\"Parent \"</span> + Parent<span class=\"hljs-class\">.<span class=\"hljs-keyword\">class</span>.<span class=\"hljs-title\">isAnnotationPresent</span>(<span class=\"hljs-title\">InheritedTest</span>.<span class=\"hljs-title\">class</span>));</span>\n    }\n}\n\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">新建子类 Child</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">package</span> com.example.demo.test;\n\n<span class=\"hljs-keyword\">import</span> com.example.demo.annotation.<span class=\"hljs-type\">InheritedTest</span>;\n\npublic <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">Child</span> <span class=\"hljs-keyword\">extends</span> <span class=\"hljs-title\">Parent</span> </span>{\n    public static void main(<span class=\"hljs-type\">String</span>[] args) {\n        <span class=\"hljs-type\">Child</span> child = <span class=\"hljs-keyword\">new</span> <span class=\"hljs-type\">Child</span>();\n        child.testMethod();\n        <span class=\"hljs-type\">System</span>.out.println(<span class=\"hljs-string\">\"Child \"</span> + <span class=\"hljs-type\">Child</span><span class=\"hljs-class\">.<span class=\"hljs-keyword\">class</span>.<span class=\"hljs-title\">isAnnotationPresent</span>(<span class=\"hljs-params\"><span class=\"hljs-type\">InheritedTest</span>.class</span>))</span>;\n    }\n}\n\n\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">isAnnotationPresent()方法表示指定注释类型的注释是否存在于此元素上，是则返回true，否则返回false。</span></p><p><span class=\"ql-lineHeight-200\">我们运行子类可以看到如下结果</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-cd18083653d56d7e?imageMogr2/auto-orient/strip|imageView2/2/w/445/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><p><span class=\"ql-lineHeight-200\">在子类中我们并没有使用 @InheritedTest 注解，结果一样返回了true，下面我们把InheritedTest中的@Inherited注释掉，然后再运行子类结果如下</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-7a80061a29247f1f?imageMogr2/auto-orient/strip|imageView2/2/w/398/format/webp\"></span></p><p><span class=\"ql-lineHeight-200\">可以看到，现在返回了false。</span></p><p><span class=\"ql-lineHeight-200\">简单示例</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">package com.example.demo.<span class=\"hljs-keyword\">annotation</span>;\n\nimport java.lang.<span class=\"hljs-keyword\">annotation</span>.*;\n\n@Target(ElementType.FIELD)\n@Documented()\n@Retention(RetentionPolicy.RUNTIME)\npublic @interface Color {\n    String value() default <span class=\"hljs-string\">\"\"</span>;\n}\n\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">以上我们就自定义了一个注解@Color，该注解应用于属性之上，在运行时有效，并且可以生成api文档。使用方法</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">package</span> com.example.demo.test;\n\n<span class=\"hljs-keyword\">import</span> com.example.demo.annotation.Color;\n\n<span class=\"hljs-keyword\">public</span> class Cat {\n\n    @Color(<span class=\"hljs-string\">\"黄色\"</span>)\n    <span class=\"hljs-keyword\">private</span> <span class=\"hljs-keyword\">String</span> <span class=\"hljs-built_in\">color</span>;\n\n    <span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">String</span> getColor(){\n        <span class=\"hljs-keyword\">return</span> <span class=\"hljs-keyword\">this</span>.<span class=\"hljs-built_in\">color</span>;\n    }\n\n    <span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">void</span> setColor(<span class=\"hljs-keyword\">String</span> <span class=\"hljs-built_in\">color</span>){\n        <span class=\"hljs-keyword\">this</span>.<span class=\"hljs-built_in\">color</span> = <span class=\"hljs-built_in\">color</span>;\n    }\n\n    <span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> main(<span class=\"hljs-keyword\">String</span> args[]){\n        Cat cat = <span class=\"hljs-keyword\">new</span> Cat();\n        System.out.<span class=\"hljs-built_in\">println</span>(cat.getColor());\n    }\n\n}\n\n</pre><p><span class=\"ql-lineHeight-200\">这里我们定义了一个Cat类，里面有一个属性color，我们使用@Color(\"黄色\")给他赋值为黄色，然后执行这个类打印出结果如下</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-2cb0b942c06fa90b?imageMogr2/auto-orient/strip|imageView2/2/w/414/format/webp\"></span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\">image</span></p><p><span class=\"ql-lineHeight-200\">什么？怎么为null呢，是不是感觉被忽悠了？因为我们只是定义了这个注解，但是却没写怎么处理被这个注解标记了字段，这个时候打印出来的当然为null了。</span></p><p><span class=\"ql-lineHeight-200\">现在我们来修改一下Cat类</span></p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">public static void main(String args<span class=\"hljs-literal\">[]</span>) throws NoSuchFieldException {\n        Cat cat = <span class=\"hljs-keyword\">new</span> <span class=\"hljs-constructor\">Cat()</span>;\n        Color color = <span class=\"hljs-module\"><span class=\"hljs-identifier\">Cat</span>.</span><span class=\"hljs-keyword\">class</span>.get<span class=\"hljs-constructor\">DeclaredField(<span class=\"hljs-string\">\"color\"</span>)</span>.get<span class=\"hljs-constructor\">Annotation(Color.<span class=\"hljs-params\">class</span>)</span>;\n        <span class=\"hljs-keyword\">if</span> (color != null) {\n            String value = color.value<span class=\"hljs-literal\">()</span>;\n            cat.set<span class=\"hljs-constructor\">Color(<span class=\"hljs-params\">value</span>)</span>;\n        }\n        <span class=\"hljs-module\"><span class=\"hljs-identifier\">System</span>.</span>out.println(cat.get<span class=\"hljs-constructor\">Color()</span>);\n    }\n\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">打印结果如下</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-bf8162184bef7dda?imageMogr2/auto-orient/strip|imageView2/2/w/406/format/webp\"></span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\">image</span></p><p><span class=\"ql-lineHeight-200\">以上我们简单的处理了下注解，并把处理逻辑放在了main方法中，其实这是不合理的。我们通过用下面的例子，使用Spring AOP面向切面编程思想来自定义日志注解。</span></p><p><span class=\"ql-lineHeight-200\">自定义注解</span></p><p><span class=\"ql-lineHeight-200\">自定义日志注解@SysLog</span></p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">package com.example.demo.<span class=\"hljs-keyword\">annotation</span>;\n\nimport java.lang.<span class=\"hljs-keyword\">annotation</span>.*;\n\n@Target(ElementType.METHOD)\n@Documented()\n@Retention(RetentionPolicy.RUNTIME)\npublic @interface SysLog {\n    String value() default <span class=\"hljs-string\">\"\"</span>;\n}\n\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">定义切面</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">package com.example.demo.aspect;\n\nimport com.example.demo.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">SysLog</span>;\nimport lombok.extern.slf4j.Slf4j;\nimport org.aspectj.lang.JoinPoint;\nimport org.aspectj.lang.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">After</span>;\nimport org.aspectj.lang.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">Aspect</span>;\nimport org.aspectj.lang.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">Before</span>;\nimport org.aspectj.lang.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">Pointcut</span>;\nimport org.aspectj.lang.reflect.MethodSignature;\nimport org.springframework.stereotype.Component;\n\nimport java.lang.reflect.Method;\n\n@Aspect\n@Component\n@Slf4j\npublic <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">SysLogAspect</span> {</span>\n\n    @Pointcut(<span class=\"hljs-string\">\"@annotation(com.example.demo.annotation.SysLog)\"</span>)\n    <span class=\"hljs-keyword\">private</span> void logPointCut() {\n\n    }\n\n    @Before(<span class=\"hljs-string\">\"logPointCut()\"</span>)\n    <span class=\"hljs-keyword\">private</span> void before(JoinPoint joinPoint){\n        String className = joinPoint.getTarget().getClass().getName();\n        MethodSignature signature = (MethodSignature) joinPoint.getSignature();\n        String methodName = signature.getName();\n        Method method = signature.getMethod();\n        SysLog sysLog = method.getAnnotation(SysLog<span class=\"hljs-class\">.<span class=\"hljs-keyword\">class</span>);</span>\n        String value = sysLog.value();\n        log.info(className);\n        log.info(methodName);\n        log.info(value);\n        log.info(<span class=\"hljs-string\">\"这里我们就可以自己处理了\"</span>);\n    }\n\n    @After(<span class=\"hljs-string\">\"logPointCut()\"</span>)\n    <span class=\"hljs-keyword\">private</span> void after(){\n        log.info(<span class=\"hljs-string\">\"执行之后\"</span>);\n    }\n}\n\n\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">测试注解</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">package com.example.demo.test;\n\nimport com.example.demo.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">SysLog</span>;\nimport org.springframework.web.bind.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">RequestMapping</span>;\nimport org.springframework.web.bind.<span class=\"hljs-keyword\">annotation</span>.<span class=\"hljs-title\">RestController</span>;\n\n@RestController\n@RequestMapping(<span class=\"hljs-string\">\"/log\"</span>)\npublic <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">LogTest</span> {</span>\n\n    @SysLog(<span class=\"hljs-string\">\"测试日志打印\"</span>)\n    @RequestMapping(<span class=\"hljs-string\">\"/test\"</span>)\n    public String testLog(){\n        <span class=\"hljs-keyword\">return</span> <span class=\"hljs-string\">\"这里是测试自定义注解日志打印\"</span>;\n    }\n\n}\n\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">用postman访问</span></p><p><span class=\"ql-lineHeight-200\">localhost:8080/log/test结果如下</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-3f77bd0c26b53dad?imageMogr2/auto-orient/strip|imageView2/2/w/705/format/webp\"></span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\">image</span></p><p><span class=\"ql-lineHeight-200\">控制台日志打印如下</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-fce70d92c8d0aaa0?imageMogr2/auto-orient/strip|imageView2/2/w/763/format/webp\"></span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\">image</span></p><p><span class=\"ql-lineHeight-200\">以上我们就完成了自定日志注解的实现，以上例子只是示例，实际开发中还可以又更多功能实现。好了，本文介绍就到这里了，如有错误请提出指正。</span></p>', 'Java 注解（Annotation）又称 Java 标注，是 JDK5.0 引入的一种注释机制。Java 语言中的类、方法、变量、参数和包等都可以被标注。注解可以看作是一种特殊的标记，在程序在编译或者运行时可以检测到这些标记而进行一些特殊的处理。本文对 Annotation 进行了整理带你一步一步解开Java注解的神秘面纱并实现自己的自定义注解。\n元注解\n元注解的作用就是负责注解其他注解。Ja', 1000, NULL, 45, 0, 0, NULL, 0, 0, '2020-01-17 18:44:31', '2020-01-21 17:09:54');
INSERT INTO `article` VALUES (17, 'Linux下 MySQL8安装教程', 11, NULL, '<p><span class=\"ql-lineHeight-200\">之前我们介绍了&nbsp;</span><a href=\"https://links.jianshu.com/go?to=http%3A%2F%2Fmp.weixin.qq.com%2Fs%3F__biz%3DMzI1OTYxMDM1Mg%3D%3D%26mid%3D2247483722%26idx%3D1%26sn%3Db8a2a1614e320e151e4dcc8771f4bdd4%26chksm%3Dea7702d5dd008bc333ea9e2a35bd801f4394a48ffda51ce884458c7a246293c5be45c538c480%26scene%3D21%23wechat_redirect\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"background-color: transparent; color: rgb(6, 129, 208);\" class=\"ql-lineHeight-200\">Windows下的MySQL8安装教程</a><span class=\"ql-lineHeight-200\">，那么Linux下该如何安装呢？本文以CentOS 7 为例，一步一步教你如何在Linux下安装MySQL-8.0.18</span></p><p><br></p><h2><span class=\"ql-lineHeight-200\">文章目录</span></h2><ul><li><span class=\"ql-lineHeight-200\">下载MySQL</span></li><li><span class=\"ql-lineHeight-200\">下载后解压</span></li><li><span class=\"ql-lineHeight-200\">编辑MySQL配置</span></li><li><span class=\"ql-lineHeight-200\">初始化MySQL</span></li><li><span class=\"ql-lineHeight-200\">启动MySQL</span></li><li><span class=\"ql-lineHeight-200\">修改root用户密码</span></li><li><span class=\"ql-lineHeight-200\">连接测试</span></li><li><span class=\"ql-lineHeight-200\">可能会遇到的问题</span></li></ul><p><br></p><h2><span class=\"ql-lineHeight-200\">1、下载MySQL</span></h2><p><span class=\"ql-lineHeight-200\">官网下载地址：</span><a href=\"https://links.jianshu.com/go?to=https%3A%2F%2Fdev.mysql.com%2Fdownloads%2Fmysql%2F\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"background-color: transparent; color: rgb(6, 129, 208);\" class=\"ql-lineHeight-200\">https://dev.mysql.com/downloads/mysql/</a></p><p><span class=\"ql-lineHeight-200\">我们选择以下版本Red Hat EnterPrise Linux / Oracle LinuxRed Hat EnterPrise Linux 7 / Oracle Linux 7 (x86 64-bit)</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-81eab618b60a0436?imageMogr2/auto-orient/strip|imageView2/2/w/963/format/webp\"></span></p><p><br></p><p><span class=\"ql-lineHeight-200\"><span class=\"ql-cursor\">﻿</span>在下方列表中选择 mysql-8.0.18-el7-x86_64.tar</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-8982adb0dd63289e?imageMogr2/auto-orient/strip|imageView2/2/w/963/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><h2><span class=\"ql-lineHeight-200\">2、下载后解压</span></h2><p><span class=\"ql-lineHeight-200\">解压后的文件如下</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-3a8f1aa9ea89e9fe?imageMogr2/auto-orient/strip|imageView2/2/w/643/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><p><span class=\"ql-lineHeight-200\">我们保留 mysql-8.0.18-el7-x86_64.tar.gz然后再解压</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">tar -zxvf mysql-8.0.18-el7-x86_64.tar.gz\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">我们准备把MySQL安装到 /usr/local 目录下所以解压后我们修改目录名称为 mysql 并移动到 /usr/local/ 目录下</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">mv mysql<span class=\"hljs-number\">-8.0.18</span>-el7-x86_64 /usr/local/mysql\n</pre><p><span class=\"ql-lineHeight-200\">下面我们来看看 /usr/local 目录下的文件</span></p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-built_in\">cd</span> /usr/<span class=\"hljs-built_in\">local</span>\n</pre><p><span class=\"ql-lineHeight-200\">我们可以看到mysql目录已经移动到了 /usr/local 下</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-faca47e7ab83ccb2?imageMogr2/auto-orient/strip|imageView2/2/w/525/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><p><span class=\"ql-lineHeight-200\">我们新建mysql用户，并更改mysql目录的所有者为mysql用户，如上图所示</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-attr\">useradd</span> <span class=\"hljs-string\">mysql</span>\n<span class=\"hljs-attr\">chown</span> <span class=\"hljs-string\">-R mysql:mysql mysql/</span>\n</pre><p><span class=\"hljs-attr\">﻿</span></p><h2><span class=\"ql-lineHeight-200\">3、编辑MySQL配置</span></h2><p><span class=\"ql-lineHeight-200\">我们进入mysql目录下并创建data、logs、config目录</span></p><ul><li><span class=\"ql-lineHeight-200\">data：数据目录</span></li><li><span class=\"ql-lineHeight-200\">logs：日志目录</span></li><li><span class=\"ql-lineHeight-200\">config：配置文件</span></li></ul><pre class=\"ql-syntax\" spellcheck=\"false\">cd mysql\nmkdir data\nmkdir logs\nmkdir<span class=\"hljs-built_in\"> config\n</span></pre><p><br></p><p><span class=\"ql-lineHeight-200\">在config目录下我们新建my.cnf配置文件，为防止与机器上其他用户安装的MySQL冲突，我们使用此配置文件来作为MySQL的配置文件</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">touch my.<span class=\"hljs-keyword\">cnf</span>\n或\n<span class=\"hljs-keyword\">vi</span> my.<span class=\"hljs-keyword\">cnf</span>\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">我们在my.cnf中写入以下配置</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">[mysql]\n<span class=\"hljs-comment\"># 设置mysql客户端默认字符集</span>\n<span class=\"hljs-attribute\">default-character-set</span>=UTF8MB4\n[mysqld]\nskip-name-resolve\n<span class=\"hljs-attribute\">default_authentication_plugin</span>=mysql_native_password\n<span class=\"hljs-comment\">#设置3355端口</span><span class=\"hljs-built_in\">\nport </span>= 3335\n<span class=\"hljs-comment\"># 设置mysql的安装目录</span>\n<span class=\"hljs-attribute\">basedir</span>=/usr/local/mysql\n<span class=\"hljs-comment\"># 设置mysql数据库的数据的存放目录</span>\n<span class=\"hljs-attribute\">datadir</span>=/usr/local/mysql/data\n<span class=\"hljs-comment\"># 允许最大连接数</span>\n<span class=\"hljs-attribute\">max_connections</span>=200\n<span class=\"hljs-comment\"># 服务端使用的字符集默认为8比特编码的latin1字符集</span>\n<span class=\"hljs-attribute\">character-set-server</span>=UTF8MB4\n<span class=\"hljs-comment\"># 创建新表时将使用的默认存储引擎</span>\n<span class=\"hljs-attribute\">log_error</span>=/usr/local/mysql/logs/mysql.log\n<span class=\"hljs-attribute\">pid-file</span>=/usr/local/mysql/logs/mysql.pid\n<span class=\"hljs-attribute\">default-storage-engine</span>=INNODB\n<span class=\"hljs-attribute\">lower_case_table_names</span>=1\n<span class=\"hljs-attribute\">max_allowed_packet</span>=16M\n<span class=\"hljs-attribute\">group_concat_max_len</span>=102400\n[client]\n<span class=\"hljs-attribute\">port</span>=3335\n</pre><p><br></p><h2><span class=\"ql-lineHeight-200\">4、初始化MySQL</span></h2><p><span class=\"ql-lineHeight-200\">配置文件编辑好以后，下面我们来初始化MySQL进入到bin目录下然后执行初始化命令</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">cd bin\n./mysqld <span class=\"hljs-attribute\">--defaults-file</span>=/usr/local/mysql/config/my.cnf --initialize <span class=\"hljs-attribute\">--user</span>=mysql <span class=\"hljs-attribute\">--basedir</span>=/usr/local/mysql/ <span class=\"hljs-attribute\">--datadir</span>=/usr/local/mysql/data/\n</pre><p><span class=\"ql-lineHeight-200\">参数说明</span></p><ul><li><span class=\"ql-lineHeight-200\">--defaults-file=/usr/local/mysql/config/my.cnf 指定配置文件（一定要放在最前面，至少 --initialize 前面）</span></li><li><span class=\"ql-lineHeight-200\">--user=mysql 指定用户（很关键）</span></li><li><span class=\"ql-lineHeight-200\">--basedir=/usr/local/mysql/ 指定安装目录</span></li><li><span class=\"ql-lineHeight-200\">--datadir=/usr/local/mysql/data/ 指定数据目录</span></li></ul><p><span class=\"ql-lineHeight-200\">以上步骤操作完成后，根据网上其他教程，此时应该会生成一个临时密码才对，然而并没有。这个时候不要慌，因为我们指定了log_error的目录（可能是因为这个原因），所以，我们来看一下 /usr/local/mysql/logs/mysql.log 这个文件</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-function\"><span class=\"hljs-title\">cd</span></span> ../logs\ncat mysql.log\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">果然发现了临时密码，我们把它记下来，等下要用到</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-95b3b6d0709fec22?imageMogr2/auto-orient/strip|imageView2/2/w/1080/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><h2><span class=\"ql-lineHeight-200\">5、启动MySQL</span></h2><p><span class=\"ql-lineHeight-200\">让我们再回到bin目录下去启动MySQL</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">cd</span> <span class=\"hljs-string\">../bin</span>\n<span class=\"hljs-string\">./mysqld_safe</span> <span class=\"hljs-params\">--defaults-file=/usr/local/mysql/config/my</span>.cnf\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">这时我们可以看到在logs目录下已经生成了 mysql.pid 这说明MySQL已经成功启动执行命令</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">cat mysql.pid\n</pre><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-cb1f372a0a10fe80?imageMogr2/auto-orient/strip|imageView2/2/w/455/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><p><span class=\"ql-lineHeight-200\">6816即为MySQL的进程，我们也可以ps一下</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">ps</span> -ef|<span class=\"hljs-keyword\">grep</span> mysql\n</pre><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-463b76d1bf21ff0d?imageMogr2/auto-orient/strip|imageView2/2/w/902/format/webp\"></span></p><p><span class=\"ql-lineHeight-200\">由上可见，MySQL已经成功启动</span></p><p><br></p><h2><span class=\"ql-lineHeight-200\">6、修改root用户密码</span></h2><p><span class=\"ql-lineHeight-200\">下面让我们回到bin目录下去登录MySQL，并修改root用户密码。</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">cd</span> <span class=\"hljs-string\">../bin</span>\n<span class=\"hljs-string\">./mysql</span> -u root -p\n</pre><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-dccd944ffe50522a?imageMogr2/auto-orient/strip|imageView2/2/w/501/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><p><span class=\"ql-lineHeight-200\">这是输入我们刚才记下的临时密码，输入后会显示登录成功</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-1b95a8c05ed853f3?imageMogr2/auto-orient/strip|imageView2/2/w/723/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><p><span class=\"ql-lineHeight-200\">执行以下命令修改root用户密码</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">ALTER<span class=\"hljs-built_in\"> USER </span><span class=\"hljs-string\">\'root\'</span>@<span class=\"hljs-string\">\'localhost\'</span> IDENTIFIED BY <span class=\"hljs-string\">\'新密码\'</span>;\n</pre><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-e9b758c01bd0b88c?imageMogr2/auto-orient/strip|imageView2/2/w/601/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><p><span class=\"ql-lineHeight-200\">修改root用户远程登录</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">update</span> mysql.<span class=\"hljs-keyword\">user</span> <span class=\"hljs-keyword\">set</span> host=<span class=\"hljs-string\">\'%\'</span> <span class=\"hljs-keyword\">where</span> <span class=\"hljs-keyword\">user</span>=<span class=\"hljs-string\">\'root\'</span>;\n</pre><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-10240cc5f65c3e14?imageMogr2/auto-orient/strip|imageView2/2/w/541/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><p><span class=\"ql-lineHeight-200\">最后记得刷新权限</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">flush</span> <span class=\"hljs-keyword\">privileges</span>;\n</pre><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-f17804512de36873?imageMogr2/auto-orient/strip|imageView2/2/w/381/format/webp\"></span></p><p class=\"ql-align-center\"><br></p><h2><span class=\"ql-lineHeight-200\">7、连接测试</span></h2><p><span class=\"ql-lineHeight-200\">现在我们用navicat来进行连接测试</span></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-bf0c9ad65e3bf517?imageMogr2/auto-orient/strip|imageView2/2/w/560/format/webp\"></span></p><p><span class=\"ql-lineHeight-200\">到此MySQL成功安装。</span></p><p><br></p><h2><span class=\"ql-lineHeight-200\">8、可能会遇到的问题</span></h2><p><span class=\"ql-lineHeight-200\">（1）初始化报错</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">error </span>while loading shared libraries: libaio.so.1: cannot open shared object file: No such file or directory\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">解决方案</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">yum <span class=\"hljs-keyword\">install</span> -y libaio\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">（2）报无权限之类的错误</span></p><pre class=\"ql-syntax\" spellcheck=\"false\"><span class=\"hljs-keyword\">chown</span> -R mysql:mysql 无权限的目录\n或\n<span class=\"hljs-keyword\">chmod</span> -R <span class=\"hljs-number\">777</span> 无权限的目录\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">（3）启动报错</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">Starting MySQL.The<span class=\"hljs-built_in\"> server </span>quit without updating PID file (/[FAILED]mysql/xxx.pid).\n</pre><p><br></p><p><span class=\"ql-lineHeight-200\">解决方案尝试用msqld_safe启动</span></p><pre class=\"ql-syntax\" spellcheck=\"false\">.<span class=\"hljs-regexp\">/mysqld_safe --defaults-file=/u</span>sr<span class=\"hljs-regexp\">/local/my</span>sql<span class=\"hljs-regexp\">/config/my</span>.cnf ···\n</pre><p><br></p><p><br></p><p class=\"ql-align-center\"><span class=\"ql-lineHeight-200\"><img src=\"https://upload-images.jianshu.io/upload_images/20263435-fdc3567e67732bd3?imageMogr2/auto-orient/strip|imageView2/2/w/189/format/webp\"></span></p><p><br></p>', '之前我们介绍了 Windows下的MySQL8安装教程，那么Linux下该如何安装呢？本文以CentOS 7 为例，一步一步教你如何在Linux下安装MySQL-8.0.18文章目录\n下载MySQL\n下载后解压\n编辑MySQL配置\n初始化MySQL\n启动MySQL\n修改root用户密码\n连接测试\n可能会遇到的问题\n1、下载MySQL\n官网下载地址：https://dev.mysql.com/do', 1000, NULL, 21, 0, 0, NULL, 0, 0, '2020-01-21 15:22:49', '2020-01-21 17:09:39');

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_category
-- ----------------------------
INSERT INTO `article_category` VALUES (25, 15, 10, 11, '2020-01-19 18:03:12', '2020-01-19 18:03:12');
INSERT INTO `article_category` VALUES (29, 17, 12, 11, '2020-01-21 15:27:29', '2020-01-21 15:27:29');

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL,
  `tag_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES (21, 15, 8, 11, '2020-01-19 18:03:12', '2020-01-19 18:03:12');
INSERT INTO `article_tag` VALUES (22, 15, 10, 11, '2020-01-19 18:03:12', '2020-01-19 18:03:12');
INSERT INTO `article_tag` VALUES (26, 17, 11, 11, '2020-01-21 15:27:29', '2020-01-21 15:27:29');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (10, 'Java', 11, '2020-01-17 18:43:53', '2020-01-17 18:43:53');
INSERT INTO `category` VALUES (11, 'python', 11, '2020-01-17 18:44:00', '2020-01-17 18:44:00');
INSERT INTO `category` VALUES (12, 'MySQL', 11, '2020-01-17 18:44:08', '2020-01-17 18:44:08');

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `autoapprove` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '终端信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client_1', NULL, '$2a$10$3hTUSGc9W2IU9PCUxqIz2e8lf810r/z0nzIgpgy1W4FZYb/SKAkFi', 'all', 'password,authorization_code,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', NULL, '2020-01-04 10:53:07');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin1', '2019-12-31 13:14:14', '2019-12-31 13:42:20');
INSERT INTO `role` VALUES (2, 'admin2', '2019-12-31 13:41:08', '2019-12-31 13:42:51');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (8, 'spring', 11, '2020-01-17 18:44:13', '2020-01-17 18:44:13');
INSERT INTO `tag` VALUES (9, 'springboot', 11, '2020-01-17 18:44:25', '2020-01-17 18:44:25');
INSERT INTO `tag` VALUES (10, '注解', 11, '2020-01-19 18:03:08', '2020-01-19 18:03:08');
INSERT INTO `tag` VALUES (11, 'mysql', 11, '2020-01-21 15:22:09', '2020-01-21 15:22:09');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 'admin', '18355193335', 'imacoder@126.com', '$2a$10$MmWjrLtYlrkfceaNafUGSu6cdAiPCTObxATlV5edj2MsE7Q4ze56u', '2019-12-04 10:17:20', '2020-01-04 11:11:17');
INSERT INTO `user` VALUES (11, 'cdd', '13482835675', 'imacoder@126.com', '$2a$10$3hTUSGc9W2IU9PCUxqIz2e8lf810r/z0nzIgpgy1W4FZYb/SKAkFi', '2019-12-04 17:51:49', '2020-01-04 11:15:15');
INSERT INTO `user` VALUES (12, 'coder的自我修养', NULL, 'imacoder@126.com', '123456', '2019-12-04 17:53:25', '2019-12-04 17:53:25');
INSERT INTO `user` VALUES (13, 'coder的自我修养', NULL, 'imacoder@126.com', '123456', '2019-12-04 17:59:22', '2019-12-04 17:59:22');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

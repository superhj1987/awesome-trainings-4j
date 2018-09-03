# awesome-trainings-4j

my code training space including some examples, some useful codes in Java. 

## 一. Tomcat的简单实现

me.rowkey.trainings.tomcattest.*

## 二. 《Effective Java》相关代码

me.rowkey.trainings.effective_java.*

## 三. Netty学习笔记

me.rowkey.trainings.netty.*

### 《Netty 权威指南》

这本书讲的很透彻，但是不知道为什么代码确感觉像是应付一样，各种纰漏。即使因为版本的原因，也不应该不做说明让读者云里雾里的。

试着按照14章的代码写了一下，发现各种错误。实在是想不通作者怎么运行成功的。故修改了一下代码使其正常运行。

代码可见 me.rowkey.trainings.netty.server.NettyServer me.rowkey.trainings.netty.client.NettyClient 以及其他相关代码

### Spring注解上下文配置

Spring 3.0支持了纯注解配置，即一个xml都不需要，完全把配置的bean写在代码中。

代码可见 me.rowkey.netty.trainings.config.SpringConfig me.rowkey.trainings.netty.Main 以及其他相关代码

### Netty源码关键点

1. ByteBuf

2. Channel 和 Unsafe

3. ChannelPipeline 和 ChannelHandler

4. EventLoop 和 EventLoopGroup

5. Future 和 Promise

## 四. JMX例子

me.rowkey.trainings.jmx.*

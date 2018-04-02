package com.github.superhj1987.trainings.tomcattest;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.startup.HostConfig;

/**
 * Author: Bryant Hang
 * Date: 12-2-24
 * Time: 上午10:31
 * Description:基于tomcat组件自实现的一个Web容器
 */
public class TomcatMainV5 {
    public static void main(String args[]) throws Exception{
        //设置基目录
        System.setProperty("catalina.base", "/Users/BryantHang/data/tomcattest/");

        // Connector
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(8090);

        StandardContext context = new StandardContext();
        context.setName("/gb");
        context.setDisplayName("gitbucket");
        context.setPath("/gb");
        context.setDocBase("/Users/BryantHang/Programming/server-software/apache-tomcat-7.0.68/webapps/gitbucket");

        // 请求处理器(Engine + Host)
        // Host
        StandardHost standardHost = new StandardHost();
        standardHost.setName("localhost");
        standardHost.setAppBase("webapps");
        // HostConfig负责解析Context配置，并将Context加入到Host中，
        standardHost.addLifecycleListener(new HostConfig());
        standardHost.addChild(context);

        // Engine
        StandardEngine standardEngine = new StandardEngine();
        standardEngine.setName("Catalina");
        //此处可以看到Host是Engine的Child
        standardEngine.addChild(standardHost);
        standardEngine.setDefaultHost("localhost");

        // Connector+请求处理器 组装成webserver
        StandardService standardService = new StandardService();
        standardService.setName("Catalina");
        //设置接收请求Connector，可支持多个Connector
        standardService.addConnector(connector);
        //设置请求处理器
        standardService.setContainer(standardEngine);

        // 开始初始化
        standardService.start();

        while (true)
        {
            Thread.sleep(60000);
            System.out.println("still active");
        }
    }
}

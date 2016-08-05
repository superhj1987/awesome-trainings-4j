package com.github.superhj1987.trainings.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.rmi.registry.LocateRegistry;

/**
 * Author: Bryant Hang
 * Date: 16/8/5
 * Time: 下午9:53
 */
public class JmxExample {
    public static final int JMX_PORT = 1099;
    public static final String HOST = "127.0.0.1";

    public void export() {
        try {
            String DOMAIN = "localhost";
            // 创建一个MBeanServer
            MBeanServer mbs = MBeanServerFactory.createMBeanServer(DOMAIN);
            //MBeanServer mbs = MBeanServerFactory.createMBeanServer();//不能在jconsole中使用
            //MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();//可在jconsole中使用
            //用MBeanServer注册LoginStatsMBean
            //MBeanServer.registerMBean(Object，ObjectName)方法使用的参数有两个：一个是MBean实现的一个实例；另一个是类型ObjectName的一个对象-它用于唯一地标识该MBean
            mbs.registerMBean(new Status(), new ObjectName(DOMAIN + ":name=statusBean"));

            // 存取该JMX服务的URL：
            JMXServiceURL url = new JMXServiceURL("rmi", HOST, JMX_PORT, "/jndi/rmi://" + HOST + ":" + 1099 + "/app");
            // start()和stop()来启动和停止 JMXConnectorServer
            JMXConnectorServer jmxServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
            System.out.println(url);
            // 在RMI上注册
            LocateRegistry.createRegistry(JMX_PORT);
            jmxServer.start();

            //创建适配器，用于能够通过浏览器访问MBean
//            HtmlAdaptorServer adapter = new HtmlAdaptorServer();
//            adapter.setPort(9797);
//            mbs.registerMBean(adapter, new ObjectName(
//                    "MyappMBean:name=htmladapter,port=9797"));
//            adapter.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

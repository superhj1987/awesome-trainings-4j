package com.github.superhj1987.trainings.tomcattest;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Author: Bryant.Hang
 * Date: 2018/4/2
 * Email: superhj1987@126.com
 */
public class TomcatV8 {
    public static void main(String[] args) throws LifecycleException, ServletException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9080);
        tomcat.setBaseDir("~/data/tomcattest/tmp");
        tomcat.getHost().setAutoDeploy(false);

        StandardContext context = new StandardContext();
        context.setPath("");
        context.setDocBase(new File("~/boot").getAbsolutePath());
        context.addLifecycleListener(new Tomcat.DefaultWebXmlListener());
        context.addLifecycleListener(new Tomcat.FixContextListener());
        ContextConfig ctxCfg = new ContextConfig();
        context.addLifecycleListener(ctxCfg);
        ctxCfg.setDefaultWebXml("org/apache/catalina/startup/NO_DEFAULT_XML");

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        context.setResources(resources);

        tomcat.getHost().addChild(context);
        tomcat.start();
        tomcat.getServer().await();

    }
}

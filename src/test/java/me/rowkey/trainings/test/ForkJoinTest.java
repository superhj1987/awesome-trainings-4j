package me.rowkey.trainings.test;

import me.rowkey.trainings.forkjoin.WordCounter;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.File;
import java.util.Locale;

import static org.apache.commons.codec.binary.Hex.DEFAULT_CHARSET;

/**
 * Created by Bryant.Hang on 17/2/20.
 */
public class ForkJoinTest {
    @Test
    public void test() throws Exception {
        WordCounter.main(new String[0]);
    }


}

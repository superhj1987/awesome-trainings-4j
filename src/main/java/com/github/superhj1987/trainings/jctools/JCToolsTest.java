package com.github.superhj1987.trainings.jctools;

import org.jctools.queues.MpscChunkedArrayQueue;
import org.junit.Test;

import java.util.Queue;

/**
 * Created by Bryant.Hang on 16/12/1.
 */
public class JCToolsTest {
    @Test
    public void test() throws Exception {
        Queue<Integer> q = new MpscChunkedArrayQueue<Integer>(1024, 8*1024);
        // fill up the queue
        int i = 0;
        while(q.offer(i)) i++;
        System.out.println("Added "+ i);
        // empty it
        i = 0;
        while(q.poll() != null) i++;
        System.out.println("Removed "+ i);


    }
}

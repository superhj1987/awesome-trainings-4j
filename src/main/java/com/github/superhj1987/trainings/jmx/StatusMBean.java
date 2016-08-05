package com.github.superhj1987.trainings.jmx;

/**
 * Author: Bryant Hang
 * Date: 16/8/5
 * Time: 下午9:55
 */
public interface StatusMBean {
    //属性
    public void setName(String name);
    public String getName();


    //操作
    /**
     * 获取当前信息
     * @return
     */
    public String status();
}

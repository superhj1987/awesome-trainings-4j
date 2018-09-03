package me.rowkey.trainings.jmx;

/**
 * Author: Bryant Hang
 * Date: 16/8/5
 * Time: 下午9:55
 */
public interface StatusMBean {
    public String getName();

    //属性
    public void setName(String name);


    //操作

    /**
     * 获取当前信息
     *
     * @return
     */
    public String status();
}

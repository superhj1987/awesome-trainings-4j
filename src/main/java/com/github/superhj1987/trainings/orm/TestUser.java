package com.github.superhj1987.trainings.orm;

import java.io.Serializable;

/**
 * Author: Bryant.Hang
 * Date: 2018/8/22
 * Email: superhj1987@126.com
 */
public class TestUser implements Serializable {
    private long id;

    private long uid;

    private String name;

    private long idd;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdd() {
        return idd;
    }

    public void setIdd(long idd) {
        this.idd = idd;
    }
}

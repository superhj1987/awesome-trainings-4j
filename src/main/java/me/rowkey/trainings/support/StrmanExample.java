package me.rowkey.trainings.support;

import com.google.common.base.MoreObjects;
import strman.Strman;

/**
 * Created by Bryant.Hang on 2017/8/17.
 */
public class StrmanExample {

    public static void main(String[] args) {
        Strman.base64Decode("testName");
        System.out.println(MoreObjects.toStringHelper(new User()).add("name", "testName").toString());
    }
}

class User {
    private String name = "aa";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

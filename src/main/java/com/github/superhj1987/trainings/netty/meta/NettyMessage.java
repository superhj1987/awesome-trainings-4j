package com.github.superhj1987.trainings.netty.meta;

import java.io.Serializable;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 上午10:45
 */
public class NettyMessage implements Serializable {
    private Header header;

    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}

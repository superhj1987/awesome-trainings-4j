package com.github.superhj1987.trainings.netty.meta;

import java.io.Serializable;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 上午10:46
 */
public class Header implements Serializable {
    private int crcCode = 0xabef0101;
    private int length;
    private long sessionID;
    private byte type;
    private byte priority;

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionID=" + sessionID +
                ", type=" + type +
                ", priority=" + priority +
                '}';
    }
}

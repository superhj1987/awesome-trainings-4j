package com.github.superhj1987.trainings.netty.meta;

/**
 * Author: Bryant
 * Date: 14/11/7
 * Time: 下午4:56
 */
public enum MessageType {
    LOGIN_REQ((byte) 1), LOGING_RES((byte) 2), HEARTBEAT_REQ((byte) 3), HEARTBEAT_RES((byte) 4);


    private byte value;

    private MessageType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}

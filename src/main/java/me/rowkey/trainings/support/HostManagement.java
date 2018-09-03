package me.rowkey.trainings.support;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Bryant Hang
 */
public class HostManagement {
    /**
     * 启动指定mac地址的主机
     *
     * @param mac
     * @return
     */
    public static boolean powerOnHost(String mac) {

        String macStr = mac;

        try {
            byte macBytes[] = getMacBytes(macStr);
            byte bytes[] = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++)
                bytes[i] = -1;

            for (int i = 6; i < bytes.length; i += macBytes.length)
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);

            InetAddress addr = InetAddress.getByName("10.150.0.255");
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
                    addr, 40000);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据mac字符串获取mac字节
     *
     * @param macStrsrc
     * @return
     */
    public static byte[] getMacBytes(String macStrsrc) {
        byte bytes[] = new byte[6];
        String hex[] = macStrsrc.split("-");
        if (hex.length != 6)
            throw new IllegalArgumentException("Invalid MAC address");
        try {
            for (int i = 0; i < 6; i++)
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid hex digit in MAC address");
        }
        return bytes;
    }

}
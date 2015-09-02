package srhang.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 远程启动主机
 * @author Bryant Hang
 *
 */
public class HostManagement {
	public boolean powerOnHost(String mac) {

		String macStr = mac;

		try {
			byte macBytes[] = getMacBytes(macStr);
			byte bytes[] = new byte[6 + 16 * macBytes.length];
			for (int i = 0; i < 6; i++)
				bytes[i] = -1;

			for (int i = 6; i < bytes.length; i += macBytes.length)
				System.arraycopy(macBytes, 0, bytes, i, macBytes.length);

			InetAddress addr = InetAddress.getByName("10.150.0.255");// 广播
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
					addr, 40000);
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			socket.close();
			System.out.println("Wake on LAN packet sent ，远程启动主机成功");
			return true;
		} catch (Exception e) {
			System.out.print("fail");
			return false;
		}
	}
	
	public byte[] getMacBytes(String macStrsrc) {
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
	
	public static void main(String args[]){
		new HostManagement().powerOnHost("00-21-85-6D-50-62");
		new HostManagement().powerOnHost("00-21-85-6D-50-5D");
	}
}
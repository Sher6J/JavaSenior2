package cn.sher6j.java1;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * UDP协议的网络编程
 * @author sher6j
 * @create 2020-04-04-14:13
 */
public class UDPTest {
    //发送端
    @Test
    public void send() throws IOException {

        DatagramSocket socket = new DatagramSocket();

        String str = "UDP数据";
        byte[] date = str.getBytes();
        DatagramPacket packet = new DatagramPacket(date, 0, date.length, InetAddress.getByName("localhost"), 9090);

        socket.send(packet);

        socket.close();

    }

    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
        socket.receive(packet);

        System.out.println(new String(packet.getData(), 0, packet.getLength()));

        socket.close();
    }
}

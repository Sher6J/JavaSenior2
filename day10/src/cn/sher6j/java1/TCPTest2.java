package cn.sher6j.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题2 ：客户端发送文件给服务器，服务器将文件保存在本地
 * @author sher6j
 * @create 2020-04-04-13:04
 */
public class TCPTest2 {

    @Test
    public void client() throws IOException {

        Socket socket = new Socket(InetAddress.getByName("localhost"), 9090);

        OutputStream os = socket.getOutputStream();

        FileInputStream fis = new FileInputStream(new File("yoona08.jpg"));

        byte[] buff = new byte[1024];
        int len;
        while ((len = fis.read(buff)) != -1) {
            os.write(buff, 0, len);
        }

        fis.close();
        os.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket socket = new ServerSocket(9090);

        Socket accept = socket.accept();

        InputStream is = accept.getInputStream();

        FileOutputStream fos = new FileOutputStream(new File("accept.jpg"));

        byte[] buff = new byte[1024];
        int len;
        while ((len = is.read(buff)) != -1) {
            fos.write(buff, 0, len);
        }

        fos.close();
        is.close();
        accept.close();

    }
}

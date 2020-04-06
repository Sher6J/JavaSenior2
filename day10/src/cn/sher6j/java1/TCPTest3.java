package cn.sher6j.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端，并关闭相应的链接
 *
 * @author sher6j
 * @create 2020-04-04-13:51
 */
public class TCPTest3 {
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

        //关闭数据的输出
        socket.shutdownOutput();

        //接受来自于服务器端的数据，并显示到控制台上
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len1;
        while ((len1 = is.read(buffer)) != -1) {
            baos.write(buff, 0, len1);
        }

        System.out.println(baos.toString());

        fis.close();
        os.close();
        socket.close();
        baos.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket socket = new ServerSocket(9090);

        Socket accept = socket.accept();

        InputStream is = accept.getInputStream();

        FileOutputStream fos = new FileOutputStream(new File("accept1.jpg"));

        byte[] buff = new byte[1024];
        int len;
        while ((len = is.read(buff)) != -1) {
            fos.write(buff, 0, len);
        }

        //服务器端给客户端反馈
        OutputStream os = accept.getOutputStream();
        os.write("照片已收到".getBytes());

        fos.close();
        is.close();
        accept.close();
        os.close();
    }
}

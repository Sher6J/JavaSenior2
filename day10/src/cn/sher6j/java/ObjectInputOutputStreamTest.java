package cn.sher6j.java;

import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 * 1. ObjectInputStream 和 ObjectOutputStream
 * 2. 作用： 用于存储和读取 基本数据类型数据或 对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 * 3. 要想一个Java对象是可序列化的，需要满足相应的要求，见Person.java
 *
 * @author sher6j
 * @create 2020-04-04-10:36
 */
public class ObjectInputOutputStreamTest {

    /*
    序列化的过程：将内存中的Java对象保存到磁盘当中或通过网络传输出去
    使用ObjectOutputSteam
     */
    @Test
    public void testObjectOutputSteam() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            oos.writeObject(new String("武汉加油"));
            oos.flush(); //刷新操作

            oos.writeObject(new Person("james", 23));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
    按序列化：将磁盘文件中的对象还原为内存中的一个Java对象
    ObjectInputStream
     */
    @Test
    public void testObjectInputStream() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object obj = ois.readObject();

            String str = (String) obj;
            System.out.println(str);

            Person p = (Person) ois.readObject();
            System.out.println(p);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

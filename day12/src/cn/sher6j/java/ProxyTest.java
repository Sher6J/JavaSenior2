package cn.sher6j.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理举例
 *
 * @author sher6j
 * @create 2020-04-05-19:02
 */
interface Human{

    String getBelif();

    void eat(String food);
}

//被代理类
class SuperMan implements Human{

    @Override
    public String getBelif() {
        return "I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("超人吃" + food);
    }
}

class HumanUtil{
    public void method1() {
        System.out.println("通用方法一==============");
    }

    public void method2() {
        System.out.println("通用方法二**************");
    }
}

/*
要想实现动态代理，需要解决的问题？
 一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象
 二：当通过代理类的对象调用方法时，如何动态的去调用被代理类中的同名方法
 */

class ProxyFactory{
    //调用此静态方法，返回一个代理类的对象，解决问题一
    public static Object getProxyInstance(Object obj) { //obj：被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);

        //类的加载器，接口
        Object o = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
        return o;

    }
}

class MyInvocationHandler implements InvocationHandler{

    private Object obj; //需要使用被代理类对象进行赋值

    public void bind(Object obj) {
        this.obj = obj;
    }

    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HumanUtil util = new HumanUtil();
        util.method1();

        //method：即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj：被代理类的对象
        Object returnValue = method.invoke(obj, args);

        util.method2();
        //上述方法的返回值就作为当前类中invoke()方法的返回值
        return returnValue;
    }
}


public class ProxyTest {
    public static void main(String[] args) {
        //先创建被代理类对象
        SuperMan superMan = new SuperMan();
        //创建代理类对象proxyInstance
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的调用被代理类中的同名方法
        String belif = proxyInstance.getBelif();
        System.out.println(belif);
        proxyInstance.eat("麻辣烫");

        System.out.println("-------------");

        NikeClothFactory nike = new NikeClothFactory();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nike);
        proxyClothFactory.productCloth();
    }
}

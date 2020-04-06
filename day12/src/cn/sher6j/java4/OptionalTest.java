package cn.sher6j.java4;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常
 *
 * @author sher6j
 * @create 2020-04-06-17:51
 */
public class OptionalTest {

    /*
    创建Optional类对象方式：
     Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
     Optional.empty() : 创建一个空的 Optional 实例
     Optional.ofNullable(T t) ：t可以为null
     */
    @Test
    public void test1() {
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
    }

    @Test
    public void test2() {
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);

        //为空就返回orElse中的另一个
        Girl girl1 = optionalGirl.orElse(new Girl("王鸥"));
        System.out.println(girl1);
    }

    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }

    @Test
    public void test3() {
        /*
        没有Girl对象
        NullPointerException空指针异常
         */
        Boy boy = new Boy();
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    //优化以后的getGirlName()  -----  两层就得套两层if，多层就得套多层if
    public String getGirlName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    @Test
    public void tets4() {
        Boy boy = new Boy();
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }

    //使用Optional类的getGirlName()
    public String getGirlName2(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("王鸥")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("杨幂"));

        return girl1.getName();
    }

    @Test
    public void test5() {
        Boy boy = null;
        String girlName = getGirlName2(boy);
        System.out.println(girlName);
    }
}

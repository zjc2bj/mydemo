package cn.zjc.demo.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {

    public static void main(String[] args) {
        MethodDemo1 demo1 = new MethodDemo1();
        demo1.userName = "aaa";
        MethodDemo2 demo2 = new MethodDemo2();
        demo1.demo2 = demo2;

        try {
            Method method = MethodDemo1.class.getDeclaredMethod("printTest2", null);
            method.setAccessible(true);
            Object invoke = method.invoke(demo1, null);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

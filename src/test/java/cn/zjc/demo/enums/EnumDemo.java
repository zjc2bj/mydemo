package cn.zjc.demo.enums;

public class EnumDemo {

    public static void main(String[] args) {
        System.out.println(Type.PAY_TYPE_AIRPAY.name());
        System.out.println(Type.PAY_TYPE_AIRPAY.toString());
        System.out.println(Type.PAY_TYPE_AIRPAY.getClass());
        
        Type[] types = Type.values();
        for(Type type:types){
            System.out.println("***********");
            System.out.println(type.name());
            //System.out.println(type.ordinal());
            System.out.println(type.value);
           // System.out.println(type.valueOf(Type.class, "PAY_TYPE_AIRPAY"));
            //System.out.println(type.valueOf(type.name()));
        }
    }
}

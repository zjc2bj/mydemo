package cn.zjc.demo;

public class AndJudgeDemo {
    public static boolean isKey(){
        System.out.println("||------执行---------");
        return false;
    }
    
    public static void main(String[] args) {
        if(1==2 || isKey()){
            System.out.println("aaaaaaaa");
        }
    }
}

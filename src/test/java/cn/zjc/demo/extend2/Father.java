package cn.zjc.demo.extend2;

public class Father{
    public int i = 1;
    public void print(){
        
        System.out.println("fffffffffffffffffffffff"+this.getClass().getName()+" || and i= "+i +" and this.i = "+this.i);
        aa();
    }
    public void aa(){
        System.out.println("father aa...........");
    }
}
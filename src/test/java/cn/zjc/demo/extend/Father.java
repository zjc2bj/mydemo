package cn.zjc.demo.extend;

public class Father {
    public int i = 1;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void print() {
        System.out.println(this.getClass().getName() + " | super | and i= " + this.i);
    }

    public void excute() {
        System.out.println(this.getClass().getName());
    }
}
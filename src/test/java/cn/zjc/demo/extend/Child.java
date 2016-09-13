package cn.zjc.demo.extend;

public class Child extends Father {
    public int i = 5;

    public void run() {
        super.print();
        this.print();
    }

    // public int getI() {
    // return i;
    // }

    public void print() {
        System.out.println(this.getClass().getName() + " | child | and i= " + this.i);
    }

    public static void main(String[] args) {
        new Child().run();
        System.out.println(new Child().getI());
        System.out.println(new Child().i);
        new Child().excute();
    }
}

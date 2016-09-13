package cn.zjc.demo.extend2;

public class Child extends Father implements Super1, Super {
    public int i = 5;

    public void run() {
        super.print();
        this.print();
    }

    // @Override
    public void print1() {
        super.print();
        System.out.println("zzzzzzzzzzzzzzzzzz" + this.getClass().getName() + " || and i= " + i + " and this.i = " + i);
    }

    public void aa() {
        System.out.println("child aa*********");
    }

    public static void main(String[] args) {
        new Child().excuteAll();
    }

    @Override
    public void excuteAll() {
        System.out.println("excuteAll..........");
    }
}

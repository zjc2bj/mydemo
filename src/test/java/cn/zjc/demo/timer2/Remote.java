package cn.zjc.demo.timer2;

public class Remote {
    // public String demo() {
    // int i = 0;
    // try {
    // while (i < 100) {
    // Thread.sleep(1000);
    // System.out.println("remote-" + i++);
    // }
    // return "remote....";
    // } catch (InterruptedException e) {
    // throw new RuntimeException(e);
    // }
    // }

    public String demo() {
        try {
            int i = 0;
            while (i >= 0) {
                // System.out.println("remote-" + i++);
                // System.out.println(".......");
            }
            return "remote....";
        } catch (Exception e) {
            System.out.println("Remote!!!!!!!!!!!!");
            e.printStackTrace();
        }
        return "remote....";
    }
}

package cn.zjc.demo;

import java.math.BigDecimal;

public class DoubleDemo {

    public static void main(String[] args) {
        Double d = new Double(2.333);
        System.out.println(d / 100);
        Double d2 = new Double(2.333);
        System.out.println(div(d2, 100, 2));
    }

    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    /**
     * double 相加
     * 
     * @param d1
     * @param d2
     * @return
     */
    public double sum(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2).doubleValue();
    }

    /**
     * double 相减
     * 
     * @param d1
     * @param d2
     * @return
     */
    public double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }

    /**
     * double 乘法
     * 
     * @param d1
     * @param d2
     * @return
     */
    public double mul(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }

    /**
     * double 除法
     * 
     * @param d1
     * @param d2
     * @param scale
     *            四舍五入 小数点位数
     * @return
     */
    public static double div(double d1, double d2, int scale) {
        // 当然在此之前，你要判断分母是否为0，
        // 为0你可以根据实际需求做相应的处理

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /*
     * 这样，计算double类型的数据计算问题就可以处理了。 另外补充一下 JavaScript 四舍五入的方法： 小数点问题
     */
    // Math.round(totalAmount*100)/100 //(保留 2 位)

    // function formatFloat(src, pos)
    // {
    // return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
    // }

}

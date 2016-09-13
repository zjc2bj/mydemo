package cn.zjc.demo.enums;

public enum Type {

    PAY_TYPE_ALIPAY(1), PAY_TYPE_HUI_FU(2), PAY_TYPE_TENPAY(3), // 财付通
    PAY_TYPE_AIRPAY(4), // 易航宝
    PAY_TYPE_AIRPAY_YHB(5);// 御航宝

    public int value;

    Type(int value) {
        this.value = value;
    }
    /*
     * public int getValue(){ return value; } public void setI(int value){
     * this.value = value; }
     */
}

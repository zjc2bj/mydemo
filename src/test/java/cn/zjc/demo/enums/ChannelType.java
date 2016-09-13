package cn.zjc.demo.enums;


public enum ChannelType {
    B2B(1, "B2B"), BSP(2, "BSP"), PLATFORM(3, "平台"), ;

    private ChannelType(int val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    private int val;

    private String desc;

    public int getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByVal(int val) {
        ChannelType[] values = ChannelType.values();
        for (ChannelType channelType : values) {
            if (channelType.getVal() == val) {
                return channelType.getDesc();
            }
        }
        return "";
    }

}

package cn.zjc.soa.vo.base;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractReturnCodeConstant {

    public static Map<String, String> messageMap = new HashMap<String, String>();

    public static final String SYS_SUCCESS = "-0000";

    public static final String SYS_ERROR_UNKNOWN = "-9999";

    /**
     * �����쳣
     */
    public static final String SYS_NET_EXCEPTION = "-8888";

    static {
        messageMap.put(SYS_SUCCESS, "ִ�гɹ�");

        messageMap.put(SYS_ERROR_UNKNOWN, "SYS_ERROR_UNKNOWN");

        messageMap.put(SYS_NET_EXCEPTION, "�����쳣");

    }
}

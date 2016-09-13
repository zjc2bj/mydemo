package com.zjc.log;

import java.io.File;
import java.io.IOException;

import cn.zjc.util.DateTools;

public class Log1 {
    public static String logPath = "c:\\syNotifyLog.txt";
    // 在加载LogWriter时(即准备写入数据之前)清空以前的数据
    static {
        try {
            File file = new File(logPath);
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void infoLog(String value) {
        new LogWriter(logPath).log(DateTools.getCurrentDateStrForLog() + value);
    }
}

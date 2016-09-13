package com.zjc.log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 用于输出的类
 * 
 * @author zjc
 * 
 */
public class LogWriter {
    // 可以写作配置：true写文件; false输出控制台
    // private static boolean fileLog = true;

    // 是否在源文件上进行追加
    private static boolean append = true;

    // 读取配置文件中的输出路径
    // private static String logFileName = LogWriter.getUrl();
    private OutputStream os;

    private String filePath;

    public LogWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 将字符串的数据写到输出流进行输出
     * 
     * @param info
     * @throws IOException
     */
    public void log(String info) {
        try {
            os = new FileOutputStream(filePath, append);
            writer(info);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件输出失败");
        } finally {
            realse();
        }
    }

    private void writer(String info) throws IOException, UnsupportedEncodingException {
        os.write((info + "\r\n").getBytes("utf-8"));
    }

    /**
     * 将字符串集合写到输出流进行输出
     * 
     * @param list
     */
    public void log(List<String> list) {
        try {
            os = new FileOutputStream(filePath, append);
            for (String str : list) {
                writer(str);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件输出失败");
        } finally {
            realse();
        }
    }

    private void realse() {
        if (os != null) {
            try {
                os.close();
                os = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

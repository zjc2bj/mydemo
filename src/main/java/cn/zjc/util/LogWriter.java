package cn.zjc.util;

import java.io.FileNotFoundException;
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
    private String filePath;

    private boolean append = true;

    public OutputStream getOutputStream() {
        try {
            // 未指定文件--控制台输出
            if (filePath == null && filePath.trim().equals("")) {
                return System.out;
            }

            FileUtils.initFile(filePath);
            // System.out.println(file.getAbsolutePath());
            return new FileOutputStream(filePath, append);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("输出流异常");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件创建失败");
        }
    }

    /**
     * 默认构造--输出到控制台
     */
    public LogWriter() {
    }

    /**
     * @param filePath
     *            默认文件后追加
     */
    public LogWriter(String filePath) {
        this(filePath, true);
    }

    /**
     * @param filePath
     *            输出路径
     * @param append
     *            是否文件后追加
     */
    public LogWriter(String filePath, boolean append) {
        this.filePath = filePath;
        this.append = append;
    }

    /**
     * 将字符串的数据写到输出流进行输出
     * 
     * @param info
     * @throws IOException
     */
    public void log(String info) {
        OutputStream outputStream = getOutputStream();

        try {
            writer(info, outputStream);
            System.out.println(info);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件输出失败");
        } finally {
            realse(outputStream);
        }
    }

    private void writer(String info, OutputStream outputStream) throws IOException, UnsupportedEncodingException {
        outputStream.write((info + "\r\n").getBytes("utf-8"));
    }

    /**
     * 将字符串集合写到输出流进行输出
     * 
     * @param list
     */
    public void log(List<String> list) {
        OutputStream os = getOutputStream();
        try {
            for (String str : list) {
                writer(str, os);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件输出失败");
        } finally {
            realse(os);
        }
    }

    private void realse(OutputStream os) {
        if (os != null) {
            try {
                os.close();
                os = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // threadsWriterDemo();
        log();
    }

    public static void log() {
        LogWriter log1 = new LogWriter("aaa.txt");
        // System.out.println(log1.getOutputStream());
        log1.log("11111111");
        LogWriter log2 = new LogWriter("/aaa.txt");
        // System.out.println(log2.getOutputStream());
        log2.log("22222222");
        LogWriter log3 = new LogWriter("c:/aaa.txt");
        // System.out.println(log2.getOutputStream());
        log3.log("22222222");
    }

    public static void threadsWriterDemo() {
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  start。。。。。。。。");
                LogWriter log1 = new LogWriter("c:\\1\\2\\aaa.txt");
                log1.log("11111111");
                System.out.println(Thread.currentThread().getName() + "  end。。。。。。。。");
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  start。。。。。。。。");
                LogWriter log2 = new LogWriter("c:\\1\\2\\aaa.txt");
                log2.log("22222222");
                System.out.println(Thread.currentThread().getName() + "  end。。。。。。。。");
            }
        }).start();
    }
}

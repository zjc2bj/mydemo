package cn.zjc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.zjc.copyfile.CopyDirectory;

import com.gd.properties.PropertiesLoader;

public class FileUtils {
    public static String getAttributes() {
        String filePath = new PropertiesLoader("").getValue("filePath");
        File file = new File(filePath);
        if (!file.exists())
            return "此文件不存在";

        // 文件最后的修改时间
        String lastModified = String.valueOf(file.lastModified());
        // 文件的大小
        String length = String.valueOf(file.length());

        return lastModified + length;
    }

    /**
     * 判断文件是否存在 如不存在 则创建
     */
    public static void initFile(String filePath) throws IOException {
        File file = new File(filePath);
        initFile(file);
    }

    /**
     * 判断文件是否存在 如不存在 则创建
     */
    public static void initFile(File file) throws IOException {
        if (!file.exists()) {// 文件不存在
            // 获取父目录
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            // 创建文件
            file.createNewFile();
        }
    }

    public static void refreshFileList(String strPath, long endTime) {
        File dir = new File(strPath);
        File[] files = dir.listFiles();

        if (files == null)
            return;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                refreshFileList(files[i].getAbsolutePath(), endTime);
            } else {
                long l = files[i].lastModified();

                if (l <= endTime) {
                    files[i].delete();
                }
            }
        }

    }

    public static void rmDir(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            String tmpPath = dir.getAbsolutePath();
            dir.delete();
            String path = tmpPath.substring(0, tmpPath.lastIndexOf("\\"));
            System.out.println(path);
            rmDir(path);
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                rmDir(files[i].getAbsolutePath());
            }
        }
    }

    // -----------------------------------------------------------
    public static void deleteFileByDate(String url, long date) {
        File file = new File(url);
        if (!file.exists())
            throw new RuntimeException("指定文件不存在");

        if (!file.isDirectory())
            throw new RuntimeException("指定文件不是文件夹 ");

        File[] listFiles = file.listFiles();
        deleteFile(listFiles, date);

    }

    private static void deleteFile(File[] arr, long date) {
        if (arr.length > 0) {
            for (File file : arr) {
                if (file.isDirectory()) {
                    deleteFile(file.listFiles(), date);
                } else {
                    long lastModified = file.lastModified();
                    if (lastModified > date)
                        file.delete();
                }
            }
        }
    }

    public static List<String> readTxtFile(String absPath) {
        try {
            return readTxtFile(new FileInputStream(absPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> readTxtFile(InputStream in) {
        BufferedReader bufReader = null;
        try {
            bufReader = new BufferedReader(new InputStreamReader(in));
            List<String> list = new ArrayList<String>();
            String lineTxt = null;
            while ((lineTxt = bufReader.readLine()) != null) {
                list.add(lineTxt);
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (bufReader != null) {
                    bufReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 复制文件/文件夹 到 指定文件夹
     * 
     * @param srcDirOrFile
     *            文件 /文件夹
     * @param target
     *            目标文件夹
     * @throws IOException
     */
    public static void copy(String srcDirOrFile, String target) throws Exception {
        File srcFile = new File(srcDirOrFile);
        File targetFile = new File(target);

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        if (!srcFile.isDirectory()) {// 复制文件
            copyFile(srcFile, new File(targetFile, srcFile.getName()));
        }

        // 复制目录
        copyDirectory(srcFile, targetFile);
    }

    /**
     * 复制文件夹:将一个文件夹下的所有文件复制到目标文件夹下 如：c:\a--> d:\
     * 
     * @param srcFile
     *            源文件夹
     * @param targetParentFile
     *            目标路径的父路径 如：d:\
     * @throws IOException
     */
    public static void copyDirectory(File srcFile, File targetParentFile) throws IOException {
        // 在目标文件夹下创建同名文件夹
        File targetDirFile = new File(targetParentFile, srcFile.getName());

        // Assert.isTrue(!targetFile.isDirectory(), "目标文件不是文件夹");
        if (!targetDirFile.exists()) {
            targetDirFile.mkdirs();
        }

        File[] files = srcFile.listFiles();
        if (files == null || files.length < 1)
            return;

        for (File file : files) {
            if (file.isFile()) {
                copyFile(file, new File(targetDirFile, file.getName()));
                continue;
            }
            copyDirectory(file, targetDirFile);
        }

    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        // 关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    public static void main(String[] args) {
        try {
            // copy("C:\\a", "D:\\");
            new CopyDirectory().copy("C:\\a", "D:\\a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

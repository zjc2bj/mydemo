package com.gd.compare;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import cn.zjc.util.LogWriter;

import com.gd.xml.FileConfigAnalysis;

/**
 * 比较两个文件夹的内容
 * 
 * @author zjc
 * 
 */
public class FolderCompare {
    public String output;

    private List<String> resultList = new ArrayList<String>();// 比较后不相同的信息集合

    // private int lenth1 = 0;
    // private int lenth2 = 0;
    @Test
    /**
     * 比较两个文件夹的内容，并将比较的不同的结果  按格式输出
     */
    public void compare() {
        Map<String, String> foldersConfigMap = FileConfigAnalysis.getFoldersConfigMap();
        // 将map中的output取出 并删除map里的相对应的键值
        output = foldersConfigMap.get("output");
        foldersConfigMap.remove("output");

        for (Entry<String, String> entry : foldersConfigMap.entrySet()) {
            String filepath1 = entry.getKey();
            String filepath2 = entry.getValue();

            File file1 = new File(filepath1);
            File file2 = new File(filepath2);

            // lenth1=file1.getPath().length()-file1.getName().length();
            // lenth2=file2.getPath().length()-file2.getName().length();

            if (!(file1.exists() && file2.exists())) {
                throw new RuntimeException("指定的文件目录不存在\r\n");
            }
            if (!(file1.isDirectory() && file2.isDirectory())) {
                throw new RuntimeException("指定的文件不是目录\r\n");
            }
            listFile(file1, file2);
        }
        new LogWriter(output).log(resultList);
    }

    public List<String> listFile(File file1, File file2) {
        File[] flist1 = file1.listFiles();
        File[] flist2 = file2.listFiles();

        List<File> list1;
        List<File> list2;

        if ((flist1.length == 0 || flist2.length == 0) && flist1.length != flist2.length) {
            resultList.add(file1.toString() + "和\r\n" + file2.toString() + "下的文件数目不同\r\n\r\n");
            return resultList;
        }
        list1 = converse(flist1);
        list2 = converse(flist2);
        // resultList.add(file1.toString()+"和"+file2.toString()+"下的文件数目不同");
        for (File lfile1 : list1) {
            int status = 0;
            for (File lfile2 : list2) {
                if (lfile1.getName().equals(lfile2.getName())) {
                    status = 1;
                    if ((lfile1.isDirectory() && lfile2.isDirectory())) {
                        listFile(lfile1, lfile2);
                        continue;
                    }
                    if (lfile1.length() != lfile2.length()) {
                        // resultList.add(file1.toString()+"目录下的"+subFile1.getName()+"与"+file2.toString()+"目录下的"+subFile1.getName()+"内容不相同");
                        resultList.add(lfile1.toString() + "  与\r\n" + lfile2.toString() + "  内容不相同\r\n\r\n");
                        continue;
                    }
                }
            }
            if (status == 0) {
                resultList.add(lfile1.toString() + "  在 \r\n" + file2.toString() + "  下无对应文件\r\n\r\n");
            }
        }
        for (File lfile2 : list2) {
            int status = 0;
            for (File lfile1 : list1) {
                if (lfile2.getName().equals(lfile1.getName())) {
                    status = 1;
                    if ((lfile1.isDirectory() && lfile1.isDirectory())) {
                        listFile(lfile1, lfile1);
                    }
                    continue;
                }
            }
            if (status == 0) {
                resultList.add(lfile2.toString() + "  在\r\n" + file1.toString() + "  下无对应文件\r\n\r\n");
            }
        }
        return resultList;
    }

    public List<File> converse(File[] arr) {
        List<File> list = new ArrayList<File>();
        for (File file : arr) {
            list.add(file);
        }
        return list;
    }

    public static void main(String[] args) {
        new FolderCompare().compare();
    }
}

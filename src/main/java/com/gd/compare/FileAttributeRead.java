package com.gd.compare;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import cn.zjc.util.LogWriter;

import com.gd.xml.FileConfigAnalysis;

public class FileAttributeRead {

    @Test
    public void getAttributes() {
        Map<String, String> fileConfigMap = FileConfigAnalysis.getFileConfigMap();
        String output = fileConfigMap.get("output");
        fileConfigMap.remove("output");// 删除output=""

        List<String> resultList = new ArrayList<String>();

        for (Entry<String, String> entry : fileConfigMap.entrySet()) {
            String fileName = entry.getKey();
            String filePath = entry.getValue();

            File file = new File(filePath);
            if (!file.exists())
                throw new RuntimeException("此文件不存在");

            // 文件最后的修改时间
            String lastModified = String.valueOf(file.lastModified());
            // 文件的大小
            String length = String.valueOf(file.length());
            String result = lastModified + length;

            resultList.add(fileName + "对应的属性值为" + result);
        }
        LogWriter writer = new LogWriter(output);
        writer.log(resultList);
    }

    public static void main(String[] args) {
        new FileAttributeRead().getAttributes();
    }
}

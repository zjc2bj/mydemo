package cn.zjc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FirstChar2Case {

    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\temp.csv"),"gbk"));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:\\aaa.csv"),"gbk")) ;
            String str;
            while ((str = br.readLine()) != null) {
                StringBuffer buffer = new StringBuffer();
                String[] split = str.split("_");
                buffer.append(split[0]);
                for (int i = 1; i < split.length; i++) {
                    String filed = split[i];
                    String value = String.valueOf(filed.charAt(0)).toUpperCase()+""+filed.substring(1, filed.length());
                    buffer.append(value);
                }
                bw.write(buffer.toString());
                bw.write("\n");
            }
            bw.flush();
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
}

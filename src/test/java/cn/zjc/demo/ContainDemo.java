package cn.zjc.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class ContainDemo {
    
    public static boolean isRefuse(String text){
        if(text.indexOf("航空公司运价失败HU网站提示")>-1 && text.indexOf("Err")>-1){
            return true;
        }
        if(text.indexOf("航空公司运价失败HU网站提示")>-1 && text.indexOf("PNR不存在或已经删除")>-1){
            return true;
        }
        if(text.indexOf("必须是")>-1){
            return true;
        }
        //只有满足PNR的状态
        if(text.indexOf("只有满足PNR的状态")>-1){
            return true;
        }
        return false;
        
    }
    
    public static void main(String[] args) throws Exception {
        InputStreamReader reader = new InputStreamReader(new FileInputStream("c:\\temp.txt"),"GBK");
        BufferedReader bufread = new BufferedReader(reader);
        String value;
        while((value = bufread.readLine())!=null){
            //System.out.println("**************************"+value);
            if(isRefuse(value)){
                System.out.println(value);
            }
        }
        //reader.r
    }
}

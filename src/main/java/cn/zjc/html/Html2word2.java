package cn.zjc.html;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Html2word2 {
	public static void htmlToWord(String html, String docFile) {    
        ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word        
        try {    
            app.setProperty("Visible", new Variant(false));    
            Dispatch docs = app.getProperty("Documents").toDispatch();    
            Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[] { html, new Variant(false), new Variant(true) }, new int[1]).toDispatch();    
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { docFile, new Variant(1) }, new int[1]);    
            Variant f = new Variant(false);    
            Dispatch.call(doc, "Close", f);    
        } catch (Exception e) {    
            e.printStackTrace();    
        } finally {    
            app.invoke("Quit", new Variant[] {});    
            ComThread.Release();    
        }    
    }   
    public static void main(String[] args){  
         String fileName = "E:/a.doc";  
         String content = "E:/index.html";  
         htmlToWord(content,fileName);  
    }  

}

package cn.zjc.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;


public class SqlResult2EntityUtils {
    public static List excuteQuerry(String sqlString,String[] para,Class cla){
        Connection connection=null;
        PreparedStatement pStatement=null;
        ResultSet rSet=null;
        connection=getConnection();
        List list = new ArrayList();
        
        try {
         pStatement=connection.prepareStatement(sqlString);
         addParam(para, pStatement);
         rSet=pStatement.executeQuery();               //执行查询操作
         ResultSetMetaData rsmd= rSet.getMetaData();         //用于获取ResultSet对象中列的类型和属性信息
         int count= rsmd.getColumnCount();           //获得rSet中有多少列
         String[] columnName=new String[count];
         for(int i=0;i<count;i++){
          columnName[i]=rsmd.getColumnName(i+1);       //得到rSet结果集中的所有列的名字
         }

           //PropertyUtils主要是利用反射机制对JavaBean的属性进行处理。支持不同类型的自行转换

           //得到JavaBean的所有属性的描述
           PropertyDescriptor[] pd=PropertyUtils.getPropertyDescriptors(cla);
           while(rSet.next()){
            Object obj=null;
            try {
            obj = cla.newInstance();          //创建新对象，便于存储
            } catch (Exception e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
            } 
            for(int i=0;i<count;i++){
             for(int j=0;j<pd.length;j++){
              if(columnName[i].equalsIgnoreCase(pd[j].getName())){
               Object value=rSet.getObject(columnName[i]);       //从数据库获得对应属性的值
               if(value!=null){
                try {

               //BeanUtils提供对Java反射和自省API的包装。其主要目的是利用反射机制对JavaBean的属性进行处理，不支持数据类型的自动转换
              BeanUtils.setProperty(obj,columnName[i],value);     //通过BeanUtils对JavaBean进行赋值
              list.add(obj);
             } catch (IllegalAccessException e) {
              // TODO 自动生成 catch 块
              e.printStackTrace();
             } catch (InvocationTargetException e) {
              // TODO 自动生成 catch 块
              e.printStackTrace();
             }
               }
              }
             }
            }
           }
           
        } catch (SQLException e) {
         throw new RuntimeException("执行非查询操作出错",e);
        }finally{
         closeAll(null, pStatement, connection);
        } 
        return list;
       }

    private static void closeAll(ResultSet rs, PreparedStatement pStatement, Connection connection) {
        // TODO Auto-generated method stub
        
    }

    private static void addParam(String[] para, PreparedStatement pStatement) {
        // TODO Auto-generated method stub
        
    }

    private static Connection getConnection() {
        // TODO Auto-generated method stub
        return null;
    }

}

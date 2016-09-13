package cn.zjc.util;

public class StringUtil {
    /**
     * 字符串首字母大写
     * @param str
     * @return
     */
    public static String replaceFirstChar2Upcase(String str){
        String value = String.valueOf(str.charAt(0)).toUpperCase()+""+str.substring(1, str.length());
        return value;
    }
    
    /**
     * 字符串(/文本) 以分隔符进行分割，将首字母大写
     * 如：seat_class_discount --> seatClassDiscount
     * @param text
     * @param separator
     * @return
     */
    public static String replaceFirstCharBySeparator(String text,String separator){
        StringBuffer strBuf = new StringBuffer();
        String[] splits = text.split(separator);
        strBuf.append(splits[0]);
        for (int i = 1; i < splits.length; i++) {
            String filed = splits[i];
            strBuf.append(replaceFirstChar2Upcase(filed));
        }
        return strBuf.toString();
    }
    
    public static void main(String[] args) {
        String str = replaceFirstCharBySeparator("seat_class_discount", "_");
        System.out.println(str);
    }
    
    public static boolean isBlank(String str){
        if(str == null || str.equals(""))
            return true;
        return false;
    }
    
    public static boolean isTrimBlank(String str){
        if(str == null || str.trim().equals(""))
            return true;
        return false;
    }
}

package cn.zjc.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    public static void main(String[] args) {
        // test1();
        // test2();
        // String patternStr = "^\\d{4}(\\-)\\d{2}(\\-)\\d{2}$";
        final String datePattern = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?"
                + "[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))"
                + "|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|"
                + "(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12"
                + "35679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))"
                + "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
                + "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[" + "1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pattern = Pattern.compile(datePattern);
        String value = "2011-1211";
        System.out.println(pattern.matcher(value).matches());
    }

    private static void test2() {
        boolean matches = Pattern.compile("^(-|\\+)?(\\d+|(\\d+\\.\\d+))").matcher("1").matches();
        System.out.println(matches);
    }

    private static void test1() {
        String str = "cu131*****313\n";
        String regex = "^cu.*\n$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        System.out.println(b);
    }
}

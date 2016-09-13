package cn.zjc.groovy;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import cn.zjc.util.DateUtils;

public class GroovyScriptEngineDemo {
    public static void main(String args[]) {
        test1();
    }

    private static void test1() {
        try {
            String[] roots = new String[] { "groovy-file/SimpleScript.groovy" };// 定义Groovy脚本引擎的根路径
            GroovyScriptEngine engine = new GroovyScriptEngine(roots);
            Binding binding = new Binding();
            binding.setVariable("language", "Groovy");
            binding.setVariable("dateUtils", new DateUtils());
            Object value = engine.run("SimpleScript.groovy", binding);
            assert value.equals("The End");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

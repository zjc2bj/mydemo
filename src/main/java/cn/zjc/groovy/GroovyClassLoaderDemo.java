package cn.zjc.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

public class GroovyClassLoaderDemo {
    public static void main(String args[]) {
        try {
            GroovyClassLoader loader = new GroovyClassLoader();
            Class fileCreator = loader.parseClass(new File("groovy-file/GroovySimpleFileCreator.groovy"));
            GroovyObject object = (GroovyObject) fileCreator.newInstance();
            object.invokeMethod("createFile", "C:\\emptyFile.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

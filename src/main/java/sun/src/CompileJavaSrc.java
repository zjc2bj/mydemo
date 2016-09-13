package sun.src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.junit.Test;

public class CompileJavaSrc {

    /**
     * StandardJavaFileManager
     * 实现此接口的每个编译器都提供一个标准的文件管理器，以便在常规文件上进行操作。StandardJavaFileManager
     * 接口定义了从常规文件创建文件对象的其他方法。 标准文件管理器有两个用途：
     * 
     * 自定义编译器如何读写文件的基本构建块 在多个编译任务之间共享 重新使用文件管理器可能会减少扫描文件系统和读取 jar
     * 文件的开销。标准文件管理器必须与多个顺序编译共同工作，尽管这样做并不能减少开销，下例是建议的编码模式：
     */
    public void test1() {
        try {
            File[] files1 = null; // input for first compilation task
            File[] files2 = null; // input for second compilation task

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

            Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(Arrays
                    .asList(files1));
            compiler.getTask(null, fileManager, null, null, null, compilationUnits1).call();

            Iterable<? extends JavaFileObject> compilationUnits2 = fileManager.getJavaFileObjects(files2); // use
                                                                                                           // alternative
                                                                                                           // method
            // reuse the same file manager to allow caching of jar files
            compiler.getTask(null, fileManager, null, null, null, compilationUnits2).call();

            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * DiagnosticCollector 用于将诊断信息收集在一个列表中，例如：
     */
    @Test
    public void test2() {
        try {
            JavaSourceFromString fileObject = new JavaSourceFromString("sun.src.Compile", "System.out.println(1+1)");

            ArrayList<JavaFileObject> list = new ArrayList<JavaFileObject>();
            list.add(fileObject);
            Iterable<? extends JavaFileObject> compilationUnits = list;
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            Boolean call = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();
            System.out.println(call);

            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics())
                System.out.format("Error on line %d in %d%n", diagnostic.getLineNumber(),
                        ((FileObject) diagnostic.getSource()).toUri());

            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ForwardingJavaFileManager、ForwardingFileObject 和 ForwardingJavaFileObject
     * 子类化不可用于重写标准文件管理器的行为
     * ，因为标准文件管理器是通过调用编译器上的方法创建的，而不是通过调用构造方法创建的。应该使用转发（或委托）。允许自定义行为时
     * ，这些类使得将多个调用转发到给定文件管理器或文件对象变得容易。例如，考虑如何将所有的调用记录到 JavaFileManager.flush()：
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void test3() {
        final Logger logger = Logger.getLogger("");
        Iterable<? extends JavaFileObject> compilationUnits = null;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager stdFileManager = compiler.getStandardFileManager(null, null, null);
        JavaFileManager fileManager = new ForwardingJavaFileManager(stdFileManager) {
            public void flush() {
                logger.entering(StandardJavaFileManager.class.getName(), "flush");
                // super.flush();
                logger.exiting(StandardJavaFileManager.class.getName(), "flush");
            }
        };
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
    }
}

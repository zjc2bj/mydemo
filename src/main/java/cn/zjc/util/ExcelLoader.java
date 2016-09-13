package cn.zjc.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelLoader {
    // private static Workbook workbook = null;
    //
    // // 解析的表格输出路径
    // public static final String TARGET_EXCEL_PATH = "C:\\1111.xls";
    //
    // // 类加载的时候 先指定包含目标文件的workbook
    // static {
    // workbook = loadWorkBook(TARGET_EXCEL_PATH);
    // }

    private static Workbook loadWorkBook(String srcFile) {
        try {
            Workbook workBook = Workbook.getWorkbook(new File(srcFile));
            return workBook;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将每行的数据 转化成map(包含表格头)。如：<br>
     * [{id=1,gmt=2014-11,status=1...},{id=2,gmt=2014-11,status=3...}]
     * 
     * @return
     */
    public static List<Map<String, Object>> loadExcel(String srcFile) {
        Workbook workBook = loadWorkBook(srcFile);
        Sheet sheet = workBook.getSheet(0);

        // 总宽度(条数)
        int colSize = sheet.getColumns();
        // 总长度(条数)
        int rowSize = sheet.getRows();

        // 表格头
        Cell[] titles = sheet.getRow(0);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        // 取每行的数据 封装成map
        for (int i = 1; i < rowSize; i++) {
            Map<String, Object> rowMap = new HashMap<String, Object>();
            Cell[] columns = sheet.getRow(i);
            for (int j = 0; j < colSize; j++) {
                rowMap.put(titles[j].getContents(), columns[j].getContents());
            }

            list.add(rowMap);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Map<String, Object>> loadExcel = loadExcel("C:\\1111.xls");
        System.out.println(loadExcel.size());
    }
}

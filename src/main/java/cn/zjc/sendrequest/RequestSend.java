package cn.zjc.sendrequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.zjc.bean.ShengYiNotifyRecord;
import cn.zjc.util.ExcelLoader;
import cn.zjc.util.FileUtils;
import cn.zjc.util.LogWriter;
import cn.zjc.util.XmlFormat;
import cn.zjc.util.bean.BeanConvertUtil;
import cn.zjc.util.jdbc.ORMConfigLoader;

import com.lt.utils.HttpClientUtil;

public class RequestSend {
    public static final String srcFile = "C:\\1111.xls";

    public static void main(String[] args) {
        sendRequestByExcel(srcFile, null);
    }

    public static List<String> sendRequestByExcel(String filePath, String resultPath) {
        LogWriter logWriter = new LogWriter(resultPath);
        List<String> list = new ArrayList<String>();
        String result = "";

        // 读取字段映射
        Map<String, String> fileMap = ORMConfigLoader.loadORM(ShengYiNotifyRecord.class);
        // 加载Excel数据(包含头信息)
        List<Map<String, Object>> excel = ExcelLoader.loadExcel(filePath);
        for (Map<String, Object> map : excel) {
            ShengYiNotifyRecord bean;
            try {
                bean = BeanConvertUtil.map2Bean(map, ShengYiNotifyRecord.class, fileMap);
            } catch (Exception e2) {
                list.add("实体转换异常");
                list.add(e2.getMessage());
                return list;
            }

            try {
                String reqUrl = bean.getReqUrl();
                Map<String, String> reqMap = HttpClientUtil.convertStr2Map(bean.getReqTxt());

                String rspTxt = HttpClientUtil.requestAsHttpPOST(reqUrl, reqMap, "UTF-8");
                if (StringUtils.isBlank(rspTxt))
                    throw new RuntimeException("返回值为空");

                result = "订单号：" + bean.getOutOrderNo() + "-->推单成功! ==> 返回参数：[" + rspTxt + "]";
            } catch (NullPointerException e1) {
                result = "订单号：" + bean.getOutOrderNo() + "-->推单失败! ==> 异常信息：[转化实体失败!]";
                e1.printStackTrace();
            } catch (Exception e) {
                result = "订单号：" + bean.getOutOrderNo() + "-->推单失败! ==> 异常信息：[" + e.getMessage() + "]";
                e.printStackTrace();
            }

            System.out.println(result);
            logWriter.log(result);
            list.add(result);
        }

        return list;
    }

    public static void sendRequestByFile(String filePath) {
        List<String> list = FileUtils.readTxtFile(filePath);
        for (String req : list) {
            String[] split = req.split("\\,");

            Map<String, String> reqMap = HttpClientUtil.convertStr2Map(split[1]);
            System.out.println("reqMap= " + reqMap);
            String rspTxt = HttpClientUtil.requestAsHttpPOST(split[0], reqMap, "UTF-8", 30000, 30000);

            if (StringUtils.isBlank(rspTxt))
                throw new RuntimeException("返回值为空");

            String rspFormatTxt = XmlFormat.xmlPrettyFormat(rspTxt);
            System.out.println("推单成功" + rspFormatTxt);
        }
    }
}

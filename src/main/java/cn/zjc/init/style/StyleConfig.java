package cn.zjc.init.style;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zjc.init.InitConfig;

public class StyleConfig extends InitConfig<StyleRef> {
    @Override
    public StyleRef refrushLoad(File confFile) {
        StyleRef styleRef = new StyleRef();

        List<TemplateRef> latestLoginTemplates = new ArrayList<TemplateRef>();
        List<TemplateRef> commonLoginTemplates = new ArrayList<TemplateRef>();
        Map<String, List<TemplateRef>> agencyLoginTemplates = new HashMap<String, List<TemplateRef>>();
        List<TemplateRef> commonInnerTemplates = new ArrayList<TemplateRef>();
        Map<String, List<TemplateRef>> agencyInnerTemplates = new HashMap<String, List<TemplateRef>>();

        int linenum = 0;
        try {
            // 解析：
            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(confFile), "utf-8"));
            String str;

            while ((str = br.readLine()) != null) {
                linenum++;
                try {
                    if (str.startsWith("#") || str.trim().equals(""))// 忽略注释和空行
                        continue;

                    TemplateRef templateRef = readRow2TemplateRef(str);
                    if (templateRef.getTag().equals("latestLogin")) {
                        latestLoginTemplates.add(templateRef);
                    }
                    if (templateRef.getTag().equals("commonLogin")) {
                        commonLoginTemplates.add(templateRef);
                    }
                    if (templateRef.getTag().equals("agencyLogin")) {
                        readAgencyRow(agencyLoginTemplates, templateRef);
                    }
                    if (templateRef.getTag().equals("commonInner")) {
                        commonInnerTemplates.add(templateRef);
                    }
                    if (templateRef.getTag().equals("agencyInner")) {
                        readAgencyRow(agencyInnerTemplates, templateRef);
                    }
                } catch (Exception e) {
                    System.out.println(confFile.getName() + "解析出错：[行号:" + linenum + "] " + e.getMessage());
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        styleRef.setAgencyInnerTemplates(agencyInnerTemplates);
        styleRef.setAgencyLoginTemplates(agencyLoginTemplates);
        styleRef.setCommonInnerTemplates(commonInnerTemplates);
        styleRef.setCommonLoginTemplates(commonLoginTemplates);
        styleRef.setLatestLoginTemplates(latestLoginTemplates);

        return styleRef;
    }

    /**
     * 将包含agency数据 解析添加到对应的集合
     * 
     * @param agencyInnerTemplates
     * @param templateRef
     */
    private void readAgencyRow(Map<String, List<TemplateRef>> agencyTemplates, TemplateRef templateRef) {
        String agencyCode = templateRef.getAgencyCode();
        String[] agencys = agencyCode.split("\\|");

        for (String code : agencys) {
            code = code.trim();
            List<TemplateRef> list = agencyTemplates.get(code);
            if (list == null) {
                list = new ArrayList<TemplateRef>();
            }
            list.add(templateRef);
            if (agencyTemplates.get(code) == null)
                agencyTemplates.put(code, list);
        }
    }

    private TemplateRef readRow2TemplateRef(String str) {
        if (str.endsWith(","))
            str += " ";
        if (str.contains(",,"))
            str.replace(",,", ", ,");
        String[] arr = str.split("\\,");

        if (arr.length < 6) {
            throw new RuntimeException("配置错误：缺少内容或','");
        }

        TemplateRef template = new TemplateRef();
        template.setTag(arr[0].trim());
        template.setLoginStyle(arr[1].trim());
        template.setBigPage(arr[2].trim());
        template.setSmallPage(arr[3].trim());
        template.setAgencyCode(arr[4].trim());
        if (arr[5].trim().equals("") && !template.getBigPage().equals("")) {// 排除内页模板
            template.setPath("/project/51book_cg/login/DIYScroll/" + arr[1].trim() + "/scroll1.jpg");
        } else {
            template.setPath(arr[5].trim());
        }
        return template;
    }
}

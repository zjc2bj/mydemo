package cn.zjc.init.tour;

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

public class TourMenuConfig extends InitConfig<TourMenuInfo> {

    @Override
    public TourMenuInfo refrushLoad(File confFile) {
        TourMenuInfo tourMenuInfo = new TourMenuInfo();

        Map<String, TourMenuRef> allTourMenus = new HashMap<String, TourMenuRef>();
        List<String> allTourMenuKeys = new ArrayList<String>();
        List<String> providerShowMenus = new ArrayList<String>();
        List<String> distributorShowMenus = new ArrayList<String>();
        List<String> providerFilterMenus = new ArrayList<String>();
        List<String> distributorFilterMenus = new ArrayList<String>();

        int linenum = 0;
        try {
            // 解析：
            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(confFile), "utf-8"));
            String str;

            while ((str = br.readLine()) != null) {
                linenum++;
                try {
                    if (str.contains("#") || str.trim().equals(""))// 忽略注释和空行
                        continue;

                    TourMenuRef tourMenuRef = readRow2TourMenuRef(str);

                    allTourMenus.put(tourMenuRef.getCrossKey(), tourMenuRef);
                    allTourMenuKeys.add(tourMenuRef.getCrossKey());
                    if (tourMenuRef.getProviderShow() == 1) {
                        providerShowMenus.add(tourMenuRef.getCrossKey());
                    } else {
                        providerFilterMenus.add(tourMenuRef.getCrossKey());
                    }
                    if (tourMenuRef.getDistributorShow() == 1) {
                        distributorShowMenus.add(tourMenuRef.getCrossKey());
                    } else {
                        distributorFilterMenus.add(tourMenuRef.getCrossKey());
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

        tourMenuInfo.setAllTourMenus(allTourMenus);
        tourMenuInfo.setAllTourMenuKeys(allTourMenuKeys);
        tourMenuInfo.setDistributorFilterMenus(distributorFilterMenus);
        tourMenuInfo.setDistributorShowMenus(distributorShowMenus);
        tourMenuInfo.setProviderFilterMenus(providerFilterMenus);
        tourMenuInfo.setProviderShowMenus(providerShowMenus);
        return tourMenuInfo;
    }

    private TourMenuRef readRow2TourMenuRef(String str) {
        if (str.endsWith(","))
            str += " ";
        if (str.contains(",,"))
            str.replace(",,", ", ,");
        String[] arr = str.split("\\,");

        if (arr.length < 4) {
            throw new RuntimeException("配置错误：缺少内容或','");
        }

        TourMenuRef tourMenuRef = new TourMenuRef();
        tourMenuRef.setCrossKey(arr[0].trim());
        tourMenuRef.setMenuName(arr[1].trim());
        tourMenuRef.setProviderShow(Integer.parseInt(arr[2].trim()));
        tourMenuRef.setDistributorShow(Integer.parseInt(arr[3].trim()));

        return tourMenuRef;
    }
}

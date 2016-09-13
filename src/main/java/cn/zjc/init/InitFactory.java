package cn.zjc.init;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.core.io.ClassPathResource;

import cn.zjc.init.style.StyleRef;
import cn.zjc.init.tour.TourMenuInfo;


/**
 * 资源加载
 * 
 * @author zhujunchao
 * 
 */
public class InitFactory {
    public static StyleRef getStyleRef() {
        return InitHelper.getStyleRef();
    }

    public static TourMenuInfo getTourMenuConfig() {
        return InitHelper.getTourMenuConfig();
    }

    /** 获取配置文件 */
    public static File getResourceFile(String resourcePath) throws URISyntaxException, IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        return resource.getFile();
    }

    public static void main(String[] args) {
        TourMenuInfo tourMenuref = getTourMenuConfig();
        System.out.println("分销：" + tourMenuref.getDistributorFilterMenus());
        System.out.println("供应：" + tourMenuref.getProviderFilterMenus());
        System.out.println("所有：" + tourMenuref.getAllTourMenus());
        System.out.println(getStyleRef());
    }
}

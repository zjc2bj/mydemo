package cn.zjc.init;

import cn.zjc.init.style.StyleConfig;
import cn.zjc.init.style.StyleRef;
import cn.zjc.init.tour.TourMenuConfig;
import cn.zjc.init.tour.TourMenuInfo;


/** @author zhujunchao */
public class InitHelper {
    private static InitConfig<TourMenuInfo> tourMenuConfig = new TourMenuConfig();

    private static InitConfig<StyleRef> styleConfig = new StyleConfig();

    public static final String RESOURCE_CONFIG_PATH_STYLE = "init/style/templatestyle_tongye.txt";// 配置文件路径

    public static final String RESOURCE_CONFIG_PATH_MENU = "init/tour/tour_menu.txt";// 配置文件路径

    public static TourMenuInfo getTourMenuConfig() {
        return tourMenuConfig.loadConfig(RESOURCE_CONFIG_PATH_MENU);
    }

    public static StyleRef getStyleRef() {
        return styleConfig.loadConfig(RESOURCE_CONFIG_PATH_STYLE);
    }
}

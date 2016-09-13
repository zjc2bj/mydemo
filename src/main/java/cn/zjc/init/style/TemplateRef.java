package cn.zjc.init.style;

import java.io.Serializable;

/**
 * 配置文件每行对应的实体
 */
public class TemplateRef implements Serializable {
    private static final long serialVersionUID = 2570093792719875891L;

    private String tag;// 改条模板所属块：最新推荐、更多精彩

    private String loginStyle;

    private String bigPage;

    private String smallPage;

    /**
     * 使用该模板的供应代码 如果为common 则为公用模板
     */
    private String agencyCode;

    /**
     * 将图片存到表中的路径： PUBLIC_STATIC_HREF = {@link#PropertyUtil}
     * .getProperty("public.static.href",
     * "/config/properties/servers.properties"); <br>
     * 默认值：path=PUBLIC_STATIC_HREF + "/project/51book_cg/login/DIYScroll/" +
     * loginStyle + "/scroll1.jpg")
     */
    private String path;

    public String getLoginStyle() {
        return loginStyle;
    }

    public String getBigPage() {
        return bigPage;
    }

    public String getSmallPage() {
        return smallPage;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public String getPath() {
        return path;
    }

    public void setLoginStyle(String loginStyle) {
        this.loginStyle = loginStyle;
    }

    public void setBigPage(String bigPage) {
        this.bigPage = bigPage;
    }

    public void setSmallPage(String smallPage) {
        this.smallPage = smallPage;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}

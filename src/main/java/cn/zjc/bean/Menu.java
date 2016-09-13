package cn.zjc.bean;

public class Menu {

    private String name;

    private String url;

    private Menu subMenu;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Menu getSubMenu() {
        return subMenu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSubMenu(Menu subMenu) {
        this.subMenu = subMenu;
    }
}

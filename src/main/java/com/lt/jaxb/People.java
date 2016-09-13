package com.lt.jaxb;


public class People {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public People() {
    }

    /**
     * @param url
     */
    public People(String url) {
        super();
        this.url = url;
    }

}

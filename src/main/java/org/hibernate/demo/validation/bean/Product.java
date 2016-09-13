package org.hibernate.demo.validation.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.demo.validation.annotation.Price;


public class Product {
    // 必须非空
    @NotNull
    private String productName;

    // 必须在 8000 至 10000 的范围内
    // @Price 是一个定制化的 constraint
    @Price
    private float price;

    public String getProductName() {
        return productName;
    }

    public float getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}

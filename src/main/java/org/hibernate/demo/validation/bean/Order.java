package org.hibernate.demo.validation.bean;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.demo.validation.annotation.Status;
import org.hibernate.validator.constraints.NotEmpty;


public class Order {
    // 必须不为 null, 大小是 10
    @NotNull
    @Size(min = 10, max = 10)
    private String orderId;

    // 必须不为空
    // @NotEmpty
    @NotNull
    @Size(max = 10, min = 4)
    private String customer;

    // 必须是一个电子信箱地址
    // @Email
    private String email;

    // 必须不为空
    @NotEmpty
    private String address;

    // 必须不为 null, 必须是下面四个字符串'created', 'paid', 'shipped', 'closed'其中之一
    // @Status 是一个定制化的 contraint
    @NotNull
    @Status
    private String status;

    // 必须不为 null
    @NotNull
    private Date createDate;

    // 嵌套验证
    @Valid
    private Product product;

    public String getOrderId() {
        return orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

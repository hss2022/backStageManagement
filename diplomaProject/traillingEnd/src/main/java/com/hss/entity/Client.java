package com.hss.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hss
 * @since 2022-11-23
 */
@Data
@TableName(value = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("phone")
    private String phone;
    @TableField("client_name")
    private String clientName;

    private String password;

    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
            "phone=" + phone +
            ", clientName=" + clientName +
            ", password=" + password +
            ", address=" + address +
        "}";
    }
}

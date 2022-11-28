package com.hss.recieve;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hss.entity.Dish1;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.List;
@Data
public class OrderMap {
    private String  orderId;

    private String orderTime;
    private String clientName ;
    private String phone;
    private String address;
    private List<Dish1>  dishes;

    public OrderMap(String orderId, String orderTime, String clientName, String phone, String address, List<Dish1> dishes) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.clientName = clientName;
        this.phone = phone;
        this.address = address;
        this.dishes = dishes;
    }

    public OrderMap() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Dish1> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish1> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "OrderMap{" +
                "orderId='" + orderId + '\'' +
                ", orderTime=" + orderTime +
                ", clientName='" + clientName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}

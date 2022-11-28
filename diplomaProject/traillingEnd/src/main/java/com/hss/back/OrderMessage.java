package com.hss.back;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hss.entity.Dish1;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.List;
@Data
public class OrderMessage {
    private Integer orderId;
    private  boolean isFinished;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp orderTime;
    private String clientName;
    private String phone;
    private String address;
    private  Integer payment;
    private List<Dish1>  dishes;
}

package com.hss;

import com.hss.entity.Dish1;
import com.hss.recieve.OrderMap;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestOrder {
    @Test
    public void testOrders(){
        OrderMap orderMap = new OrderMap();
        String createTime = "2022-5-11 13:24:15";
        try {
            orderMap.setOrderTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime).getTime())) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(orderMap.getOrderTime());
    }
    @Test
    public void testList(){
        List<Dish1> list =  new ArrayList<>();

    }
}

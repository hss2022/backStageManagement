package com.hss.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hss.entity.Checkout;
import com.hss.entity.Indent;
import com.hss.recieve.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hss
 * @since 2022-11-23
 */
public interface OrderMapper extends BaseMapper<Indent> {

    Checkout placeNewOrder(@Param("orderTime")String orderTime,
                           @Param("clientName")String clientName,
                           @Param("phone")String phone,
                           @Param("address")String address,
                           @Param("dish")List<Dish> dish
                           );
    List<Indent> selectAllOrder();

}
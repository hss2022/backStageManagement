package com.hss.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("dish")
public class Dish1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;
    @TableField("menu_name")
    private String menuName;
    @TableField("dish_quantity")
    private Integer dishQuantity;


}

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
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("menu_name")
    private String menuName;
    @TableField("menu_price")
    private Integer menuPrice;
    @TableField("is_residue")
    private Boolean isResidue;
    @TableField("today_sale")
    private Integer todaySale;


}

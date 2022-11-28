package com.hss.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hss.entity.Checkout;
import com.hss.entity.Menu;
import com.hss.mapper.MenuMapper;
import com.hss.recieve.Param;
import com.hss.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hss
 * @since 2022-11-23
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuServiceImpl menuService;
    @Autowired
    private MenuMapper menuMapper;
    @GetMapping("/sign")
    public List<Menu> selectSign(){
        List<Menu> list = new ArrayList<>();
        list.add(menuMapper.selectById("麻婆豆腐"));
        list.add(menuMapper.selectById("玉米排骨汤"));
        list.add(menuMapper.selectById("白斩鸡"));
        list.add(menuMapper.selectById("红烧狮子头"));
        return list;
    }
    @GetMapping("/all")
    public List<Menu> selectAllMenu(){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("menu_name","menu_price","today_sale","is_residue");
        queryWrapper.last("limit 9");
        return menuMapper.selectList(queryWrapper);
    }
    @GetMapping("/every")
    public List<Menu> selectEveryMenu(){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("menu_name","menu_price","today_sale","is_residue");
        return menuMapper.selectList(queryWrapper);
    }
    @PostMapping("/create")
    public Checkout  insertNew(@RequestBody Menu menu){
        Checkout checkout = new Checkout();
        checkout.setCheckout(menuService.saveOrUpdate(menu));
        return checkout;
    }
    @PostMapping("/delete")
    public  Checkout deleteMenu(@RequestBody Menu menu){
        Checkout checkout = new Checkout();
        checkout.setCheckout(false);
       int i= menuMapper.deleteById(menu.getMenuName());
       if (i>0)
        checkout.setCheckout(true);
       return  checkout;
    }
    @PostMapping("/page")
    public IPage<Menu> SelectMenuPage(@RequestBody Param param){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("menu_name");
        Page<Menu> page = new Page<>(param.getPageNum(), param.getPageSize());
        return menuService.page(page,queryWrapper);
    }
}

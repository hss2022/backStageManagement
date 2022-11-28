package com.hss.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hss.entity.Checkout;
import com.hss.entity.Client;
import com.hss.mapper.ClientMapper;
import com.hss.recieve.Param;
import com.hss.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private IClientService clientService;

    @PostMapping("/create")
    public Checkout CreateClient(@RequestBody Client client){
        System.out.println(client);
        Checkout checkout = new Checkout();
        checkout.setCheckout(clientService.save(client));
        return checkout;
    }
    @PostMapping("/page")
    public IPage<Client> SelectClientPage(@RequestBody Param param){
        System.out.println(param);
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("phone");
        Page<Client>  page = new Page<>(param.getPageNum(), param.getPageSize());
        System.out.println(clientService.page(page,queryWrapper));
        return clientService.page(page,queryWrapper);
    }
    @GetMapping("/all")
    public List<Client> SelectAll(){
      QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
      queryWrapper.select("phone","client_name","address").last("limit 10");
        return   clientMapper.selectList(queryWrapper);
    }
}

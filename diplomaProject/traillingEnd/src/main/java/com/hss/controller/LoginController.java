package com.hss.controller;

import com.hss.entity.Client;
import com.hss.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private ClientMapper clientMapper;
//    @PostMapping ("/login")
//    public Checkout clientLogin(@RequestBody Client client){
//        Checkout  checkout = new Checkout();
//        checkout.setCheckout(false);
//        if(client.getPhone().equals("13123085632")&&client.getPassword().equals("123456")) {
//                checkout.setCheckout(true);
//        }
//        return  checkout;
//    }
    @PostMapping ("/login")
    public Client clientLogin(@RequestBody Client client){

      Client client1 =  clientMapper.selectById(client.getPhone());
      if (client1.getPassword().equals(client.getPassword())){
          return  client1;
      }else {
          return  null;
      }
    }
}

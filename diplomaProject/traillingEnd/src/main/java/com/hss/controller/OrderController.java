package com.hss.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hss.back.ThreeCards;
import com.hss.entity.*;
import com.hss.mapper.DishMapper;
import com.hss.mapper.MenuMapper;
import com.hss.mapper.OrderMapper;
import com.hss.recieve.OrderMap;
import com.hss.recieve.Param;
import com.hss.service.impl.DishServiceImpl;
import com.hss.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishServiceImpl dishService;
    @Autowired
    private MenuMapper menuMapper;
    //首页的三张卡片
    @GetMapping("/cards")
    ThreeCards  getThreeParams(){
        ThreeCards threeCards = new ThreeCards();
        QueryWrapper<Indent> queryWrapper = new QueryWrapper<>();
        long finished = 0;
        long unfinished = 0;
        long finishedSum = 0;
        long unfinishedSum = 0;
        List<Indent> list = orderService.list(queryWrapper);
        for(Indent order : list){

            if(order.isFinished()) {
                finished++;
                if(order.getPayment()!=null) finishedSum+=order.getPayment();
            }
            else {
                unfinished++;
                if(order.getPayment()!=null) unfinishedSum+=order.getPayment();
            }

        }
        threeCards.setFinished(finished);
        threeCards.setUnfinished(unfinished);
        threeCards.setSum(finished+unfinished);
        threeCards.setFinishedSum(finishedSum);
        threeCards.setUnfinishedSum(unfinishedSum);
        threeCards.setSaleSum(finishedSum+unfinishedSum);
        return  threeCards;
    }
   @GetMapping("/all")
    List<Indent>  selectAllOrders(){
  return orderMapper.selectAllOrder();
   }
   //获取订单的详细信息：各种菜名和数量
   @PostMapping("/dishes")
    List<Dish1>  selectOrderDishes(@RequestBody Indent order){
        int id = order.getOrderId();
        QueryWrapper<Dish1> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",id);
       return dishService.list(queryWrapper);

   }
   //新订单
   @PostMapping(value = "/create")
   @Transactional(rollbackFor = Exception.class)
    Checkout placeANewOrder(@RequestBody OrderMap orderMap){
       Indent order = new Indent();
//       时间类型的转换
       String createTime =orderMap.getOrderTime();
       try {
           order.setOrderTime(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime).getTime())) ;
       } catch (ParseException e) {
           e.printStackTrace();
       }
//       生成不完整的新订单
       order.setClientName(orderMap.getClientName());
       order.setPhone(orderMap.getPhone());
       order.setAddress(orderMap.getAddress());
       orderMapper.insert(order);
       int id = order.getOrderId();
       Checkout checkout = new Checkout();
       List<Dish1> list = orderMap.getDishes();

       int sum = 0;
       for(Dish1 dish : list ){
           //计算该订单收入
           Menu menu = menuMapper.selectById(dish.getMenuName());
           sum += menu.getMenuPrice() * dish.getDishQuantity();
           //给菜单某个菜变更出售数目
           menu.setTodaySale(menu.getTodaySale()+dish.getDishQuantity());
           menuMapper.updateById(menu);
           //把Dish封装为Dish1存入数据库
           Dish1 dish1 =new Dish1();
           dish1.setOrderId(id);
           dish1.setMenuName(dish.getMenuName());
           dish1.setDishQuantity(dish.getDishQuantity());
           System.out.println(dish1);
           dishMapper.insert(dish1);
       }
//       最后把订单金额补上
       order.setPayment(sum);
       orderMapper.updateById(order);

      checkout.setCheckout(true);
       return checkout;
   }
   @PostMapping("/some")
    Checkout  createSomeDish(@RequestBody List<Dish1> dishes){
       Checkout checkout = new Checkout();
       checkout.setCheckout(dishService.saveBatch(dishes));
       return checkout;
   }
//   获取插入语句时自动增加的id   测试成功
   @PostMapping("/test")
    int  getAutoId(@RequestBody Indent newOrder){
        orderMapper.insert(newOrder);
       return newOrder.getOrderId();
   }
   //分页功能
   @PostMapping("/page")
   public IPage<Indent> SelectClientPage(@RequestBody Param param){
       QueryWrapper<Indent> queryWrapper = new QueryWrapper<>();
       queryWrapper.isNotNull("order_id");
       Page<Indent> page = new Page<>(param.getPageNum(), param.getPageSize());
       System.out.println(orderService.page(page,queryWrapper));
       return orderService.page(page,queryWrapper);
   }
   @PostMapping("/switch")
    public  Checkout  switchFinished(@RequestBody Indent order){
       Checkout checkout = new Checkout();
       Indent indent = orderMapper.selectById(order.getOrderId());
       boolean bool = indent.isFinished();
       //将bool值取反
       indent.setFinished(!bool);
       checkout.setCheckout(  orderService.saveOrUpdate(indent));
       return  checkout;
   }
}

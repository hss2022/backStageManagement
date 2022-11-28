package com.hss.service.impl;

import com.hss.entity.Indent;
import com.hss.mapper.OrderMapper;
import com.hss.service.IOrder1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hss
 * @since 2022-11-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Indent> implements IOrder1Service {

}

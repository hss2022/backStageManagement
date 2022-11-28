package com.hss.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hss.entity.Client;
import com.hss.mapper.ClientMapper;
import com.hss.service.IClientService;
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
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

}

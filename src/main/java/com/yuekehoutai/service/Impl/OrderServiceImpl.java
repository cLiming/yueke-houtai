package com.yuekehoutai.service.Impl;

import com.yuekehoutai.domain.Order;
import com.yuekehoutai.mapper.OrderMapper;
import com.yuekehoutai.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}

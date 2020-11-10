package com.yuekehoutai.service.Impl;

import com.yuekehoutai.domain.Food;
import com.yuekehoutai.mapper.FoodMapper;
import com.yuekehoutai.service.FoodService;
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
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {

}

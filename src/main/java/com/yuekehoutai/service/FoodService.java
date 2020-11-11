package com.yuekehoutai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.Food;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuekehoutai.domain.param.FoodListParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface FoodService extends IService<Food> {
    Page<Food> foodList(FoodListParam foodListParam);
}

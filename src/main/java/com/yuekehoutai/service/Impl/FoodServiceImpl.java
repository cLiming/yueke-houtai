package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.Food;
import com.yuekehoutai.domain.param.FoodListParam;
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

    @Override
    public Page<Food> foodList(FoodListParam param) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        Page<Food> foodPage = new Page<Food>(param.getPageIndex(),param.getPageNum());
        if(!param.getName().isEmpty()){
            wrapper.like("name",param.getName());
        }
        if(param.getCId()>0){
            wrapper.eq("c_id",param.getCId());
        }
        if(param.getPriceType()==0){
            wrapper.orderByDesc("price");
        }else if (param.getPriceType()==1){
            wrapper.orderByAsc("price");
        }
        if(param.getSaleType()==0){
            wrapper.orderByAsc("sales");
        }else if (param.getSaleType()==1){
            wrapper.orderByDesc("sales");
        }
        return this.page(foodPage,wrapper);
    }
}

package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Food;
import com.yuekehoutai.domain.param.FoodListParam;
import com.yuekehoutai.service.FoodService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/food")
public class FoodController {
    @Resource
    private FoodService foodService;


    //根据条件分页查询美食列表(城市id,营地ID,名称,)
    @RequestMapping("selectAll")
    public JsonResult selectAll(@Valid FoodListParam foodListParam){
        return new JsonResult(200,"success",null,foodService.foodList(foodListParam));
    }

    @RequestMapping("insert")
    public JsonResult foodInsert(Food food){

        return  new JsonResult(200,"success",null,null);
    }

}


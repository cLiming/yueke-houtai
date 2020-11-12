package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.Food;
import com.yuekehoutai.domain.param.FoodInsertParam;
import com.yuekehoutai.domain.param.FoodListParam;
import com.yuekehoutai.service.FoodService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public JsonResult foodInsert(@Valid FoodInsertParam param) throws Exception {
        for (int i = 0;i< param.getFiles().length;i++){
            if(param.getFiles()[i]==null){
                return new JsonResult(500,"图片不能为空",null,null);
            }
            if (!(param.getFiles()[i].getOriginalFilename().endsWith(".jpg")||param.getFiles()[i].getOriginalFilename().endsWith(".png"))) {
                return new JsonResult(500,"图片格式错误,只能上传jpg或者png格式图片",null,null);
            }
        }
        Food food = new Food();
        BeanUtils.copyProperties(param,food);
        boolean tag = foodService.addFood(param.getFiles(),food);
        if (tag) {
            return new JsonResult(200, "success", null, null);
        }else {
            return new JsonResult(500, "服务器错误", null, null);
        }
    }

}


package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Food;
import com.yuekehoutai.domain.param.FoodInsertParam;
import com.yuekehoutai.domain.param.FoodListParam;
import com.yuekehoutai.domain.param.FoodUpdateParam;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.service.FoodService;
import com.yuekehoutai.util.JsonResult;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.ValidationException;

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


    //根据条件分页查询美食列表(城市id,营地ID,名称)
    @GetMapping("selectAll")
    public JsonResult selectAll(@Valid FoodListParam foodListParam){
        return new JsonResult(200,"success",null,foodService.foodList(foodListParam));
    }
    //新增美食
    @PostMapping("insert")
    public JsonResult foodInsert(@Valid FoodInsertParam param) throws Exception {
        for (int i = 0;i< param.getFiles().length;i++){
            if (!(param.getFiles()[i].getOriginalFilename().endsWith(".jpg")||param.getFiles()[i].getOriginalFilename().endsWith(".png"))) {
                throw new ProjectException(1001, "只能上传jpg或png格式的图片");
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
    //更新美食信息
    @PutMapping("update")
    public JsonResult foodUpdate(@Valid FoodUpdateParam param)throws Exception{
        if(param.getFiles()!=null){
            for (int i = 0;i< param.getFiles().length;i++){
                if (!(param.getFiles()[i].getOriginalFilename().endsWith(".jpg")||param.getFiles()[i].getOriginalFilename().endsWith(".png"))) {
                    throw new ProjectException(1001, "只能上传jpg或png格式的图片");
                }
            }
        }
        Food food = new Food();
        BeanUtils.copyProperties(param,food);
        boolean tag = foodService.updateFood(param.getFiles(),food);
        if (tag) {
            return new JsonResult(200, "success", null, null);
        }else {
            return new JsonResult(500, "服务器错误", null, null);
        }
    }

    //删除美食
    @DeleteMapping("delete")
    public JsonResult deleteById(Integer id)throws Exception{
        if(id==null){
            throw new ProjectException(1007,"参数错误");
        }
        if(foodService.removeById(id)){
            return new JsonResult(200,"success",null,null);
        }else {
            return new JsonResult(500,"删除失败,请联系平台管理员",null,null);
        }
    }

    //单点删除美食图片
    @DeleteMapping("deleteImage")
    public JsonResult deleteImage(Integer id,String image)throws Exception{
        if(id==null||image.isEmpty()){
            throw new ValidationException("参数错误");
        }
        if(foodService.removeImage(id, image)){
            return new JsonResult(200,"success",null,null);
        }else {
            throw new ProjectException(500,"删除失败");
        }
    }
    //通过ID删除所有活动图片
    @DeleteMapping("deleteImageAll")
    public JsonResult deleteImageAll(Integer id)throws Exception{
        if(id==null){
            throw new ValidationException("参数错误");
        }
        if(foodService.removeImages(id)){
            return new JsonResult(200,"success",null,null);
        }else {
            throw new ProjectException(500,"删除失败");
        }
    }
}


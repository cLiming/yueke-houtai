package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Food;
import com.yuekehoutai.domain.param.FoodInsertParam;
import com.yuekehoutai.domain.param.FoodListParam;
import com.yuekehoutai.domain.param.FoodUpdateParam;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.service.FoodService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "营地美食")
@RequestMapping("/food")
public class FoodController {
    @Resource
    private FoodService foodService;


    //根据条件分页查询美食列表(城市id,营地ID,名称)
    @ApiOperation("根据条件分页查询美食列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "营地名称",required=true),
            @ApiImplicitParam(name = "priceType",value = "价格顺序0升序1降序",required=true),
            @ApiImplicitParam(name = "saleType",value = "销售量顺序0升序1降序",required=true),
            @ApiImplicitParam(name = "cityId",value = "所选城市ID",required=true),
            @ApiImplicitParam(name = "pageIndex",value = "当前页码",required=true),
            @ApiImplicitParam(name = "pageNum",value = "每页查询数据量",required=true)
    })
    @GetMapping("selectAll")
    public JsonResult selectAll(@Valid FoodListParam foodListParam){
        return new JsonResult(200,"success",null,foodService.foodList(foodListParam));
    }
    //新增美食
    @ApiOperation("新增美食")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "营地名称",required=true),
            @ApiImplicitParam(name = "price",value = "美食价格",required=true),
            @ApiImplicitParam(name = "address",value = "商家地址",required=true),
            @ApiImplicitParam(name = "cityId",value = "所选城市ID",required=true),
            @ApiImplicitParam(name = "number",value = "剩余数量",required=true),
            @ApiImplicitParam(name = "description",value = "描述",required=true),
            @ApiImplicitParam(name = "files",value = "图片上传",required=true)
    })
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
    @ApiOperation("更新美食信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "营地名称",required=true),
            @ApiImplicitParam(name = "id",value = "美食ID",required=true),
            @ApiImplicitParam(name = "price",value = "美食价格",required=true),
            @ApiImplicitParam(name = "address",value = "商家地址",required=true),
            @ApiImplicitParam(name = "number",value = "剩余数量",required=true),
            @ApiImplicitParam(name = "description",value = "描述",required=true),
            @ApiImplicitParam(name = "files",value = "图片上传",required=true)
    })
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
    @ApiOperation("删除美食")
    @ApiImplicitParam(name = "id",value = "美食id")
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
    @ApiOperation("单点删除美食图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "美食id"),
            @ApiImplicitParam(name = "image",value = "美食图片")
    })
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
    @ApiOperation("通过ID删除所有活动图片")
    @ApiImplicitParam(name = "id",value = "美食id")
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


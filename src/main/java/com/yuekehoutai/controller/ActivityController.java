package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.param.ActListParam;
import com.yuekehoutai.domain.param.ActivityParam;
import com.yuekehoutai.domain.param.ActivityUpdateParam;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.service.ActivityService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@RestController
@Api(tags = "活动接口")
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private ActivityService actService;


    //条件查询所有活动
    @ApiOperation("查询活动（可以通过条件）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cId",value = "营地id"),
            @ApiImplicitParam(name = "name",value = "活动名称"),
            @ApiImplicitParam(name = "cityId",value = "所处城市",required = true),
            @ApiImplicitParam(name = "priceType",value = "价格升降类型0升序 1降序",required = true),
            @ApiImplicitParam(name = "type",value = "活动类型",required = true),
            @ApiImplicitParam(name = "pageIndex",value = "当前页码",required = true),
            @ApiImplicitParam(name = "pageNum",value = "每页数据条数",required = true),
    })
    @GetMapping("selectAll")
    public JsonResult selectAll(@Valid ActListParam actListParam) throws Exception {
        return new JsonResult(200,"success",null,actService.actList(actListParam));
    }

    //新增活动(包括上传图片)
    @ApiOperation("新增活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "活动名称",required = true),
            @ApiImplicitParam(name = "number",value = "剩余票数",required = true),
            @ApiImplicitParam(name = "price",value = "价格",required = true),
            @ApiImplicitParam(name = "description",value = "活动描述",required = true),
            @ApiImplicitParam(name = "cId",value = "所属营地ID",required = true),
            @ApiImplicitParam(name = "actTypeId",value = "活动类型ID",required = true),
            @ApiImplicitParam(name = "cityId",value = "所属城市ID",required = true),
            @ApiImplicitParam(name = "file",value = "活动图片",required = true),
    })
    @PostMapping("insert")
    public JsonResult actInsert(@Valid ActivityParam param) throws Exception {
        if (param.getFiles()==null){
            throw new ProjectException(1000, "图片不能为空");
        }
        for (int i = 0;i< param.getFiles().length;i++){
            if (!(param.getFiles()[i].getOriginalFilename().endsWith(".jpg")||param.getFiles()[i].getOriginalFilename().endsWith(".png"))) {
                throw new ProjectException(1001, "图片格式错误,只能上传jpg或者png格式图片");
            }
        }
        Activity activity = new Activity();
        BeanUtils.copyProperties(param,activity);
        boolean tag = actService.addAct(param.getFiles(),activity);
        if (tag) {
            return new JsonResult(200, "success", null, null);
        }else {
            throw new ProjectException(1005, "上传失败");
        }
    }

    //修改活动
    @ApiOperation("修改活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",required = true),
            @ApiImplicitParam(name = "name",value = "活动名称",required = true),
            @ApiImplicitParam(name = "number",value = "剩余票数",required = true),
            @ApiImplicitParam(name = "price",value = "价格",required = true),
            @ApiImplicitParam(name = "description",value = "活动描述",required = true),
            @ApiImplicitParam(name = "actTypeId",value = "活动类型ID",required = true),
            @ApiImplicitParam(name = "file",value = "活动图片"),
    })
    @PutMapping("update")
    public JsonResult actUpdate(@Valid ActivityUpdateParam param)throws Exception{
        Activity activity = new Activity();
        BeanUtils.copyProperties(param,activity);
        if(param.getFiles()!=null){
            for (int i = 0;i< param.getFiles().length;i++){
                if (!(param.getFiles()[i].getOriginalFilename().endsWith(".jpg")||param.getFiles()[i].getOriginalFilename().endsWith(".png"))) {
                    throw new ProjectException(1001, "图片格式错误,只能上传jpg或者png格式图片");
                }
            }
        }
        boolean tag = actService.updateAct(param.getFiles(),activity);
        if (tag) {
            return new JsonResult(200, "success", null, null);
        }else {
            throw new ProjectException(1005, "更新活动失败");
        }
    }

    //单选删除活动图片
    @ApiOperation("单选删除活动图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "活动id"),
            @ApiImplicitParam(name = "image",value = "活动图片")
    })
    @DeleteMapping("deleteImage")
    public JsonResult deleteImage(Integer id,String image)throws Exception{
        if(id==null||image.isEmpty()){
            throw new ValidationException("参数错误");
        }
        if(actService.removeImage(id, image)){
            return new JsonResult(200,"success",null,null);
        }else {
            throw new ProjectException(500,"删除失败");
        }
    }

    //通过ID删除所有活动图片
    @ApiOperation("通过ID删除所有活动图片")
    @ApiImplicitParam(name = "id",value = "活动id")
    @DeleteMapping("deleteImageAll")
    public JsonResult deleteImageAll(Integer id)throws Exception{
        if(id==null){
            throw new ValidationException("参数错误");
        }
        if(actService.removeImages(id)){
            return new JsonResult(200,"success",null,null);
        }else {
            throw new ProjectException(500,"删除失败");
        }
    }


    //通过id删除活动
    @ApiOperation("通过id删除活动")
    @ApiImplicitParam(name = "id",value = "活动id")
    @DeleteMapping("delete")
    public JsonResult actDelete(Integer id)throws Exception{
        if(id==null){
            throw new ProjectException(1007,"参数错误");
        }
        if(actService.actRemoveById(id)){
            return new JsonResult(200,"success",null,null);
        }else {
            return new JsonResult(500,"删除失败",null,null);
        }
    }


}


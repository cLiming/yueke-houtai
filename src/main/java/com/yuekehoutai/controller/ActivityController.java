package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.param.ActListParam;
import com.yuekehoutai.domain.param.ActivityParam;
import com.yuekehoutai.domain.param.ActivityUpdateParam;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.service.ActivityService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private ActivityService actService;


    //条件查询所有活动
    @RequestMapping("selectAll")
    public JsonResult selectAll(ActListParam actListParam) throws Exception {
        return new JsonResult(200,"success",null,actService.actList(actListParam));
    }

    //新增活动(包括上传图片)
    @RequestMapping("insert")
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
    @RequestMapping("update")
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
    @RequestMapping("deleteImage")
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
    @RequestMapping("deleteImageAll")
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
    @RequestMapping("delete")
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


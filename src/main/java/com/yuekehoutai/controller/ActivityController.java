package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.param.ActInsertParam;
import com.yuekehoutai.domain.param.ActListParam;
import com.yuekehoutai.service.ActivityService;
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
    public JsonResult actInsert(MultipartFile[] files, @Valid ActInsertParam param) throws Exception {
        if(files==null){
            return new JsonResult(500,"请上传营地图片",null,null);
        }
        for (int i = 0;i< files.length;i++){
            if(files[i]==null){
                return new JsonResult(500,"图片不能为空",null,null);
            }
            if (!(files[i].getOriginalFilename().endsWith(".jpg")||files[i].getOriginalFilename().endsWith(".png"))) {
                return new JsonResult(500,"图片格式错误,只能上传jpg或者png格式图片",null,null);
            }
        }
        Activity activity = new Activity();
        BeanUtils.copyProperties(param,activity);
        boolean tag = actService.addAct(files,activity);
        if (tag) {
            return new JsonResult(200, "success", null, null);
        }else {
            return new JsonResult(500, "服务器错误", null, null);
        }
    }



}


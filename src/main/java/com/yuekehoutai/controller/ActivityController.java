package com.yuekehoutai.controller;


import com.yuekehoutai.domain.param.ActListParam;
import com.yuekehoutai.service.ActivityService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    //新增活动(包括上传图片)，在写完前台网页，测试完营地加盟接口后再写
    @RequestMapping("insert")
    public JsonResult actInsert(){
        return null;
    }



}


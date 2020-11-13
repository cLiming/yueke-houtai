package com.yuekehoutai.controller;


import com.yuekehoutai.domain.param.CampListParam;
import com.yuekehoutai.service.CampService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/camp")
public class CampController {
    @Resource
    private CampService campService;



    @GetMapping("selectAllCheck")
    public JsonResult selectAllCheck(){
        return new JsonResult(200,"success",campService.selectAllCheck(),null);
    }

    @PostMapping("check")
    public JsonResult check(Integer id){
        boolean tag = campService.campstatusUpdate(id,1);
        if (tag) {
            return new JsonResult(200,"success",null,null);
        }else {
            return new JsonResult(500,"服务器错误",null,null);
        }
    }

    //通过城市ID,营地名称,营地状态查询符合条件的所有营地
    @GetMapping("select")
    public JsonResult campList(@Valid CampListParam campListParam) throws Exception {
        return new JsonResult(200, "success", null, campService.campListLimit(campListParam));
    }

    //审核营地
    @PostMapping("update")
    public JsonResult campUpdate(Integer id){
        boolean tag = campService.campstatusUpdate(id,0);
        if (tag) {
            return new JsonResult(200,"success",null,null);
        }else {
            return new JsonResult(500,"服务器错误",null,null);
        }
    }




}


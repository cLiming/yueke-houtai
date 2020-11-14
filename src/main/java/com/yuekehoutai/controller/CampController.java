package com.yuekehoutai.controller;


import com.yuekehoutai.domain.param.CampListParam;
import com.yuekehoutai.service.CampService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "营地")
@RequestMapping("/camp")
public class CampController {
    @Resource
    private CampService campService;


    @ApiOperation("查询所有加盟的营地申请")
    @GetMapping("selectAllCheck")
    public JsonResult selectAllCheck(){
        return new JsonResult(200,"success",campService.selectAllCheck(),null);
    }

    @ApiOperation("设置热门营地")
    @ApiImplicitParam(name = "id",value = "审核营地id")
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
    @ApiOperation("条件查询营地")
    @ApiImplicitParam(name = "campListParam",value = "营地条件param对象")
    @GetMapping("select")
    public JsonResult campList(@Valid CampListParam campListParam) throws Exception {
        return new JsonResult(200, "success", null, campService.campListLimit(campListParam));
    }

    //审核营地
    @ApiOperation("审核营地")
    @ApiImplicitParam(name = "id",value = "审核营地id")
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


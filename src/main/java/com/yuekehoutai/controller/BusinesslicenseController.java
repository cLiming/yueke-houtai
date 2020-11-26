package com.yuekehoutai.controller;


import com.yuekehoutai.service.BusinesslicenseService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author corazon
 * @since 2020-11-26
 */
@Api(tags = "上架合同接口")
@RestController
@RequestMapping("/businesslicense")
public class BusinesslicenseController {
    @Resource
    private BusinesslicenseService businesslicenseService;
    @ApiOperation("修改合同状态为1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "商家id"),
    })
    @PostMapping("/updateStatus")
    public JsonResult updateStatus(Integer id){
        businesslicenseService.updateStatus(id);
        return  new JsonResult(200,"success",null,null);
    }
}


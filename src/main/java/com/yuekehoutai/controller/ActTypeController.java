package com.yuekehoutai.controller;


import com.yuekehoutai.service.ActTypeService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "活动类型")
@RequestMapping("/actType")
public class ActTypeController {
    @Resource
    private ActTypeService actTypeService;
    @ApiOperation("查询所有活动类型")
    @GetMapping("selectAll")
    public JsonResult selectAll(){
        return new JsonResult(200,"success",actTypeService.list(),null);
    }
}


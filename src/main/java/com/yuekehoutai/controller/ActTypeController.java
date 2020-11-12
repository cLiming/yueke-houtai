package com.yuekehoutai.controller;


import com.yuekehoutai.service.ActTypeService;
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
@RequestMapping("/actType")
public class ActTypeController {
    @Resource
    private ActTypeService actTypeService;

    @RequestMapping("selectAll")
    public JsonResult selectAll(){
        return new JsonResult(200,"success",actTypeService.list(),null);
    }
}


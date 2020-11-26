package com.yuekehoutai.controller;


import com.yuekehoutai.service.RoleService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;
    @ApiOperation("查询所有角色")
    @GetMapping
    public JsonResult selectRole(){
        return  new JsonResult(200,"seccess",roleService.selectRole(),null);
    }
}


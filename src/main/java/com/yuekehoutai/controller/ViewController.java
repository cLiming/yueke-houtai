package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.View;
import com.yuekehoutai.service.ViewService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Controller
@RequestMapping("/view")
public class ViewController {
    @Resource
    private ViewService viewService;
    //查询所有景点
    @GetMapping("selectAll")
    public JsonResult selectAll(Integer page)throws Exception{
        Page<View> viewPage = viewService.selectAll(page);
        return new JsonResult(200,"success",null,viewPage);
    }
}


package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.View;
import com.yuekehoutai.domain.param.ViewParam;
import com.yuekehoutai.service.ViewService;
import com.yuekehoutai.util.JsonResult;
import com.yuekehoutai.util.OosManagerUtil;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/view")
public class ViewController {
    @Resource
    private ViewService viewService;
    //查询所有景点
    @GetMapping("selectAll")
    public JsonResult selectAll(@Valid ViewParam viewParam)throws Exception{
        Page<View> viewPage = viewService.selectAll(viewParam);
        return new JsonResult(200,"success",null,viewPage);
    }

    //新增景点
    @PostMapping("insertView")
    public JsonResult insertView(ViewParam viewParam)throws Exception{
        viewService.insrtView(viewParam);
        return new JsonResult(200,"success",null,null);
    }

    //删除景点
    @DeleteMapping("deleteView")
    public JsonResult deleteView(Integer id)throws Exception{
        viewService.deleteView(id);
        return new JsonResult(200,"success",null,null);
    }
    //修改景点
    @PutMapping("updateView")
    public JsonResult updateView( ViewParam viewParam)throws Exception{
        viewService.updateView(viewParam);
        return new JsonResult(200,"success",null,null);
    }
}


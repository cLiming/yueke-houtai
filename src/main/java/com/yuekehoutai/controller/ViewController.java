package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.View;
import com.yuekehoutai.domain.param.ViewParam;
import com.yuekehoutai.service.ViewService;
import com.yuekehoutai.util.JsonResult;
import com.yuekehoutai.util.OosManagerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "景点接口")
@RequestMapping("/view")
public class ViewController {
    @Resource
    private ViewService viewService;
    //查询所有景点
    @ApiOperation("查询景点（可以通过条件）")
    @GetMapping("selectAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "景点名（模糊查询）"),
            @ApiImplicitParam(name = "cId",value = "城市id"),
            @ApiImplicitParam(name = "page",value = "页数"),
    })
    public JsonResult selectAll(@Valid ViewParam viewParam)throws Exception{
        System.out.println(viewParam.toString());
        Page<View> viewPage = viewService.selectAll(viewParam);
        return new JsonResult(200,"success",null,viewPage);
    }
    //根据id查景点
    @ApiOperation("查询景点（根据id）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "景点id")
    })
    @GetMapping("/selectViewById")
    public JsonResult selectViewById(Integer id){
        View view = viewService.getOne(new QueryWrapper<View>().eq("id", id));
        return  new JsonResult(200,"success",null,view);
    }
    //新增景点
    @ApiOperation("新增景点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "景点名"),
            @ApiImplicitParam(name = "description",value = "景点描述"),
            @ApiImplicitParam(name = "address",value = "景点地址"),
            @ApiImplicitParam(name = "price",value = "门票价格"),
            @ApiImplicitParam(name = "time",value = "营业时间"),
            @ApiImplicitParam(name = "cId",value = "城市id"),
            @ApiImplicitParam(name = "location",value = "经纬度"),
            @ApiImplicitParam(name = "file",value = "图片"),

    })
    @PostMapping("insertView")
    public JsonResult insertView(ViewParam viewParam)throws Exception{
        System.out.println(viewParam.toString());
        viewService.insrtView(viewParam);
        return new JsonResult(200,"success",null,null);
    }

    //删除景点
    @ApiOperation("删除景点")
    @ApiImplicitParam(name = "id",value = "景点id")
    @DeleteMapping("deleteView")
    public JsonResult deleteView(Integer id)throws Exception{
        viewService.deleteView(id);
        return new JsonResult(200,"success",null,null);
    }
    //修改景点
    @ApiOperation("修改景点")
     @ApiImplicitParams({
             @ApiImplicitParam(name = "id",value = "景点id"),
            @ApiImplicitParam(name = "name",value = "景点名"),
            @ApiImplicitParam(name = "description",value = "景点描述"),
            @ApiImplicitParam(name = "address",value = "景点地址"),
            @ApiImplicitParam(name = "price",value = "门票价格"),
            @ApiImplicitParam(name = "time",value = "营业时间"),
            @ApiImplicitParam(name = "cId",value = "城市id"),
            @ApiImplicitParam(name = "location",value = "经纬度"),
            @ApiImplicitParam(name = "file",value = "图片"),

    })
    @PutMapping("updateView")
    public JsonResult updateView( ViewParam viewParam)throws Exception{
        viewService.updateView(viewParam);
        return new JsonResult(200,"success",null,null);
    }
}


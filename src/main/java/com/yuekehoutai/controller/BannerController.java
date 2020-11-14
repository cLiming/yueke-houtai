package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.BannerDto;
import com.yuekehoutai.domain.Banner;
import com.yuekehoutai.param.BannerParam;
import com.yuekehoutai.service.BannerService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
@RestController
@Api(tags = "景区banner设置")
@RequestMapping("/banner")
public class BannerController {
    @Resource
    private BannerService bannerService;

    @ApiOperation("查询banner")
    @ApiImplicitParam(name = "BannerParam",value = "bannerparam对象")
    @GetMapping("/selectBanner")
    public JsonResult selectBanner(BannerParam bannerParam) throws Exception{
        Page<Banner> page = new Page<>(bannerParam.getPageNow(),bannerParam.getPageSize());
        return new JsonResult(200, "success", null, bannerService.selectBanner(page));
    }
    @ApiOperation("新增banner")
    @ApiImplicitParam(name = "BannerParam",value = "bannerparam对象")
    @PostMapping("/insertBanner")
    public JsonResult insertBanner(BannerParam bannerParam) throws Exception{
        BannerDto bannerDto = new BannerDto();
        BeanUtils.copyProperties(bannerParam, bannerDto);
        bannerService.insertBanner(bannerDto);
        return new JsonResult(200, "success", null,null);
    }
    @ApiOperation("修改banner")
    @ApiImplicitParam(name = "BannerParam",value = "bannerparam对象")
    @PutMapping("/updateBanner")
    public JsonResult updateBanner(BannerParam bannerParam) throws Exception{
        BannerDto bannerDto = new BannerDto();
        BeanUtils.copyProperties(bannerParam, bannerDto);
        bannerService.updateBanner(bannerDto);
        return new JsonResult(200, "success", null,null);
    }
    @ApiOperation("删除banner")
    @ApiImplicitParam(name = "id",value = "营地id")
    @PutMapping("/deleteBanner")
    public JsonResult deleteBanner(Integer id) throws Exception{
        bannerService.deleteBanner(id);
        return new JsonResult(200, "success", null, null);
    }

}


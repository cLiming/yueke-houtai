package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.InformationDto;
import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.Information;

import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.param.InformationParam;
import com.yuekehoutai.service.InformationService;

import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;


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
@Api(tags = "旅游资讯")
@RequestMapping("/information")
public class InformationController {
    @Resource
    private InformationService informationService;
    //查询旅游资讯 有条件则根据条件查询 没有条件查询全部
    @ApiOperation("查询旅游资讯（必须分页）")
    @ApiImplicitParam(name = "InformationParam",value = "旅游资讯param对象")
    @GetMapping("selectInformation")
    public JsonResult selectInformation(InformationParam informationParam) throws Exception{
        InformationDto informationDto = new InformationDto();
        Page<Information> page = new Page<>(informationParam.getPageNow(),informationParam.getPageSize());
        BeanUtils.copyProperties(informationParam, informationDto);

        return new JsonResult(200, "success", null, informationService.selectInformation(informationDto,page));
    }
    //新增景区资讯(包括上传图片)
    @ApiOperation("新增景区资讯")
    @ApiImplicitParam(name = "InformationParam",value = "旅游资讯param对象")
    @PostMapping("insertInformation")
    public JsonResult insertInformation(InformationParam informationParam) throws Exception {
        for (int i = 0;i< informationParam.getImages().length;i++){
            if(informationParam.getImages()[i]==null){
                throw  new ProjectException(500,"图片不能为空");
            }
            if (!(informationParam.getImages()[i].getOriginalFilename().endsWith(".jpg")||informationParam.getImages()[i].getOriginalFilename().endsWith(".png"))) {
                throw  new ProjectException(500,"图片格式错误,只能上传jpg或者png格式图片");
            }
        }
        InformationDto informationDto = new InformationDto();
        BeanUtils.copyProperties(informationParam, informationDto);
        informationService.insertInformation(informationDto);
        return new JsonResult(200, "success", null, null);
    }
    //删除旅游资讯
    @ApiOperation("删除旅游资讯")
    @ApiImplicitParam(name = "id",value = "旅游资讯id")
    @DeleteMapping("/deleteInformation")
    public JsonResult deleteInformation(Integer id) throws Exception{
        System.err.println("controller"+id);
        informationService.deleteInformation(id);
        return new JsonResult(200, "success", null, null);
    }
    //修改旅游资讯
    @ApiOperation("修改旅游资讯")
    @ApiImplicitParam(name = "InformationParam",value = "旅游资讯param对象")
    @PutMapping("/updateInformation")
    public JsonResult updateInformation(InformationParam informationParam) throws Exception {
        InformationDto informationDto = new InformationDto();
        BeanUtils.copyProperties(informationParam, informationDto);
        informationService.updateInformation(informationDto);
        return new JsonResult(200, "success", null, null);
    }
}


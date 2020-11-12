package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.InformationDto;
import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.Information;
import com.yuekehoutai.domain.param.ActInsertParam;
import com.yuekehoutai.param.InformationParam;
import com.yuekehoutai.service.InformationService;

import com.yuekehoutai.util.JsonResult;
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
@RequestMapping("/information")
public class InformationController {
    @Resource
    private InformationService informationService;
    //查询旅游资讯 有条件则根据条件查询 没有条件查询全部
    @GetMapping("selectInformation")
    public JsonResult selectInformation(InformationParam informationParam) throws Exception{
        InformationDto informationDto = new InformationDto();
        Page<Information> page = new Page<>(informationParam.getPageNow(),informationParam.getPageSize());
        BeanUtils.copyProperties(informationParam, informationDto);

        return new JsonResult(200, "success", null, informationService.selectInformation(informationDto,page));
    }
    //新增景区资讯(包括上传图片)
    @RequestMapping("insert")
    public JsonResult actInsert(MultipartFile[] files, @Valid ActInsertParam param) throws Exception {

        return new JsonResult(200, "success", null, null);
    }
    //删除旅游资讯
    @DeleteMapping("/deleteInformation")
    public JsonResult deleteInformation(Integer id) throws Exception{
        System.err.println("controller"+id);
        informationService.deleteInformation(id);
        return new JsonResult(200, "success", null, null);
    }

}


package com.yuekehoutai.controller;


import com.yuekehoutai.Dto.CampContractDto;
import com.yuekehoutai.param.CampContractParam;
import com.yuekehoutai.service.CampContractService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/campContract")
public class CampContractController {

    @Resource
    private CampContractService campContractService;
    @ApiOperation("新增营地加盟的合同")
    @PostMapping("checkCamp")
    @ApiImplicitParam(name = "campContractParam",value = "营地加盟合同",required = true)
    public JsonResult checkCamp(CampContractParam campContractParam)throws Exception{
        CampContractDto contractDto = CampContractDto.builder().build();
        BeanUtils.copyProperties(campContractParam,contractDto);
        campContractService.insertCampContract(contractDto);
        return new JsonResult(200,"success",null,null);
    }


}


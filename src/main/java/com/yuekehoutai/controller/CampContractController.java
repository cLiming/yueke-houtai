package com.yuekehoutai.controller;


import com.yuekehoutai.Dto.CampContractDto;
import com.yuekehoutai.domain.CampContract;
import com.yuekehoutai.param.CampContractParam;
import com.yuekehoutai.service.CampContractService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

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
    @PostMapping("insertCampHeTong")
    @ApiImplicitParam(name = "campContractParam",value = "营地加盟合同对象",required = true)
    public JsonResult insertCampHeTong(CampContractParam campContractParam)throws Exception{
        CampContractDto contractDto = CampContractDto.builder().build();
        BeanUtils.copyProperties(campContractParam,contractDto);
        campContractService.insertCampContract(contractDto);
        return new JsonResult(200,"success",null,null);
    }

    @ApiOperation("审核营地加盟的合同")
    @PutMapping("checkCamp")
    @ApiImplicitParam(name = "campContractParam",value = "营地加盟合同对象",required = true)
    public JsonResult checkCampHeTong(CampContractParam campContractParam)throws Exception{
        CampContractDto contractDto = CampContractDto.builder().build();
        BeanUtils.copyProperties(campContractParam,contractDto);
        campContractService.setCampContract(contractDto);
        return new JsonResult(200,"success",null,null);
    }


    @ApiOperation("条件查询营地加盟的合同")
    @GetMapping("selectCampContract")
    @ApiImplicitParam(name = "campContractParam",value = "营地加盟合同对象",required = true)
    public JsonResult selectCampContract(CampContractParam campContractParam)throws Exception{
        List<CampContract> campContracts=campContractService.getCampContract(campContractParam);
        return new JsonResult(200,"success",campContracts,null);
    }

}


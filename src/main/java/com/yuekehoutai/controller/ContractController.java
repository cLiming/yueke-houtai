package com.yuekehoutai.controller;


import com.alibaba.fastjson.JSON;
import com.yuekehoutai.domain.Contract;
import com.yuekehoutai.service.ContractService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
 * @since 2020-11-21
 */
@RestController
@RequestMapping("/contract")
public class ContractController {
    @Resource
    private ContractService contractService;
    @PostMapping("insertContract")
    public JsonResult insertContract(Contract contract){
        contractService.insertContract(contract);
        return new JsonResult(200, "success", null, null);
    }
    @DeleteMapping("deleteContract")
    public JsonResult deleteContract(Integer id){
        contractService.deleteContract(id);
        return new JsonResult(200, "success", null, null);
    }
}


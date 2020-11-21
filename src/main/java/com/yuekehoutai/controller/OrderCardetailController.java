package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.yuekehoutai.Dto.CarDto;
import com.yuekehoutai.Dto.OrderRentDetailDto;
import com.yuekehoutai.domain.Car;
import com.yuekehoutai.domain.Worker;
import com.yuekehoutai.param.CarParam;
import com.yuekehoutai.param.OrderRentDetailParam;
import com.yuekehoutai.service.OrderCardetailService;
import com.yuekehoutai.service.OrderService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@Api(tags = "订单")
@RequestMapping("/orderCardetail")
public class OrderCardetailController {
    @Resource
    private OrderCardetailService orderCardetailService;
    //查询营地的所有
    @ApiOperation("管理员所在的营地的房车（分页）这个对象是存在subject中 必须登录才能实现")
    @ApiImplicitParam(name = "carParam",value = "管理员所在的营地的房车")
    @GetMapping("/selectCampCar")
    public JsonResult selectCampCar(CarParam carParam) throws Exception{
        CarDto carDto = new CarDto();
        Page<Car> page = new Page<>(carParam.getPageNow(),carParam.getPageSize());
        BeanUtils.copyProperties(carParam, carDto);
        return new JsonResult(200,"success", null, orderCardetailService.selectCampCar(carDto,page));
    }
    //查询营地房车的订单
    @ApiOperation("查询营地房车的订单（分页）这个对象是存在subject中 必须登录才能实现")
    @ApiImplicitParam(name = "orderRentDetailParam",value = "订单详情param对象")
    @GetMapping("/selectTimeCarOrder")
    public JsonResult selectTimeCarOrder(OrderRentDetailParam orderRentDetailParam) throws Exception{
        OrderRentDetailDto orderRentDetailDto = new OrderRentDetailDto();
        BeanUtils.copyProperties(orderRentDetailParam, orderRentDetailDto);
        return new JsonResult(200,"success", orderCardetailService.selectTimeCarOrder(orderRentDetailDto),null);
    }
    //延长订单时间
    @ApiOperation("延长订单时间")
    @ApiImplicitParam(name = "orderRentDetailParam",value = "订单详情param对象")
    @PutMapping("/addCarOrderTime")
    public JsonResult addCarOrderTime(OrderRentDetailParam orderRentDetailParam) throws Exception{
        OrderRentDetailDto orderRentDetailDto = new OrderRentDetailDto();
        System.err.println("param"+orderRentDetailParam.getEndTime());
        BeanUtils.copyProperties(orderRentDetailParam, orderRentDetailDto);
        System.err.println("dto"+orderRentDetailDto.getEndTime());
        orderCardetailService.addCarOrderTime(orderRentDetailDto);
        return new JsonResult(200, "success", null, null);
    }

}


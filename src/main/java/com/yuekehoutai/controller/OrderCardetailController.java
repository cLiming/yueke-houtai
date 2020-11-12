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
@RequestMapping("/orderCardetail")
public class OrderCardetailController {
    @Resource
    private OrderCardetailService orderCardetailService;
    //查询营地的所有
    @GetMapping("/selectCampCar")
    public JsonResult selectCampCar(CarParam carParam) throws Exception{
        CarDto carDto = new CarDto();
        Page<Car> page = new Page<>(carParam.getPageNow(),carParam.getPageSize());
        BeanUtils.copyProperties(carParam, carDto);
        return new JsonResult(200,"success", null, orderCardetailService.selectCampCar(carDto,page));
    }
    //查询营地房车的订单
    @GetMapping("/selectTimeCarOrder")
    public JsonResult selectTimeCarOrder(OrderRentDetailParam orderRentDetailParam) throws Exception{
        OrderRentDetailDto orderRentDetailDto = new OrderRentDetailDto();
        BeanUtils.copyProperties(orderRentDetailParam, orderRentDetailDto);
        return new JsonResult(200,"success", orderCardetailService.selectTimeCarOrder(orderRentDetailDto),null);
    }
    //延长订单时间
    @PutMapping("/addCarOrderTime")
    public JsonResult addCarOrderTime(OrderRentDetailParam orderRentDetailParam) throws Exception{
        OrderRentDetailDto orderRentDetailDto = new OrderRentDetailDto();
        BeanUtils.copyProperties(orderRentDetailParam, orderRentDetailDto);
        orderCardetailService.addCarOrderTime(orderRentDetailDto);
        return new JsonResult(200, "success", null, null);
    }

}


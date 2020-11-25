package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.CarDto;
import com.yuekehoutai.domain.Car;
import com.yuekehoutai.param.CarParam;
import com.yuekehoutai.service.CarService;
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
@Api(tags = "租聘房车")
@RequestMapping("/car")
public class CarController {
    @Resource
    private CarService carService;
    //平台 查询房车（可带条件）
    @ApiOperation("查询房车（可带条件）必须有分页")
    @ApiImplicitParam(name = "carParam",value = "房车param对象")
    @GetMapping("/selectShareCar")
    public JsonResult selectShareCar(CarParam carParam) throws Exception{
        System.err.println("controller");
        CarDto carDto = new CarDto();
        Page<Car> page = new Page<>(1,2);
        BeanUtils.copyProperties(carParam, carDto);

        IPage<Car> carIPage = carService.selectShareCar(carDto, page);
        return new JsonResult(200,"success",null,carIPage);
    }

    //在审核的同时(从审核状态3改为空闲状态0) 房车的定金和金额输入（不输入审核金额不能通过）
    @ApiOperation("审核房车，同时输入定金和金额 先判断有没有合同")
    @ApiImplicitParam(name = "carParam",value = "房车param对象")
    @PutMapping("/updateShareCarPrice")
    public JsonResult updateShareCarPrice(CarParam carParam) throws Exception {
        CarDto carDto = new CarDto();
        BeanUtils.copyProperties(carParam, carDto);
        carService.updateShareCarPrice(carDto);
        return new JsonResult(200, "success", null, null);
    }
    //删除房车信息
    @ApiOperation("删除房车")
    @ApiImplicitParam(name = "carParam",value = "房车param对象")
    @PutMapping("/deleteCarcarcar")
    public JsonResult deleteCar(CarParam carParam) throws Exception{
        CarDto carDto = new CarDto();
        BeanUtils.copyProperties(carParam, carDto);
        carService.deleteCar(carDto);
        return new JsonResult(200, "success", null, null);
    }
}


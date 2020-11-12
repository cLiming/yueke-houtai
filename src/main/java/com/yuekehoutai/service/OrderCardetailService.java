package com.yuekehoutai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.CarDto;
import com.yuekehoutai.Dto.OrderRentDetailDto;
import com.yuekehoutai.Dto.OrderRentDto;
import com.yuekehoutai.domain.Car;
import com.yuekehoutai.domain.OrderCardetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuekehoutai.domain.Worker;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface OrderCardetailService extends IService<OrderCardetail> {
    Page<Car> selectCampCar(CarDto carDto, Page<Car> page) throws Exception;
    List<OrderRentDetailDto> selectTimeCarOrder(OrderRentDetailDto orderRentDetailDto) throws Exception;
    void addCarOrderTime(OrderRentDetailDto orderRentDetailDto) throws Exception;
}

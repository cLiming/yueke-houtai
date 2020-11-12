package com.yuekehoutai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.CarDto;
import com.yuekehoutai.domain.Car;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface CarService extends IService<Car> {
    IPage<Car> selectShareCar(CarDto carDto, Page<Car> page );
    void updateShareCarPrice(CarDto carDto) throws Exception;
    void deleteCar(CarDto carDto) throws Exception;
}

package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.CarDto;
import com.yuekehoutai.domain.Car;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.mapper.CarMapper;
import com.yuekehoutai.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuekehoutai.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
    @Resource
    private CarMapper carMapper;
    @Override
    public IPage<Car>  selectShareCar(CarDto carDto, Page<Car> page) {
        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();

        if(carDto!=null){
            //条件查询 有就查询 没有就不查询
            carQueryWrapper.like(carDto.getName()!=null,"name",carDto.getName());
            carQueryWrapper.eq(carDto.getPrice()!=null,"price",carDto.getName());
            carQueryWrapper.like(carDto.getType()!=null,"type",carDto.getType());
            carQueryWrapper.eq(carDto.getStatus()!=null,"status",carDto.getStatus());
            //前端只是传一个标记过来 要查看今天将要过期的房车
            carQueryWrapper.ge(carDto.getEndTime()!=null,"end_time", DateUtil.getStartTimestamp());
            carQueryWrapper.le(carDto.getEndTime()!=null,"end_time", DateUtil.getEndTimestamp());
            IPage<Car> page1 = this.page(page, carQueryWrapper);
            return page1;
        }else {
            carQueryWrapper.eq("status", 3);
            IPage<Car> page1 = this.page(page, carQueryWrapper);
            return page1;
        }
    }


    @Override
    public void updateShareCarPrice(CarDto carDto) throws Exception {
        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
        if(carDto!=null&&carDto.getPrice()!=null&&carDto.getPreprice()!=null){
            carQueryWrapper.eq(carDto.getId()!=null,"id", carDto.getId());
            Car car = carMapper.selectOne(carQueryWrapper);
            if(car!=null){
                UpdateWrapper<Car> carUpdateWrapper = new UpdateWrapper<>();
                carUpdateWrapper.set("status", 0);
                carUpdateWrapper.eq("id", car.getId());
                carUpdateWrapper.set("price",carDto.getPrice());
                carUpdateWrapper.set("preprice",carDto.getPreprice());
                carMapper.update(null, carUpdateWrapper);
            }else{
                throw new ProjectException(9004, "信息不存在");
            }
        }else {
            throw new ProjectException(9003, "传递信息不正确或者房车必须有价格");
        }

    }

    @Override
    public void deleteCar(CarDto carDto) throws Exception {
        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
        if(carDto!=null&&carDto.getId()!=null){
            carQueryWrapper.eq("id",carDto.getId());
            Car car = carMapper.selectOne(carQueryWrapper);
            UpdateWrapper<Car> carUpdateWrapper = new UpdateWrapper<>();
            carUpdateWrapper.eq("id", carDto.getId());
            carUpdateWrapper.set("status", 4);
            carMapper.update(null,carUpdateWrapper);
        }else {
            throw new ProjectException(9001, "房车不存在");
        }
    }
}

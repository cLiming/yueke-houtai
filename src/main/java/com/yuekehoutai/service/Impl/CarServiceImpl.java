package com.yuekehoutai.service.Impl;

import com.yuekehoutai.domain.Car;
import com.yuekehoutai.mapper.CarMapper;
import com.yuekehoutai.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

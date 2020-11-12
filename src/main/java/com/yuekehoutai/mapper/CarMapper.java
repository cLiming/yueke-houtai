package com.yuekehoutai.mapper;

import com.yuekehoutai.domain.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface CarMapper extends BaseMapper<Car> {
    List<Car> selectByDate(@Param("endTime") String endTime);
}

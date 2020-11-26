package com.yuekehoutai.mapper;

import com.yuekehoutai.domain.Menu;
import com.yuekehoutai.domain.Worker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface WorkerMapper extends BaseMapper<Worker> {
    List<Menu> selectWokerPermissions(Integer id);
    List<Menu> selectButton(Integer id,Integer pId);
    List<Menu> selectMenu(Integer id);
}

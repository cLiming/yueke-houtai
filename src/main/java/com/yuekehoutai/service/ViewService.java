package com.yuekehoutai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.View;
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
public interface ViewService extends IService<View> {
     Page<View> selectAll(Integer page)throws Exception;
}

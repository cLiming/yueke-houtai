package com.yuekehoutai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuekehoutai.domain.param.ActListParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface ActivityService extends IService<Activity> {
    Page actList(ActListParam actListParam)throws Exception;
}

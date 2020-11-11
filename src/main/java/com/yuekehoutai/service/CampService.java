package com.yuekehoutai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.Camp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuekehoutai.domain.param.CampListParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface CampService extends IService<Camp> {

    List<Camp> selectAllCheck();

    @Transactional
    boolean campstatusUpdate(Integer id,Integer status);

    Page campListLimit(CampListParam param)throws Exception;

}

package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yuekehoutai.domain.Businesslicense;
import com.yuekehoutai.mapper.BusinesslicenseMapper;
import com.yuekehoutai.service.BusinesslicenseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-26
 */
@Service
public class BusinesslicenseServiceImpl extends ServiceImpl<BusinesslicenseMapper, Businesslicense> implements BusinesslicenseService {
    @Resource
    private BusinesslicenseMapper businesslicenseMapper;
    @Override
    public void updateStatus(Integer id) {
        businesslicenseMapper.update(new Businesslicense(),new UpdateWrapper<Businesslicense>().set("status",1).eq("b_id",id));
    }
}

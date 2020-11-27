package com.yuekehoutai.service;

import com.yuekehoutai.domain.Businesslicense;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-26
 */
public interface BusinesslicenseService extends IService<Businesslicense> {
    void updateStatus(Integer id);
    List<Businesslicense> selectAll();
    void deleteById(Integer id);
}

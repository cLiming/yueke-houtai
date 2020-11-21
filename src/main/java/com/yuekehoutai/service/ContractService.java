package com.yuekehoutai.service;

import com.yuekehoutai.domain.Contract;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-21
 */
public interface ContractService extends IService<Contract> {
    void insertContract(Contract contract);
    void deleteContract(Integer id);
}

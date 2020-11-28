package com.yuekehoutai.service;

import com.yuekehoutai.Dto.CampContractDto;
import com.yuekehoutai.domain.CampContract;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-28
 */
public interface CampContractService extends IService<CampContract> {

    void insertCampContract(CampContractDto contractDto);
}

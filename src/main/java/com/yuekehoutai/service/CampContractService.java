package com.yuekehoutai.service;

import com.yuekehoutai.Dto.CampContractDto;
import com.yuekehoutai.domain.CampContract;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuekehoutai.param.CampContractParam;

import java.util.List;

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

    void setCampContract(CampContractDto contractDto);

    List<CampContract> getCampContract(CampContractParam campContractParam);
}

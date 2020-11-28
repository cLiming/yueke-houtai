package com.yuekehoutai.service.Impl;

import com.yuekehoutai.Dto.CampContractDto;
import com.yuekehoutai.domain.CampContract;
import com.yuekehoutai.mapper.CampContractMapper;
import com.yuekehoutai.service.CampContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-28
 */
@Service
public class CampContractServiceImpl extends ServiceImpl<CampContractMapper, CampContract> implements CampContractService {

    @Resource
    private  CampContractMapper campContractMapper;
    @Override
    public void insertCampContract(CampContractDto contractDto) {
        CampContract campContract = new CampContract();
        if(campContract!=null){
            campContract.setCampId(contractDto.getCampId());
            campContract.setCost(contractDto.getCost());
            campContract.setImages(contractDto.getImages());
            campContract.setStatus(contractDto.getStatus());
            campContract.setWorkerId(contractDto.getWorkerId());
        }
        campContractMapper.insert(campContract);
    }
}

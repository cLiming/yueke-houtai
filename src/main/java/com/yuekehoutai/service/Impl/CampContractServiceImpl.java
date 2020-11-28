package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuekehoutai.Dto.CampContractDto;
import com.yuekehoutai.domain.Camp;
import com.yuekehoutai.domain.CampContract;
import com.yuekehoutai.domain.Contract;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.mapper.CampContractMapper;
import com.yuekehoutai.mapper.CampMapper;
import com.yuekehoutai.param.CampContractParam;
import com.yuekehoutai.service.CampContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuekehoutai.service.CampService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private CampMapper campMapper;
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

    @Override
    public void setCampContract(CampContractDto contractDto) {
        QueryWrapper<CampContract> wrapper = new QueryWrapper<>();
        CampContract campContract = new CampContract();
        BeanUtils.copyProperties(contractDto,campContract);
        if(contractDto!=null){
            campContractMapper.updateById(campContract);
            Camp camp = campMapper.selectById(campContract.getCampId());
            camp.setStatus(1);
            campMapper.updateById(camp);
        }else{
            throw new ProjectException(10030,"参数异常");
        }

    }

    @Override
    public List<CampContract> getCampContract(CampContractParam campContractParam) {
        if (campContractParam!=null){
            CampContract contract = new CampContract();
            BeanUtils.copyProperties(campContractParam,contract);
            QueryWrapper<CampContract> wrapper = new QueryWrapper<>();
            wrapper.eq(contract.getId()!=null,"id",contract.getId());
            wrapper.eq(contract.getCampId()!=null,"camp_id",contract.getCampId());
            wrapper.eq(contract.getImages()!=null,"images",contract.getImages());
            wrapper.eq(contract.getStatus()!=null,"status",contract.getStatus());
            wrapper.eq(contract.getWorkerId()!=null,"worker_id",contract.getWorkerId());
            wrapper.eq(contract.getCost()!=null,"cost",contract.getCost());

            return campContractMapper.selectList(wrapper);
        }
        return null;
    }


}

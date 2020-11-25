package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yuekehoutai.domain.Contract;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.mapper.ContractMapper;
import com.yuekehoutai.service.ContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-21
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {
    @Resource
    private ContractMapper contractMapper;
    @Override
    public void insertContract(Contract contract){
        contract.setStatus("0");
        QueryWrapper<Contract> contractQueryWrapper = new QueryWrapper<>();
        contractQueryWrapper.eq(contract.getCarId()!=null, "car_id", contract.getCarId());
        contractQueryWrapper.eq("status",0);
        Contract contract1 = contractMapper.selectOne(contractQueryWrapper);
        if(contract1==null){
            QueryWrapper<Contract> contractQueryWrapper1 = new QueryWrapper<>();
            contractQueryWrapper1.eq("status",1);
            Contract contract2 = contractMapper.selectOne(contractQueryWrapper1);
            if(contract2!=null){

            }else{
                contractMapper.insert(contract);
            }

        }else{
            throw new ProjectException(9003, "车合同已经存在");
        }
    }

    @Override
    public void deleteContract(Integer id) {
        QueryWrapper<Contract> contractQueryWrapper = new QueryWrapper<>();
        contractQueryWrapper.eq("id", id);
        Contract contract = contractMapper.selectOne(contractQueryWrapper);
        if(contract!=null){
            UpdateWrapper<Contract> contractUpdateWrapper = new UpdateWrapper<>();
            contractUpdateWrapper.eq("id",id);
            contractUpdateWrapper.set("status", 1);

            contractMapper.update(null, contractUpdateWrapper);
        }else {
            throw new ProjectException(9003, "合同不存在");
        }
    }
}

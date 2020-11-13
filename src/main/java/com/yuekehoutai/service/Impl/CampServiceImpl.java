package com.yuekehoutai.service.Impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.Camp;
import com.yuekehoutai.domain.param.CampListParam;
import com.yuekehoutai.mapper.CampMapper;
import com.yuekehoutai.service.CampService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Service
public class CampServiceImpl extends ServiceImpl<CampMapper, Camp> implements CampService {

    @Override
    public List<Camp> selectAllCheck() {
        QueryWrapper<Camp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",2);
        return this.list(queryWrapper);
    }


    @Override
    public boolean campstatusUpdate(Integer id,Integer status) {
        UpdateWrapper<Camp> wrapper = new UpdateWrapper<Camp>();
        wrapper.eq("id",id);
        wrapper.set("status",status);
        return this.update(wrapper);
    }

    @Override
    public Page campListLimit(CampListParam param) throws Exception {
        QueryWrapper<Camp> wrapper = new QueryWrapper<>();
        Page<Camp> page = new Page<Camp>(param.getPageIndex(),param.getPageNum());
        wrapper.eq("city_id",param.getCityId());
        if(!StringUtils.isEmpty(param.getName())){
            wrapper.like("name",param.getName());
        }
        if(param.getStatus()>=0&&param.getStatus()<=2){
            if(param.getStatus()==1){
                wrapper.lt("status",2);
            }else{
                wrapper.eq("status",param.getStatus());
            }
        }
        return this.page(page,wrapper);
    }

}

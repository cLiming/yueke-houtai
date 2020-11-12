package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.InformationDto;
import com.yuekehoutai.domain.Information;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.mapper.InformationMapper;
import com.yuekehoutai.service.InformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {
    @Resource
    private InformationMapper informationMapper;
    @Override
    public Page<InformationDto> selectInformation(InformationDto informationDto,Page<Information> page) throws Exception {
        QueryWrapper<Information> informationQueryWrapper = new QueryWrapper<>();
        if(informationDto!=null){
            informationQueryWrapper.like(informationDto.getTitle()!=null,"title", informationDto.getTitle());
            informationQueryWrapper.like(informationDto.getDescription()!=null, "description", informationDto.getDescription());
            Page<Information> page1 = this.page(page, informationQueryWrapper);
            Page<InformationDto> informationDtoPage = new Page<>();
            BeanUtils.copyProperties(page1, informationDtoPage);
            return informationDtoPage;
        }else {
            //没有条件查询全部旅游自诩
            Page<Information> page1 = this.page(page);
            Page<InformationDto> informationDtoPage = new Page<>();
            BeanUtils.copyProperties(page1, informationDtoPage);
            return informationDtoPage;
        }

    }

    @Override
    public void deleteInformation(Integer id) throws Exception {
        if(id!=null){
            System.err.println("service"+id);
            informationMapper.deleteById(id);
        }else {
            throw new ProjectException(9001, "删除旅游资讯不存在");
        }
    }
}

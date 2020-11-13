package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.InformationDto;
import com.yuekehoutai.domain.Information;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.mapper.InformationMapper;
import com.yuekehoutai.service.InformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuekehoutai.util.OosManagerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public void insertInformation(InformationDto informationDto) throws Exception {
        Information information = new Information();
        BeanUtils.copyProperties(informationDto, information);
        String Path = "information/img";
        StringBuffer images = new StringBuffer();
        MultipartFile[] files = informationDto.getFiles();
        for(int i=0;i<files.length;i++){
            String imagePath = OosManagerUtil.uploadFile(files[i], Path);
            if(i==files.length-1){
                images.append(imagePath);
            }else{
                images.append(imagePath+",");
            }
        }
        information.setImage(images.toString());
        this.save(information);
    }
    @Override
    public void updateInformation(InformationDto informationDto) throws Exception {
        Information information = new Information();
        UpdateWrapper<Information> informationUpdateWrapper = new UpdateWrapper<>();
        if(informationDto!=null){
            informationUpdateWrapper.eq("id", informationDto.getId());
            informationUpdateWrapper.set(informationDto.getTitle()!=null, "title", informationDto.getTitle());
            if(informationDto.getFiles()!=null){
                String Path = "information/img";
                StringBuffer images = new StringBuffer();
                MultipartFile[] files = informationDto.getFiles();
                if(files!=null&&files.length<=0){
                    for(int i=0;i<files.length;i++){
                        String imagePath = OosManagerUtil.uploadFile(files[i], Path);
                        if(i==files.length-1){
                            images.append(imagePath);
                        }else{
                            images.append(imagePath+",");
                        }
                    }
                    informationUpdateWrapper.set("image", images);
                }
            }
            informationUpdateWrapper.set(informationDto.getDescription()!=null, "description", informationDto.getDescription());
            informationMapper.update(null, informationUpdateWrapper);
        }

    }
}

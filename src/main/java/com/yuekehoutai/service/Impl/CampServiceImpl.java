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
import com.yuekehoutai.util.FilePath;
import com.yuekehoutai.util.OosManagerUtil;
import com.yuekehoutai.util.StringTool;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
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
        if(param.getStatus()>=0&&param.getStatus()<=3){
            if(param.getStatus()==3){
                wrapper.lt("status",2);
            }else{
                wrapper.eq("status",param.getStatus());
            }
        }
        return this.page(page,wrapper);
    }

    @Override
    public boolean removeImage(Integer id, String image) throws Exception {
        Camp camp = this.getById(id);
        String images = StringTool.trimImage(camp.getImage(),image);
        camp.setImage(images);
        OosManagerUtil.deleteFile(image);
        return this.updateById(camp);
    }

    @Override
    public boolean removeImages(Integer id) throws Exception {
        Camp camp = this.getById(id);
        String[] s = camp.getImage().split(",");
        for(int i=0;i<s.length;i++){
            s[i] = StringTool.trimStr(s[i], FilePath.accessUrl+"/");
        }
        List<String> images = new ArrayList<String>();
        Collections.addAll(images, s);
        System.out.println("imagesList:"+images.toString());
        OosManagerUtil.deleteFiles(images);
        camp.setImage("");
        return this.updateById(camp);
    }

    @Override
    public boolean updateCamp(Camp camp, MultipartFile[] files) throws Exception {
        String Path = "camp/img";
        StringBuffer images = new StringBuffer();
        for(int i=0;i<files.length;i++){
            String imagePath = OosManagerUtil.uploadFile(files[i], Path);
            if(i==files.length-1){
                images.append(imagePath);
            }else{
                images.append(imagePath+",");
            }
        }
        Camp camp1 = this.getById(camp.getId());
        if(camp1.getImage()!=null){
            camp.setImage(camp1.getImage()+","+images.toString());
        }else{
            camp.setImage(images.toString());
        }

        return this.updateById(camp);
    }


}

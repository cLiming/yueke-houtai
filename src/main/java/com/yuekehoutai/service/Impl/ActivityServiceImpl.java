package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.param.ActListParam;
import com.yuekehoutai.mapper.ActivityMapper;
import com.yuekehoutai.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuekehoutai.util.OosManagerUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Override
    public Page actList(ActListParam param) throws Exception {
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("act_type_id",param.getType());
        wrapper.eq("city_id",param.getCityId());
        if(!param.getName().isEmpty()){
            wrapper.like("name",param.getName());
        }
        if (param.getCId()!=null){
            wrapper.eq("c_id",param.getCId());
        }
        if(param.getPriceType()==0){
            wrapper.orderByAsc("price");
        }else if (param.getPriceType()==1){
            wrapper.orderByDesc("price");
        }
        Page<Activity> page = new Page<>(param.getPageIndex(), param.getPageNum());
        return this.page(page,wrapper);
    }

    @Override
    public boolean addAct(MultipartFile[] files, Activity activity) throws Exception{
        String Path = "activity/img";
        StringBuffer images = new StringBuffer();
        for(int i=0;i<files.length;i++){
            String imagePath = OosManagerUtil.uploadFile(files[i], Path);
            if(i==files.length-1){
                images.append(imagePath);
            }else{
                images.append(imagePath+",");
            }
        }
        activity.setImage(images.toString());
        return this.save(activity);
    }
}

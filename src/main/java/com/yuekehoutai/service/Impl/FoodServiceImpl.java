package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.Food;
import com.yuekehoutai.domain.param.FoodListParam;
import com.yuekehoutai.mapper.FoodMapper;
import com.yuekehoutai.service.FoodService;
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
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {

    @Override
    public Page<Food> foodList(FoodListParam param) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        Page<Food> foodPage = new Page<Food>(param.getPageIndex(),param.getPageNum());
        if(!param.getName().isEmpty()){
            wrapper.like("name",param.getName());
        }
        if(param.getCId()>0){
            wrapper.eq("c_id",param.getCId());
        }
        if(param.getPriceType()==0){
            wrapper.orderByDesc("price");
        }else if (param.getPriceType()==1){
            wrapper.orderByAsc("price");
        }
        if(param.getSaleType()==0){
            wrapper.orderByAsc("sales");
        }else if (param.getSaleType()==1){
            wrapper.orderByDesc("sales");
        }
        return this.page(foodPage,wrapper);
    }

    @Override
    public boolean addFood(MultipartFile[] files, Food food) throws Exception {
        String Path = "food/img";
        StringBuffer images = new StringBuffer();
        for(int i=0;i<files.length;i++){
            String imagePath = OosManagerUtil.uploadFile(files[i], Path);
            if(i==files.length-1){
                images.append(imagePath);
            }else{
                images.append(imagePath+",");
            }
        }
        food.setImage(images.toString());
        return this.save(food);
    }

    @Override
    public boolean updateFood(MultipartFile[] files, Food param) throws Exception {
        Food food = this.getById(param.getId());
        food.setName(param.getName());
        food.setPrice(param.getPrice());
        food.setAddress(param.getAddress());
        food.setNumber(param.getNumber());
        food.setDescription(param.getDescription());
        if(files!=null){
            String Path = "food/img";
            StringBuffer images = new StringBuffer();
            for(int i=0;i<files.length;i++){
                String imagePath = OosManagerUtil.uploadFile(files[i], Path);
                if(i==files.length-1){
                    images.append(imagePath);
                }else{
                    images.append(imagePath+",");
                }
            }
            food.setImage(food.getImage()+","+images.toString());
        }
        return this.updateById(food);
    }

    @Override
    public boolean removeImage(Integer id, String image) throws Exception {
        Food food = this.getById(id);
        String images = StringTool.trimImage(food.getImage(),image);
        food.setImage(images);
        OosManagerUtil.deleteFile(image);
        return this.updateById(food);
    }

    @Override
    public boolean removeImages(Integer id) throws Exception {
        Food food = this.getById(id);
        String[] s = food.getImage().split(",");
        for(int i=0;i<s.length;i++){
            s[i] = StringTool.trimStr(s[i], FilePath.accessUrl+"/");
        }
        List<String> images = new ArrayList<String>();
        Collections.addAll(images, s);
        System.out.println("imagesList:"+images.toString());
        OosManagerUtil.deleteFiles(images);
        food.setImage("");
        return this.updateById(food);
    }
}

package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.View;
import com.yuekehoutai.domain.param.ViewParam;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.mapper.ViewMapper;
import com.yuekehoutai.service.ViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuekehoutai.util.JsonResult;
import com.yuekehoutai.util.OosManagerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
public class ViewServiceImpl extends ServiceImpl<ViewMapper, View> implements ViewService {
    @Resource
    private ViewMapper viewMapper;
    @Override
    public Page<View> selectAll(ViewParam viewParam) throws Exception {
        System.out.println(viewParam.getPage());
        if(viewParam.getPage()!=null&&!viewParam.getPage().equals("")){
            Page<View> page1 = new Page<View>(viewParam.getPage(),10);
            QueryWrapper<View> wrapper = new QueryWrapper<View>().eq(viewParam.getCId()!=null,"c_id",viewParam.getCId()).like(viewParam.getName()!=null,"name", viewParam.getName());
            //List<View> list = viewMapper.selectList(wrapper);

            //System.out.println(list.size());
            //page1.setRecords(list);
            return this.page(page1,wrapper);
        }
        return null ;
    }

    @Override
    public void insrtView(ViewParam viewParam) throws Exception {
        StringBuffer sb = new StringBuffer();
        String path = "view";
        MultipartFile[] file = viewParam.getFile();
        if(file!=null&&file.length>0){
            for(int i=0;i<file.length;i++){
                String name = file[i].getOriginalFilename();
                if(name.endsWith(".jpg")||name.endsWith(".png")){
                    String file1 = OosManagerUtil.uploadFile(file[i], path);
                    sb.append(file1);
                    if(i!=file.length-1){
                        sb.append(",");
                    }
                }else{
                    throw new ProjectException(502,"文件规格不符合");
                }
            }
        }
        View view = new View();
        BeanUtils.copyProperties(viewParam,view);
        view.setImage(sb.toString());
        viewMapper.insert(view);
    }

    @Override
    public void deleteView(Integer id) throws Exception {
        viewMapper.deleteById(id);
    }

    @Override
    public void updateView(ViewParam viewParam) throws Exception {
        StringBuffer sb = new StringBuffer();
        String path = "view";
        MultipartFile[] file = viewParam.getFile();
        if(file!=null&&file.length>0){
            for(int i=0;i<file.length;i++){
                String name = file[i].getOriginalFilename();
                if(name.endsWith(".jpg")||name.endsWith(".png")){
                    String file1 = OosManagerUtil.uploadFile(file[i], path);
                    sb.append(file1);
                    if(i!=file.length-1){
                        sb.append(",");
                    }
                }else{
                    throw new ProjectException(502,"文件规格不符合");
                }
            }

        }
        View view = new View();
        BeanUtils.copyProperties(viewParam,view);
        view.setImage(sb.toString());
        viewMapper.updateById(view);
    }
}

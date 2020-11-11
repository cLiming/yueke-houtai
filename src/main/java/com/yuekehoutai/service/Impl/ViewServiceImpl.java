package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.View;
import com.yuekehoutai.domain.param.ViewParam;
import com.yuekehoutai.mapper.ViewMapper;
import com.yuekehoutai.service.ViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2020-11-10
 */
@Service
public class ViewServiceImpl extends ServiceImpl<ViewMapper, View> implements ViewService {
    @Resource
    private ViewMapper viewMapper;
    @Override
    public Page<View> selectAll(ViewParam viewParam) throws Exception {
        Page<View> page1 = new Page<View>(viewParam.getPage(),3);

        QueryWrapper<View> wrapper = new QueryWrapper<View>().eq(viewParam.getCId()!=null,"c_id",viewParam.getCId()).like(viewParam.getName()!=null,"name", viewParam.getName());
        List<View> list = viewMapper.selectList(wrapper);
        page1.setRecords(list);
        return page1;
    }

    @Override
    public void insrtView(ViewParam viewParam) throws Exception {
        View view = new View();
        BeanUtils.copyProperties(viewParam,view);
        viewMapper.insert(view);
    }
}

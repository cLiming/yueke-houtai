package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.View;
import com.yuekehoutai.mapper.ViewMapper;
import com.yuekehoutai.service.ViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public Page<View> selectAll(Integer page) throws Exception {
        Page<View> page1 = new Page<View>(page,3);
        List<View> list = viewMapper.selectList(null);
        page1.setRecords(list);
        return page1;
    }
}

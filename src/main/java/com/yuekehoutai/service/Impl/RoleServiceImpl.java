package com.yuekehoutai.service.Impl;

import com.yuekehoutai.domain.Role;
import com.yuekehoutai.mapper.RoleMapper;
import com.yuekehoutai.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectRole() {

        return roleMapper.selectList(null);
    }
}

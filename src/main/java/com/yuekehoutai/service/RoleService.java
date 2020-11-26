package com.yuekehoutai.service;

import com.yuekehoutai.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface RoleService extends IService<Role> {
    public List<Role> selectRole();
}

package com.yuekehoutai.service.Impl;

import com.yuekehoutai.domain.User;
import com.yuekehoutai.mapper.UserMapper;
import com.yuekehoutai.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuekehoutai.domain.Menu;
import com.yuekehoutai.domain.Worker;
import com.yuekehoutai.mapper.WorkerMapper;
import com.yuekehoutai.service.WorkerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

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
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements WorkerService {
    @Resource
    private WorkerMapper workerMapper;
    @Override
    public Worker selectByWorkerName(String tel) throws Exception {
        QueryWrapper<Worker> workerQueryWrapper = new QueryWrapper<>();
        workerQueryWrapper.eq("tel", tel);
        Worker worker = workerMapper.selectOne(workerQueryWrapper);
        if(worker!=null){
            return worker;
        }else{
            throw new UnknownAccountException();
        }
    }

    @Override
    public List<Menu> selectWokerPermissions(Worker worker) {
        workerMapper.selectWokerPermissions(worker);
        return null;
    }
}

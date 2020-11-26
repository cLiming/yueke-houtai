package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuekehoutai.domain.Menu;
import com.yuekehoutai.domain.Role;
import com.yuekehoutai.domain.Worker;
import com.yuekehoutai.domain.WorkerRole;
import com.yuekehoutai.mapper.RoleMapper;
import com.yuekehoutai.mapper.WorkerMapper;
import com.yuekehoutai.mapper.WorkerRoleMapper;
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
    @Resource
    private WorkerRoleMapper workerRoleMapper;
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

        return workerMapper.selectWokerPermissions(worker);
    }

    @Override
    public List<Menu> selectButton(Integer id,Integer pId) {

        return workerMapper.selectButton(id,pId);
    }

    @Override
    public List<Menu> selectMenu(Worker worker) {
        System.err.println(workerMapper.selectMenu(worker));
        return workerMapper.selectMenu(worker);
    }

    @Override
    public List<Worker> selectUser() {
        List<Worker> list = workerMapper.selectList(null);
        return list;
    }

    @Override
    public List<WorkerRole> selectRole(Integer id) {
        List<WorkerRole> list = workerRoleMapper.selectList(new QueryWrapper<WorkerRole>().eq("w_id", id));
        return list;
    }

    @Override
    public void updateRole(Integer id, List<WorkerRole> list) {
        workerRoleMapper.deleteById(id);
        for(WorkerRole li :list){
            WorkerRole wr = new WorkerRole();
            wr.setwId(id);
            wr.setrId(li.getrId());
            workerRoleMapper.insert(wr);
        }
    }
}

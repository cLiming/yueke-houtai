package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuekehoutai.Dto.MenuDto;
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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        QueryWrapper<Worker> workerQueryWrapper = new QueryWrapper<>();
        workerQueryWrapper.eq("tel", worker.getTel());
        Worker worker1 = workerMapper.selectOne(workerQueryWrapper);
        return workerMapper.selectWokerPermissions(worker1.getId());
    }

    @Override
    public List<Menu> selectButton(Worker worker,Integer pId) {
        QueryWrapper<Worker> workerQueryWrapper = new QueryWrapper<>();
        workerQueryWrapper.eq("tel", worker.getTel());
        Worker worker1 = workerMapper.selectOne(workerQueryWrapper);
        return workerMapper.selectButton(worker1.getId(),pId);
    }

    @Override
    public List<MenuDto> selectMenu(Worker worker) {
        //System.out.println(workerMapper.selectMenu(worker));
        QueryWrapper<Worker> workerQueryWrapper = new QueryWrapper<>();
        workerQueryWrapper.eq("tel", worker.getTel());
        Worker worker1 = workerMapper.selectOne(workerQueryWrapper);
        //查询出所有的菜单
        List<Menu> list = workerMapper.selectMenu(worker1.getId());
        //用来放一集按钮
        ArrayList<MenuDto> menuDtos = new ArrayList<>();
        for(Menu menu:list){
            //从数据库里面查出的所有的一级菜单
            if(menu.getLevel().equals("1")){
                MenuDto menuDto = new MenuDto();
                BeanUtils.copyProperties(menu,menuDto);
                menuDtos.add(menuDto);
                menuDto.setChildren(new ArrayList<MenuDto>());
            }
        }
        for(Menu menu:list){
            //从数据库里面查出的所有的二级菜单
            if(!menu.getLevel().equals("1")){
                MenuDto menuDto = new MenuDto();
                BeanUtils.copyProperties(menu,menuDto);
                for (MenuDto menuone:menuDtos){
                    if(menuone.getId()==menuDto.getPId()){
                        menuone.getChildren().add(menuDto);
                    }
                }
            }
        }
        return menuDtos;
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

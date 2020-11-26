package com.yuekehoutai.service;

import com.yuekehoutai.domain.Menu;
import com.yuekehoutai.domain.Role;
import com.yuekehoutai.domain.Worker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuekehoutai.domain.WorkerRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface WorkerService extends IService<Worker> {
    Worker selectByWorkerName(String tel) throws Exception;
    List<Menu> selectWokerPermissions(Worker worker);
    List<Menu> selectButton(@Param("id") Integer id,@Param("pId") Integer pId);
    List<Menu> selectMenu(Worker worker);
    List<Worker>selectUser();
    List<WorkerRole>selectRole(Integer id);
    void updateRole(Integer id,List<WorkerRole>list);
}

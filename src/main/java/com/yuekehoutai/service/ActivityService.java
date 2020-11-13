package com.yuekehoutai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuekehoutai.domain.param.ActListParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface ActivityService extends IService<Activity> {
    Page actList(ActListParam actListParam)throws Exception;
    @Transactional
    boolean addAct(MultipartFile[] files, Activity activity)throws Exception;
    @Transactional
    boolean updateAct(MultipartFile[] files, Activity activity)throws Exception;
    @Transactional
    boolean actRemoveById(Integer id);
    @Transactional
    boolean removeImage(Integer id,String image)throws Exception;
    @Transactional
    boolean removeImages(Integer id)throws Exception;
}

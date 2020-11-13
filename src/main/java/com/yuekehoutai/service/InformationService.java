package com.yuekehoutai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.InformationDto;
import com.yuekehoutai.domain.Information;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface InformationService extends IService<Information> {
    Page<InformationDto> selectInformation(InformationDto informationDto,Page<Information> page) throws Exception;
    void deleteInformation(Integer id) throws Exception;
    void insertInformation(InformationDto informationDto) throws Exception;
    void updateInformation(InformationDto informationDto) throws Exception;
}

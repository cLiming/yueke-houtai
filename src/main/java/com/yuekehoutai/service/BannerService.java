package com.yuekehoutai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.BannerDto;
import com.yuekehoutai.domain.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
public interface BannerService extends IService<Banner> {
    Page<BannerDto> selectBanner(Page<Banner> page) throws Exception;
    void insertBanner(BannerDto bannerDto) throws Exception;
    void updateBanner(BannerDto bannerDto) throws Exception;
    void deleteBanner(Integer id) throws Exception;
}

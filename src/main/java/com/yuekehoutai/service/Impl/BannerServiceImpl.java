package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.BannerDto;
import com.yuekehoutai.domain.Banner;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.mapper.BannerMapper;
import com.yuekehoutai.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    @Resource
    private BannerMapper bannerMapper;
    @Override
    public Page<BannerDto> selectBanner(Page<Banner> page) throws Exception {
        QueryWrapper<Banner> bannerQueryWrapper = new QueryWrapper<>();
        bannerQueryWrapper.eq("status",1);
        Page<BannerDto> bannerDtoPage = new Page<>();
        Page<Banner> page1 = this.page(page, bannerQueryWrapper);
        BeanUtils.copyProperties(page1, bannerDtoPage);
        return bannerDtoPage;
    }

    @Override
    public void insertBanner(BannerDto bannerDto) throws Exception {
        QueryWrapper<Banner> bannerQueryWrapper = new QueryWrapper<>();
        if(bannerDto.getVId()!=null){
            bannerQueryWrapper.eq("v_id",bannerDto.getVId());
            bannerQueryWrapper.eq("status",1);
            Banner banner = bannerMapper.selectOne(bannerQueryWrapper);
            QueryWrapper<Banner> bannerQueryWrapper1 = new QueryWrapper<>();
            bannerQueryWrapper1.eq("v_id",bannerDto.getVId());
            bannerQueryWrapper1.eq("status",0);
            Banner banner1 = bannerMapper.selectOne(bannerQueryWrapper1);
            if(banner!=null){
                throw new ProjectException(500, "该景区已经存在banner");
            }else if(banner1!=null){
                UpdateWrapper<Banner> bannerUpdateWrapper = new UpdateWrapper<>();
                bannerUpdateWrapper.set("status",1);
                bannerUpdateWrapper.eq("v_id", bannerDto.getVId());
                bannerMapper.update(null, bannerUpdateWrapper);
            }else {
                Banner banner2 = new Banner();
                System.err.println(banner2);
                BeanUtils.copyProperties(bannerDto, banner2);
                System.err.println(bannerDto);
                System.err.println(banner2);
                banner1.setStatus(1);
                bannerMapper.insert(banner2);
            }
        }else {
            throw new ProjectException(500, "景区不存在");
        }
    }
    @Override
    public void updateBanner(BannerDto bannerDto) throws Exception {
        QueryWrapper<Banner> bannerQueryWrapper = new QueryWrapper<>();
        bannerQueryWrapper.eq("id",bannerDto.getId());
        Banner banner = bannerMapper.selectOne(bannerQueryWrapper);
        if(banner!=null){
            UpdateWrapper<Banner> bannerUpdateWrapper = new UpdateWrapper<>();
            System.err.println(bannerDto.getImage());
            bannerUpdateWrapper.set(bannerDto.getImage()!=null, "image", bannerDto.getImage());
            bannerUpdateWrapper.eq(bannerDto.getId()!=null, "id", bannerDto.getId());
            bannerUpdateWrapper.eq(bannerDto.getVId()!=null, "v_id", bannerDto.getVId());
            bannerMapper.update(null, bannerUpdateWrapper);
        }else {
            throw new ProjectException(500, "修改商品不存在");
        }
    }

    @Override
    public void deleteBanner(Integer id) throws Exception {
        UpdateWrapper<Banner> bannerUpdateWrapper = new UpdateWrapper<>();
        bannerUpdateWrapper.eq("id",id);
        bannerUpdateWrapper.set("status",0);
        bannerMapper.update(null, bannerUpdateWrapper);
    }

}

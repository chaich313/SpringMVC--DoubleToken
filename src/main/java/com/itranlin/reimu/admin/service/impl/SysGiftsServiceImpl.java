package com.itranlin.reimu.admin.service.impl;

import com.itranlin.reimu.admin.dto.SplitPageDTO;
import com.itranlin.reimu.admin.dto.gift.GiftsDTO;
import com.itranlin.reimu.admin.entity.SysGifts;
import com.itranlin.reimu.admin.mapper.SysGiftsMapper;
import com.itranlin.reimu.admin.service.ISysGiftCodeService;
import com.itranlin.reimu.admin.service.ISysGiftVideoService;
import com.itranlin.reimu.admin.service.ISysGiftsService;
import com.itranlin.reimu.common.handler.BaseContextHandler;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 礼包 服务实现类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
@Service
public class SysGiftsServiceImpl extends ServiceImpl<SysGiftsMapper, SysGifts> implements ISysGiftsService {

    @Resource
    private ISysGiftCodeService giftCodeService;

    @Resource
    private ISysGiftVideoService giftVideoService;

    @Override
    public IPage<SysGifts> page(SplitPageDTO splitPage) {
        return this.page(new PageDTO<>(splitPage.getPage(),splitPage.getPageSize()));
    }

    @Override
    public void commit(GiftsDTO gifts, String type) {
        if (type.equals("add")){
            gifts.setCreateBy(BaseContextHandler.getId());
            this.save(gifts);
            giftCodeService.generatorCode(gifts.getCodeNum(),gifts.getId(),false);
            giftVideoService.bindVideo(gifts.getVideos(),gifts.getId(),false);
        }
        if (type.equals("edit")){
            this.updateById(gifts);
        }
    }

    @Override
    public void codeNumIncrease(Integer num,String id) {
        new LambdaUpdateChainWrapper<>(this.baseMapper)
                .eq(SysGifts::getId,id)
                .setSql("code_num = code_num + "+ num)
                .update();
    }

    @Override
    public void videoNumIncrease(Integer num, String id) {
        new LambdaUpdateChainWrapper<>(this.baseMapper)
                .eq(SysGifts::getId,id)
                .setSql("video_num = video_num + "+ num)
                .update();
    }
}

package com.itranlin.reimu.admin.service.impl;

import com.itranlin.reimu.admin.dto.gift.GiftVideoSearch;
import com.itranlin.reimu.admin.entity.SysGiftVideo;
import com.itranlin.reimu.admin.entity.SysVideo;
import com.itranlin.reimu.admin.mapper.SysGiftVideoMapper;
import com.itranlin.reimu.admin.service.ISysGiftVideoService;
import com.itranlin.reimu.admin.service.ISysGiftsService;
import com.itranlin.reimu.admin.service.ISysVideoService;
import com.itranlin.reimu.admin.vo.gift.GiftVideoVO;
import com.itranlin.reimu.common.handler.BaseContextHandler;
import com.itranlin.reimu.common.util.page.PageUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
@Service
public class SysGiftVideoServiceImpl extends ServiceImpl<SysGiftVideoMapper, SysGiftVideo> implements ISysGiftVideoService {


    @Resource
    @Lazy
    private ISysGiftsService giftsService;

    @Resource
    private ISysVideoService videoService;

    @Override
    public IPage<SysGiftVideo> pageByGift(GiftVideoSearch codeSearch) {
        IPage<SysGiftVideo> giftVideoIPage = new LambdaQueryChainWrapper<>(this.baseMapper)
                .eq(SysGiftVideo::getGiftId,codeSearch.getGiftId())
                .page(new PageDTO<>(codeSearch.getPage(),codeSearch.getPageSize()));

        Map<String, SysVideo> videoMap;
        if (giftVideoIPage.getRecords().size()>0){
            videoMap = videoService.listByIds(giftVideoIPage.getRecords().stream().map(SysGiftVideo::getVideoId).collect(Collectors.toList())).stream().collect(Collectors.toMap(SysVideo::getId,e->e));
        }else {
            videoMap = new HashMap<>();
        }
        return PageUtils.copyPage(giftVideoIPage, giftVideo ->{
            GiftVideoVO videoVO =  GiftVideoVO.translate(giftVideo);
            videoVO.setVideo(videoMap.get(giftVideo.getVideoId()));
            return videoVO;
        });
    }

    @Override
    public void bindVideo(List<String> videoIds, String giftId, boolean updateNum) {
        this.saveBatch(videoIds.stream().map(e ->{
            SysGiftVideo giftVideo = new SysGiftVideo();
            giftVideo.setCreateBy(BaseContextHandler.getId());
            giftVideo.setVideoId(e);
            giftVideo.setGiftId(giftId);
            return giftVideo;
        }).collect(Collectors.toList()));
        giftsService.videoNumIncrease(videoIds.size(),giftId);
    }
}

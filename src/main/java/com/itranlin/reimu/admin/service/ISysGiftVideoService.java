package com.itranlin.reimu.admin.service;

import com.itranlin.reimu.admin.dto.gift.GiftVideoSearch;
import com.itranlin.reimu.admin.entity.SysGiftVideo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
public interface ISysGiftVideoService extends IService<SysGiftVideo> {
    IPage<SysGiftVideo> pageByGift(GiftVideoSearch codeSearch);

    void bindVideo(List<String> videoIds, String giftId,boolean updateNum);
}

package com.itranlin.reimu.admin.controller;

import com.itranlin.reimu.admin.dto.gift.GiftVideoSearch;
import com.itranlin.reimu.admin.entity.SysGiftVideo;
import com.itranlin.reimu.admin.service.ISysGiftVideoService;
import com.itranlin.reimu.common.bean.RequestResult;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/api/gift-video")
public class SysGiftVideoController {
    @Resource
    private ISysGiftVideoService giftVideoService;

    @GetMapping("/get-by-gift")
    public RequestResult<IPage<SysGiftVideo>> getByGift(GiftVideoSearch codeSearch){
        return RequestResult.e(giftVideoService.pageByGift(codeSearch));
    }
}

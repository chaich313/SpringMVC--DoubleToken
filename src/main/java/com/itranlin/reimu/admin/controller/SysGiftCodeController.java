package com.itranlin.reimu.admin.controller;

import com.itranlin.reimu.admin.dto.gift.GiftCodeNumPlus;
import com.itranlin.reimu.admin.dto.gift.GiftCodeSearch;
import com.itranlin.reimu.admin.entity.SysGiftCode;
import com.itranlin.reimu.admin.service.ISysGiftCodeService;
import com.itranlin.reimu.common.bean.RequestResult;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/gift-code")
public class SysGiftCodeController {
    @Resource
    private ISysGiftCodeService giftCodeService;

    @GetMapping("/get-by-gift")
    public RequestResult<IPage<SysGiftCode>> getByGift(GiftCodeSearch codeSearch){
        return RequestResult.e(giftCodeService.pageByGift(codeSearch));
    }
    @PostMapping("/add-code-num")
    public RequestResult<Void> addCodeNum(@RequestBody GiftCodeNumPlus codeNumPlus){
        giftCodeService.generatorCode(codeNumPlus.getCodeNum(), codeNumPlus.getGiftId(),true);
        return RequestResult.e();
    }
}

package com.itranlin.reimu.admin.service;

import com.itranlin.reimu.admin.dto.gift.GiftCodeSearch;
import com.itranlin.reimu.admin.entity.SysGiftCode;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
public interface ISysGiftCodeService extends IService<SysGiftCode> {

    void generatorCode(Integer codeNum,String giftId,boolean updateNum);

    IPage<SysGiftCode> pageByGift(GiftCodeSearch codeSearch);
}

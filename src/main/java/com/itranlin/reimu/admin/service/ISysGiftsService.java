package com.itranlin.reimu.admin.service;

import com.itranlin.reimu.admin.dto.SplitPageDTO;
import com.itranlin.reimu.admin.dto.gift.GiftsDTO;
import com.itranlin.reimu.admin.entity.SysGifts;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 礼包 服务类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
public interface ISysGiftsService extends IService<SysGifts> {
    IPage<SysGifts> page(SplitPageDTO splitPage);

    void commit(GiftsDTO gifts, String type);

    void codeNumIncrease(Integer num,String id);
    void videoNumIncrease(Integer num,String id);

}

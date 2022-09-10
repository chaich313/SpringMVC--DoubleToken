package com.itranlin.reimu.admin.service.impl;

import com.itranlin.reimu.admin.dto.gift.GiftCodeSearch;
import com.itranlin.reimu.admin.entity.SysGiftCode;
import com.itranlin.reimu.admin.mapper.SysGiftCodeMapper;
import com.itranlin.reimu.admin.service.ISysGiftCodeService;
import com.itranlin.reimu.admin.service.ISysGiftsService;
import com.itranlin.reimu.common.handler.BaseContextHandler;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
@Service
public class SysGiftCodeServiceImpl extends ServiceImpl<SysGiftCodeMapper, SysGiftCode> implements ISysGiftCodeService {

    @Resource
    @Lazy
    private ISysGiftsService giftsService;

    @Override
    public void generatorCode(Integer codeNum, String giftId,boolean updateNum) {
        this.saveBatch(IntStream.range(0, codeNum).mapToObj(i -> {
            String code = RandomStringUtils.randomAlphabetic(8);
            SysGiftCode giftCode = new SysGiftCode();
            giftCode.setCode(code);
            giftCode.setGiftId(giftId);
            giftCode.setCreateBy(BaseContextHandler.getId());
            return giftCode;
        }).collect(Collectors.toList()));
        if (updateNum){
            giftsService.codeNumIncrease(codeNum, giftId);
        }
    }

    @Override
    public IPage<SysGiftCode> pageByGift(GiftCodeSearch codeSearch) {
        return new LambdaQueryChainWrapper<>(this.baseMapper)
                .eq(SysGiftCode::getGiftId,codeSearch.getGiftId())
                .page(new PageDTO<>(codeSearch.getPage(),codeSearch.getPageSize()));
    }
}

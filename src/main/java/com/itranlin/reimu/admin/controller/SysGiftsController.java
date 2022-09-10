package com.itranlin.reimu.admin.controller;

import com.itranlin.reimu.admin.dto.SplitPageDTO;
import com.itranlin.reimu.admin.dto.gift.GiftsDTO;
import com.itranlin.reimu.admin.entity.SysGifts;
import com.itranlin.reimu.admin.service.ISysGiftsService;
import com.itranlin.reimu.common.bean.RequestResult;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 礼包 前端控制器
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/api/gifts")
public class SysGiftsController {

    @Resource
    private ISysGiftsService giftsService;

    @GetMapping("get-list")
    public RequestResult<IPage<SysGifts>> getList(SplitPageDTO splitPage){
        return RequestResult.e( giftsService.page(splitPage));
    }

    @PostMapping("commit/{type}")
    public RequestResult<Void> update(@RequestBody GiftsDTO gifts, @PathVariable String type){
        giftsService.commit(gifts,type);
        return RequestResult.e();
    }

    @PostMapping("delete/{id}")
    public RequestResult<Void> getList(@PathVariable String id){
        giftsService.removeById(id);
        return RequestResult.e();
    }
}

package com.itranlin.reimu.admin.controller;

import com.itranlin.reimu.admin.dto.SplitPageDTO;
import com.itranlin.reimu.admin.entity.SysVideo;
import com.itranlin.reimu.admin.service.ISysVideoService;
import com.itranlin.reimu.common.bean.RequestResult;
import com.itranlin.reimu.common.handler.BaseContextHandler;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itranlin
 * @since 2022-09-06
 */
@RestController
@RequestMapping("/api/video")
public class SysVideoController {

    @Resource
    private ISysVideoService videoService;

    @GetMapping("get-list")
    public RequestResult<IPage<SysVideo>> getList(SplitPageDTO splitPage){
        return RequestResult.e( videoService.page(splitPage));
    }
    @GetMapping("get-all-list")
    public RequestResult<List<SysVideo>> getAllList(){
        return RequestResult.e( videoService.list());
    }

    @PostMapping("update")
    public RequestResult<Void> update(@RequestBody SysVideo video){
        videoService.saveOrUpdate(video.setCreateBy(BaseContextHandler.getId()));
        return RequestResult.e();
    }

    @PostMapping("delete/{id}")
    public RequestResult<Void> getList(@PathVariable String id){
        videoService.removeById(id);
        return RequestResult.e();
    }
}

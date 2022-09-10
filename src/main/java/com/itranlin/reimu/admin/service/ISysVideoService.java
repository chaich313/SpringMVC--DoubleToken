package com.itranlin.reimu.admin.service;

import com.itranlin.reimu.admin.dto.SplitPageDTO;
import com.itranlin.reimu.admin.entity.SysVideo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-06
 */
public interface ISysVideoService extends IService<SysVideo> {
    IPage<SysVideo> page(SplitPageDTO splitPage);
}

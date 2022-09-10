package com.itranlin.reimu.admin.service;

import com.itranlin.reimu.admin.entity.SysSource;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-06
 */
public interface ISysSourceService extends IService<SysSource> {

    SysSource getByMd5(String md5);
}

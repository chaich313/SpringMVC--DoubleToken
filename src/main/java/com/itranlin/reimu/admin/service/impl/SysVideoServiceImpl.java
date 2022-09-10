package com.itranlin.reimu.admin.service.impl;

import com.itranlin.reimu.admin.dto.SplitPageDTO;
import com.itranlin.reimu.admin.entity.SysVideo;
import com.itranlin.reimu.admin.mapper.SysVideoMapper;
import com.itranlin.reimu.admin.service.ISysVideoService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-06
 */
@Service
public class SysVideoServiceImpl extends ServiceImpl<SysVideoMapper, SysVideo> implements ISysVideoService {

    @Override
    public IPage<SysVideo> page(SplitPageDTO splitPage) {
        return this.page(new PageDTO<>(splitPage.getPage(),splitPage.getPageSize()));
    }
}

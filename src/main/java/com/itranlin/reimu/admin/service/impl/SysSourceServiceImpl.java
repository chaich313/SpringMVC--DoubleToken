package com.itranlin.reimu.admin.service.impl;

import com.itranlin.reimu.admin.entity.SysSource;
import com.itranlin.reimu.admin.mapper.SysSourceMapper;
import com.itranlin.reimu.admin.service.ISysSourceService;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
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
public class SysSourceServiceImpl extends ServiceImpl<SysSourceMapper, SysSource> implements ISysSourceService {

    @Override
    public SysSource getByMd5(String md5) {
        return new LambdaQueryChainWrapper<>(this.baseMapper)
                .eq(SysSource::getMd5,md5).one();
    }
}

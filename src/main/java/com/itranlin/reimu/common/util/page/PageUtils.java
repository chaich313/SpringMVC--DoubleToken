package com.itranlin.reimu.common.util.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PageUtils {
    public static <T, M> IPage<M> copyPage(IPage<T> source, TranslateEntity<T, M> translate) {
        IPage<M> target = new Page<>();
        target.setPages(source.getPages());
        target.setCurrent(source.getCurrent());
        target.setSize(source.getSize());
        target.setTotal(source.getTotal());
        List<M> targetRecords = source.getRecords().stream().map(translate::trans).collect(Collectors.toList());
        target.setRecords(targetRecords);
        return target;
    }
}

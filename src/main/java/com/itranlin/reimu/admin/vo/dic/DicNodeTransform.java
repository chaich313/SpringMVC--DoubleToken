package com.itranlin.reimu.admin.vo.dic;

import com.itranlin.reimu.admin.entity.Dic;
import com.itranlin.reimu.common.util.tree.fi.NodeTransform;

import org.springframework.beans.BeanUtils;

/**
 * @author itranlin
 * @since 2022/6/1 00:08
 */
public class DicNodeTransform implements NodeTransform<Dic, DicVO, String> {
    @Override
    public DicVO transform(Dic source) {
        DicVO dicVO = new DicVO();
        BeanUtils.copyProperties(source, dicVO);
        return dicVO;
    }
}

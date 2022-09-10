package com.itranlin.reimu.common.util.tree.fi;

import com.itranlin.reimu.common.util.tree.node.SourceNode;
import com.itranlin.reimu.common.util.tree.node.TargetNode;

/**
 * @author itranlin
 * @since 2021/12/16 21:47
 */
@FunctionalInterface
public interface NodeTransform<S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> {

    /**
     * 将源对象转换为目标对象的方法
     *
     * @param source 源对象
     * @return 目标对象
     */
    T transform(S source);
}

package com.itranlin.reimu.common.util.tree.fi;

import com.itranlin.reimu.common.util.tree.node.SourceNode;

/**
 * @author itranlin
 * @since 2021/12/16 21:54
 */
@FunctionalInterface
public interface ParentIdsFormatter<S extends SourceNode<ID>, ID> {

    /**
     * 生成 parentIds
     *
     * @param source 先在的
     * @return parentIds
     */
    String format(S source);
}

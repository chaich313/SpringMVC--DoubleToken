package com.itranlin.basic.common.util.tree.node;


import java.util.List;

/**
 * @author 陈泉林
 * @since 2019/5/10 15:53
 */
public interface TargetNode<T, ID> {

    /**
     * 目标数据id getter
     *
     * @return id
     */
    ID getId();

    /**
     * 目标数据父ID getter
     *
     * @return 父ID
     */
    ID getParentId();

    /**
     * 目标数据是否为叶子节点
     *
     * @return 是否为叶子节点
     */
    boolean isLeaf();

    /**
     * 设置目标数据是否为叶子节点
     *
     * @param leaf 是否为叶子节点
     */
    void setLeaf(Boolean leaf);

    /**
     * 目标数据的子节点
     *
     * @return 子节点
     */
    List<T> getChildren();

    /**
     * 设置目标数据的子节点
     *
     * @param children 子节点
     */
    void setChildren(List<T> children);
}

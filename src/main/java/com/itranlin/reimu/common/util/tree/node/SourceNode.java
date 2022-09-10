package com.itranlin.reimu.common.util.tree.node;


/**
 * @author itranlin
 * @since 2018/11/14,014
 */
public interface SourceNode<ID> {

    /**
     * 源数据id getter
     *
     * @return id
     */
    ID getId();

    /**
     * 源数据ID路径 getter
     *
     * @return id路径
     */
    String getParentIds();

    /**
     * 源数据ID路径 setter
     *
     * @param parentIds id路径
     */
    void setParentIds(String parentIds);
}

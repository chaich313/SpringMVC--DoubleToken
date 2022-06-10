package com.itranlin.basic.common.util.tree;

import com.itranlin.basic.common.util.tree.fi.NodeTransform;
import com.itranlin.basic.common.util.tree.fi.ParentIdsFormatter;
import com.itranlin.basic.common.util.tree.node.SourceNode;
import com.itranlin.basic.common.util.tree.node.TargetNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author itranlin
 * @since 2018/11/14,014
 */
public class TreeNodeBuild {

    /**
     * 处理树
     *
     * @param <S>            传入数据类型 需要继承TreeNodeBaseSource
     * @param <T>            结果数据类型 需要继承TreeNodeBaseTarget
     * @param <ID>           ID的数据类型
     * @param sourceList     放入的原始数据
     * @param transform      数据转换器
     * @param rootId         根节点ID
     * @param sortComparator 排序器
     * @return Z 返回处理结果 {树}
     */
    public static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> T buildTree(
            List<S> sourceList, NodeTransform<S, T, ID> transform, ID rootId, Comparator<T> sortComparator) {


        // 原始数据转MAP (类型已改变)
        Map<ID, T> sourceNodeMap = toMap(sourceList, transform);

        // 通过MAP封装成树
        AtomicReference<T> root = new AtomicReference<>();
        sourceNodeMap.forEach((key, value) -> {
            if (value.getParentId() != null) {
                addChildToParent(sourceNodeMap.get(value.getParentId()), value);
            } else {
                root.set(value);
            }
        });
        T rootNode;
        if (null == rootId) {
            rootNode = root.get();
        } else {
            rootNode = sourceNodeMap.get(rootId);
        }
        if (null != sortComparator) {
            sortForest(rootNode.getChildren(), sortComparator);
        }
        return rootNode;
    }


    /**
     * 处理树 不进行排序
     *
     * @param <S>        传入数据类型 需要继承TreeNodeBaseSource
     * @param <T>        结果数据类型 需要继承TreeNodeBaseTarget
     * @param <ID>       ID的数据类型
     * @param sourceList 放入的原始数据
     * @param transform  数据转换器
     * @param rootId     根节点ID
     * @return Z 返回处理结果 {树}
     */
    public static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> T buildTree(
            List<S> sourceList, NodeTransform<S, T, ID> transform, ID rootId) {
        return buildTree(sourceList, transform, rootId, null);
    }

    /**
     * 处理森林 指定根
     *
     * @param <S>            传入数据类型 需要继承TreeNodeBaseSource
     * @param <T>            结果数据类型 需要继承TreeNodeBaseTarget
     * @param <ID>           ID的数据类型
     * @param sourceList     放入的原始数据
     * @param transform      数据转换器
     * @param rootIds        根节点ID
     * @param sortComparator 排序器
     * @return List<Z> 返回处理结果
     */
    public static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> List<T> buildForest(
            List<S> sourceList, NodeTransform<S, T, ID> transform, Comparator<T> sortComparator, ID... rootIds) {

        if (rootIds == null) {
            throw new IllegalArgumentException("rootIds must not be null");
        }
        // 原始数据转MAP (类型已改变)
        Map<ID, T> sourceNodeMap = toMap(sourceList, transform);

        // 通过MAP封装成森林
        sourceNodeMap.forEach((key, value) -> {
            if (null != (value.getParentId())) {
                addChildToParent(sourceNodeMap.get(value.getParentId()), value);
            }
        });
        List<T> rootList = new ArrayList<>();
        for (ID rootId : rootIds) {
            rootList.add(sourceNodeMap.get(rootId));
        }
        if (null != sortComparator) {
            sortForest(rootList, sortComparator);
        }
        return rootList;
    }

    /**
     * 处理森林 指定根 不排序
     *
     * @param <S>        传入数据类型 需要继承TreeNodeBaseSource
     * @param <T>        结果数据类型 需要继承TreeNodeBaseTarget
     * @param <ID>       ID的数据类型
     * @param sourceList 放入的原始数据
     * @param transform  数据转换器
     * @param rootIds    根节点ID
     * @return List<Z> 返回处理结果
     */
    public static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> List<T> buildForest(
            List<S> sourceList, NodeTransform<S, T, ID> transform, ID... rootIds) {
        return buildForest(sourceList, transform, null, rootIds);
    }


    /**
     * 处理森林 自动识别根
     *
     * @param <S>            传入数据类型 需要继承TreeNodeBaseSource
     * @param <T>            结果数据类型 需要继承TreeNodeBaseTarget
     * @param <ID>           ID的数据类型
     * @param sourceList     放入的原始数据
     * @param transform      数据转换器
     * @param sortComparator 排序器
     * @return List<Z> 返回处理结果
     */
    public static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> List<T> buildForest(
            List<S> sourceList, NodeTransform<S, T, ID> transform, Comparator<T> sortComparator) {

        // 原始数据转MAP (类型已改变)
        Map<ID, T> sourceNodeMap = toMap(sourceList, transform);

        // 通过MAP封装成森林
        List<T> rootList = new ArrayList<>();
        sourceNodeMap.forEach((key, value) -> {
            if (null != value.getParentId() && null != sourceNodeMap.get(value.getParentId())) {
                addChildToParent(sourceNodeMap.get(value.getParentId()), value);
            } else {
                rootList.add(value);
            }
        });
        if (null != sortComparator) {
            sortForest(rootList, sortComparator);
        }
        return rootList;
    }


    /**
     * 处理森林 自动识别根 不排序
     *
     * @param <S>        传入数据类型 需要继承TreeNodeBaseSource
     * @param <T>        结果数据类型 需要继承TreeNodeBaseTarget
     * @param <ID>       ID的数据类型
     * @param sourceList 放入的原始数据
     * @param transform  数据转换器
     * @return List<Z> 返回处理结果
     */
    public static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> List<T> buildForest(
            List<S> sourceList, NodeTransform<S, T, ID> transform) {
        return buildForest(sourceList, transform, (Comparator<T>) null);
    }

    /**
     * 重新设置 数据路径 修改原始数据，过程需要转换为树
     *
     * @param <S>       传入数据类型 需要继承TreeNodeBaseSource
     * @param <T>       结果数据类型 需要继承TreeNodeBaseTarget
     * @param <ID>      ID的数据类型
     * @param source    放入的原始数据
     * @param transform 数据转换器
     * @param formatter parentIds的生成方法
     */
    public static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> void buildParentIds(
            List<S> source, ID rootId, NodeTransform<S, T, ID> transform, ParentIdsFormatter<S, ID> formatter) {
        Map<ID, S> sourceNodeMap = source.stream().collect(Collectors.toMap(S::getId, e -> e, (e1, e2) -> e2));
        T z = TreeNodeBuild.buildTree(source, transform, rootId);
        traverseTree(sourceNodeMap, z, formatter);
    }

    private static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> Map<ID, T> toMap(
            List<S> source, NodeTransform<S, T, ID> transform) {
        return source.stream().map(transform::transform).collect(Collectors.toMap(T::getId, t -> t, (t1, t2) -> t2));
    }

    private static <T extends TargetNode<T, ID>, ID> void sortForest(List<T> nodes, Comparator<T> sortComparator) {
        nodes.sort(sortComparator);
        nodes.forEach(node -> {
            if (!node.isLeaf()) {
                sortForest(node.getChildren(), sortComparator);
            } else {
                node.setChildren(null);
            }
        });
    }

    private static <S extends SourceNode<ID>, T extends TargetNode<T, ID>, ID> void traverseTree(
            Map<ID, S> sourceNodeMap, T t, ParentIdsFormatter<S, ID> formatter) {
        if (null != t.getParentId() && null != sourceNodeMap.get(t.getParentId())) {
            S s = sourceNodeMap.get(t.getParentId());
            if (s.getParentIds() == null) {
                s.setParentIds("");
            }
            String parentIds = formatter.format(s);
            S s2 = sourceNodeMap.get(t.getId());
            s2.setParentIds(parentIds);
        }
        List<T> children = t.getChildren();
        if (null == (children)) {
            children = new ArrayList<>();
        }
        children.forEach(e -> traverseTree(sourceNodeMap, e, formatter));
    }


    private static <T extends TargetNode<T, ID>, ID> void addChildToParent(T parent, T child) {
        if (null == parent.getChildren()) {
            parent.setChildren(new ArrayList<>());
        }
        parent.getChildren().add(child);
        parent.setLeaf(false);
    }
}

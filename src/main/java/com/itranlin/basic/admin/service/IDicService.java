package com.itranlin.basic.admin.service;


import com.itranlin.basic.admin.dto.dic.DicCommitDTO;
import com.itranlin.basic.admin.entity.Dic;
import com.itranlin.basic.admin.vo.dic.DicVO;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author Ayo
 **/
public interface IDicService extends IService<Dic> {

    /**
     * 提交字典数据
     *
     * @param dto 字典数据
     */
    void commit(DicCommitDTO dto);

    /**
     * 根据type获取部门森林结构
     *
     * @return List<DicVO>
     */
    List<DicVO> getTreeAll();

    /**
     * 获取扁平化数据
     *
     * @return Map<String, DicVO>
     */
    Map<String, DicVO> getAll();

    /**
     * 通过Code 获取其子节点
     *
     * @param code code编码
     * @return 子节点数据
     */
    List<Dic> getChildrenByCode(String code);

    /**
     * 通过Code 获取其子节点
     *
     * @param codes code编码
     * @return 各自子节点数据
     */
    Map<String, List<Dic>> getCodeList(List<String> codes);


    /**
     * 根据ID删除字典，如果有子节点则不允许直接删除
     *
     * @param id ID
     */
    void deletedById(String id);
}

package com.itranlin.basic.admin.service.impl;

import com.itranlin.basic.admin.dto.dic.DicCommitDTO;
import com.itranlin.basic.admin.entity.Dic;
import com.itranlin.basic.admin.mapper.DicMapper;
import com.itranlin.basic.admin.service.IDicService;
import com.itranlin.basic.admin.vo.dic.DicNodeTransform;
import com.itranlin.basic.admin.vo.dic.DicVO;
import com.itranlin.basic.common.bean.StatusEnum;
import com.itranlin.basic.common.exception.RequestException;
import com.itranlin.basic.common.util.tree.TreeNodeBuild;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ayo
 **/
@Service
@Slf4j
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements IDicService {


    @Override
    public void commit(DicCommitDTO dto) {
        String id = dto.getId();
        Dic dic;
        if (ObjectUtils.isEmpty(id)) {
            dic = new Dic();
            BeanUtils.copyProperties(dto, dic);
            dic.setCreateTime(System.currentTimeMillis());
            if (!StringUtils.isNullOrEmpty(dto.getCode())) {
                Dic code = this.getOne(new QueryWrapper<Dic>().eq("code", dto.getCode()));
                if (code != null) {
                    throw new RequestException(StatusEnum.FAIL, "code已存在");
                }
                dic.setCode(dto.getCode());
            }
            if (!ObjectUtils.isEmpty(dto.getParentId())) {
                Dic parentDic = this.getById(dto.getParentId());
                if (parentDic == null) {
                    throw new RequestException(StatusEnum.FAIL, "该父级不存在");
                }
                dic.setParentIds(dic.getParentIds() + "/" + parentDic.getId());
            } else {
                dic.setParentIds("");
            }
            dic.setDeleted(false);
        } else {
            dic = this.getById(id);
            if (dic == null) {
                throw new RequestException(StatusEnum.FAIL, "字典项不存在");
            }
            dic.setTheSort(dto.getTheSort());
            dic.setName(dto.getName());
            dic.setAllowDelete(dto.getAllowDelete());
            if (!StringUtils.isNullOrEmpty(dto.getCode())) {
                if (!Objects.equals(dic.getCode(), dto.getCode())) {
                    Dic code = this.getOne(new QueryWrapper<Dic>().eq("code", dto.getCode()));
                    if (code != null) {
                        throw new RequestException(StatusEnum.FAIL, "code已存在");
                    }
                    dic.setCode(dto.getCode());
                }
            }
        }
        this.saveOrUpdate(dic);
    }

    @Override
    public List<DicVO> getTreeAll() {
        List<Dic> dicList = this.list();
        if (dicList.size() == 0) {
            return new ArrayList<>();
        }
        return TreeNodeBuild.buildForest(dicList, new DicNodeTransform());
    }

    @Override
    public Map<String, DicVO> getAll() {
        List<Dic> dicList = this.list();
        return dicList.stream().map(e -> {
            DicVO dicVO = new DicVO();
            BeanUtils.copyProperties(e, dicVO);
            return dicVO;
        }).collect(Collectors.toMap(DicVO::getCode, e -> e, (e1, e2) -> e2));
    }

    @Override
    public List<Dic> getChildrenByCode(String code) {
        QueryWrapper<Dic> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", false)
                .eq("code", code);

        Dic dicOne = this.getOne(wrapper, false);
        if (dicOne == null) {
            throw new RequestException(StatusEnum.FAIL, "未获取到本节点");
        }
        QueryWrapper<Dic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", dicOne.getId());
        queryWrapper.eq("deleted", false);
        return this.list(queryWrapper);
    }

    @Override
    public Map<String, List<Dic>> getCodeList(List<String> code) {
        Map<String, List<Dic>> map = new HashMap<>(code.size() * 4 / 3);
        // todo @itranlin 需要优化
        code.forEach(c -> map.put(c, this.getChildrenByCode(c)));
        return map;
    }

    @Override
    public void deletedById(String id) {
        Dic dic = this.getById(id);
        if (!dic.getAllowDelete()) {
            throw new RequestException(StatusEnum.FAIL, "该标签不允许被删除");
        }
        QueryWrapper<Dic> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        if (!ObjectUtils.isEmpty(this.list(wrapper))) {
            throw new RequestException(StatusEnum.FAIL, "请删除该数据下的子数据，再删除该数据");
        }
        this.removeById(id);
    }

}

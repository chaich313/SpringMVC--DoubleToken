package com.itranlin.basic.admin.controller;

import com.itranlin.basic.admin.dto.dic.DicCommitDTO;
import com.itranlin.basic.admin.entity.Dic;
import com.itranlin.basic.admin.service.IDicService;
import com.itranlin.basic.admin.vo.dic.DicVO;
import com.itranlin.basic.common.bean.RequestResult;
import com.itranlin.basic.common.bean.StatusEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * @author Ayo
 **/
@RestController
@RequestMapping(value = {"/api/dic"})
@Api(tags = {"字典项相关"})
public class DicController {
    @Resource
    private IDicService service;

    @PostMapping(value = {"/update"})
    @ApiOperation(value = "创建字典项")
    public RequestResult<Void> update(@RequestBody DicCommitDTO dto) {
        service.commit(dto);
        return RequestResult.e(StatusEnum.OK);
    }

    @GetMapping(value = "/get-tree/all")
    @ApiOperation(value = "获取字典树")
    public RequestResult<List<DicVO>> getTreeByType() {
        return RequestResult.e(StatusEnum.OK, service.getTreeAll());
    }

    @GetMapping(value = "/get/all")
    @ApiOperation(value = "获取扁平化所有字典")
    public RequestResult<Map<String, DicVO>> getAllDept() {
        return RequestResult.e(StatusEnum.OK, service.getAll());
    }

    @GetMapping(value = "/get/code/{code}")
    @ApiOperation(value = "获取code下的数据")
    public RequestResult<List<Dic>> getAllCode(@PathVariable String code) {
        return RequestResult.e(StatusEnum.OK, service.getChildrenByCode(code));
    }

    @PostMapping(value = "/get/code")
    @ApiOperation(value = "获取code下的数据")
    public RequestResult<Map<String, List<Dic>>> getCodeList(@RequestBody List<String> code) {
        return RequestResult.e(StatusEnum.OK, service.getCodeList(code));
    }


    @GetMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除字典项")
    public RequestResult<Void> delete(@PathVariable String id) {
        service.deletedById(id);
        return RequestResult.e(StatusEnum.OK);
    }

}

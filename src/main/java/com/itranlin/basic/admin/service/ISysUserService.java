package com.itranlin.basic.admin.service;


import com.itranlin.basic.admin.dto.account.PwdDTO;
import com.itranlin.basic.admin.dto.account.SignDTO;
import com.itranlin.basic.admin.dto.user.UserCommitDTO;
import com.itranlin.basic.admin.dto.user.UserDTO;
import com.itranlin.basic.admin.entity.SysUser;
import com.itranlin.basic.admin.vo.account.SignInVO;
import com.itranlin.basic.admin.vo.user.UserVO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author CY
 * @since 2020-03-15
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 登录
     *
     * @param signDTO 登录数据
     * @return 登录结果数据
     */
    SignInVO signIn(SignDTO signDTO);


    /**
     * 刷新凭证
     *
     * @return 登录结果数据
     */
    SignInVO refresh();


    /**
     * 用户分页
     *
     * @param userDTO 查询数据
     * @return 分页结果
     */
    IPage<UserVO> userPage(UserDTO userDTO);

    /**
     * 提交数据
     *
     * @param commitDTO 提交数据
     */
    void commit(UserCommitDTO commitDTO);

    /**
     * 修改密码
     *
     * @param pwdDTO 提交数据
     */
    void pwd(PwdDTO pwdDTO);


}

package com.itranlin.reimu.admin.service.impl;

import com.itranlin.reimu.admin.dto.account.PwdDTO;
import com.itranlin.reimu.admin.dto.account.SignDTO;
import com.itranlin.reimu.admin.dto.user.UserCommitDTO;
import com.itranlin.reimu.admin.dto.user.UserDTO;
import com.itranlin.reimu.admin.entity.SysUser;
import com.itranlin.reimu.admin.mapper.SysUserMapper;
import com.itranlin.reimu.admin.service.ISysUserService;
import com.itranlin.reimu.admin.vo.account.SignInVO;
import com.itranlin.reimu.admin.vo.user.UserVO;
import com.itranlin.reimu.common.bean.StatusEnum;
import com.itranlin.reimu.common.exception.RequestException;
import com.itranlin.reimu.common.handler.BaseContextHandler;
import com.itranlin.reimu.common.util.Encrypt;
import com.itranlin.reimu.common.util.TokenUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CY
 * @since 2020-03-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SignInVO signIn(SignDTO signDTO) {
        SignInVO signInVO = new SignInVO();
        if ("".equals(signDTO.getUsername()) || "".equals(signDTO.getPassword())) {
            throw new RequestException(StatusEnum.SING_IN_INPUT_EMPTY);
        }
        SysUser user = this.getOne(
                new QueryWrapper<SysUser>()
                        .eq("username", signDTO.getUsername())
                        .eq("password", Encrypt.md5(signDTO.getPassword() + signDTO.getUsername()))
        );
        if (null == user) {
            throw new RequestException(StatusEnum.SIGN_IN_INPUT_FAIL);
        }
        UserVO userVO = new UserVO();
        String token = TokenUtil.sign(user);
        BeanUtils.copyProperties(user, userVO);
        signInVO.setUserInfo(userVO);
        signInVO.setAuthorization(token);
        return signInVO;
    }

    @Override
    public SignInVO refresh() {
        SignInVO signInVO = new SignInVO();
        SysUser user = this.getById(BaseContextHandler.getId());
        UserVO userVO = new UserVO();
        String token = TokenUtil.sign(user);
        BeanUtils.copyProperties(user, userVO);
        signInVO.setUserInfo(userVO);
        signInVO.setAuthorization(token);
        return signInVO;
    }

    @Override
    public IPage<UserVO> userPage(UserDTO userDTO) {
        IPage<UserVO> resultPage = new Page<>();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.like(null != userDTO.getName(), "real_name", userDTO.getName());
        IPage<SysUser> userPage = this.page(new Page<>(userDTO.getPage(), userDTO.getPageSize()), wrapper);
        BeanUtils.copyProperties(userPage, resultPage);
        if (null == userPage.getRecords() || userPage.getRecords().size() == 0) {
            return resultPage;
        }
        List<UserVO> userVos = new ArrayList<>();
        userPage.getRecords().forEach(e -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(e, userVO);
            userVos.add(userVO);
        });
        resultPage.setRecords(userVos);
        return resultPage;
    }

    @Override
    public void commit(UserCommitDTO commitDTO) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(commitDTO, user);
        if (null == user.getId()) {
            SysUser userOld = this.getOne(new QueryWrapper<SysUser>().eq("username", commitDTO.getUsername()), false);
            if (null != userOld) {
                throw new RequestException(StatusEnum.FAIL, "用户名已存在");
            }
            user.setPassword(Encrypt.md5((null == commitDTO.getPassword() ? commitDTO.getUsername().substring(
                    commitDTO.getUsername().length() - 6) : commitDTO.getPassword()) + user.getUsername()));
        }
        this.saveOrUpdate(user);
    }

    @Override
    public void pwd(PwdDTO pwdDTO) {
        SysUser user = this.getById(BaseContextHandler.getId());
        if (null == user) {
            throw new RequestException(StatusEnum.FAIL, "用户不存在");
        }
        if (!user.getPassword().equals(Encrypt.md5(pwdDTO.getPassword() + user.getUsername()))) {
            throw new RequestException(StatusEnum.FAIL, "密码错误");
        }
        UpdateWrapper<SysUser> wrapper = new UpdateWrapper<>();
        wrapper.set("password", Encrypt.md5(pwdDTO.getNewPassword() + user.getUsername()));
        wrapper.eq("id", BaseContextHandler.getId());
        this.update(wrapper);
    }
}

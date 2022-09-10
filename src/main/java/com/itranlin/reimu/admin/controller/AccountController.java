package com.itranlin.reimu.admin.controller;

import com.itranlin.reimu.admin.dto.account.MpLoginDTO;
import com.itranlin.reimu.admin.dto.account.PwdDTO;
import com.itranlin.reimu.admin.dto.account.SignDTO;
import com.itranlin.reimu.admin.service.ISysCustomerService;
import com.itranlin.reimu.admin.service.ISysUserService;
import com.itranlin.reimu.admin.vo.account.SignInVO;
import com.itranlin.reimu.common.bean.RequestResult;
import com.itranlin.reimu.common.bean.StatusEnum;
import com.itranlin.reimu.common.handler.BaseContextHandler;
import com.itranlin.reimu.common.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author itranlin
 */
@RestController
@RequestMapping(value = {"/api/account"})
@Api(tags = {"账户相关"})
public class AccountController {

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysCustomerService customerService;

    @PostMapping(value = {"/sign-in"})
    @ApiOperation(value = "登录")
    public RequestResult<SignInVO> signIn(
            @RequestBody @Validated @ApiParam(value = "登录数据", required = true) SignDTO signDTO) {
        return RequestResult.e(StatusEnum.SIGN_IN_OK, userService.signIn(signDTO));
    }

    @PostMapping("mp-sign-in")
    @ApiOperation(value = "登录")
    public RequestResult<SignInVO> login(@RequestBody MpLoginDTO mpLogin) {
        return RequestResult.e(customerService.login(mpLogin.getCode(), mpLogin.getState()));
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "注销登录")
    public RequestResult<Void> logout() {
        RedisUtil.del(BaseContextHandler.getToken());
        return RequestResult.e(StatusEnum.LOGOUT_OK);
    }

    @GetMapping(value = "/refresh")
    @ApiOperation(value = "刷新token并获取最新用户信息")
    public RequestResult<SignInVO> refresh() {
        return RequestResult.e(StatusEnum.OK, userService.refresh());
    }


    @PostMapping(value = "/reset-pwd")
    @ApiOperation(value = "修改密码")
    public RequestResult<Void> pwd(@RequestBody @Validated PwdDTO pwdDTO) {
        userService.pwd(pwdDTO);
        return RequestResult.e(StatusEnum.OK);
    }
}

package com.itranlin.reimu.admin.service;

import com.itranlin.reimu.admin.entity.SysCustomer;
import com.itranlin.reimu.admin.vo.account.SignInVO;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-10
 */
public interface ISysCustomerService extends IService<SysCustomer> {

    SignInVO login(String code, String state);

    SysCustomer getByOpenId(String openId);
}

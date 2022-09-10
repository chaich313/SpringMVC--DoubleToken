package com.itranlin.reimu.admin.service.impl;

import com.itranlin.reimu.admin.entity.SysCustomer;
import com.itranlin.reimu.admin.mapper.SysCustomerMapper;
import com.itranlin.reimu.admin.service.ISysCustomerService;
import com.itranlin.reimu.admin.vo.account.SignInVO;
import com.itranlin.reimu.common.bean.StatusEnum;
import com.itranlin.reimu.common.exception.RequestException;
import com.itranlin.reimu.common.util.TokenUtil;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itranlin
 * @since 2022-09-10
 */
@Service
public class SysCustomerServiceImpl extends ServiceImpl<SysCustomerMapper, SysCustomer> implements ISysCustomerService {

    @Resource
    private WxMpService wxMpService;

    @Override
    public SignInVO login(String code, String state) {
        try {
            WxOAuth2AccessToken auth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
            WxOAuth2UserInfo wxMpUser = wxMpService.getOAuth2Service().getUserInfo(auth2AccessToken,null);
            SysCustomer dbCustomer = this.getByOpenId(wxMpUser.getOpenid());
            if (null == dbCustomer){
                dbCustomer = new SysCustomer();
                BeanUtils.copyProperties(wxMpUser,dbCustomer);
                this.save(dbCustomer);
            }
            SignInVO signInVO = new SignInVO();

            String token = TokenUtil.sign(dbCustomer);
            signInVO.setUserInfo(dbCustomer);
            signInVO.setAuthorization(token);
            return signInVO;
        }catch (Exception e){
            throw new RequestException(StatusEnum.FAIL, "登录失败！",e);
        }
    }

    @Override
    public SysCustomer getByOpenId(String openId) {
        return new LambdaQueryChainWrapper<>(this.baseMapper).eq(SysCustomer::getOpenid,openId)
                .one();
    }
}

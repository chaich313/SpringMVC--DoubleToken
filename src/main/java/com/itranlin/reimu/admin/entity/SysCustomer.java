package com.itranlin.reimu.admin.entity;

import com.itranlin.reimu.admin.bo.BaseUser;
import com.itranlin.reimu.common.bean.Constants;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author itranlin
 * @since 2022-09-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_customer")
@ApiModel(value = "SysCustomer对象")
public class SysCustomer implements BaseUser,Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String openid;

    private String nickname;

    private String sex;

    private String city;

    private String province;

    private String country;

    private String headImgUrl;

    private String unionId;

    private String privileges;

    private String snapshotUser;

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public String getType() {
        return Constants.USER_TYPE.MP;
    }
}

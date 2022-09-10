package com.itranlin.reimu.admin.dto.gift;

import com.itranlin.reimu.admin.dto.SplitPageDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GiftVideoSearch extends SplitPageDTO {
    private String giftId;
}

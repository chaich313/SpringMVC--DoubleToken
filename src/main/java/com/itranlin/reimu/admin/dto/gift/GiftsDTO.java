package com.itranlin.reimu.admin.dto.gift;

import com.itranlin.reimu.admin.entity.SysGifts;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GiftsDTO extends SysGifts {
    private List<String> videos;
}

package com.itranlin.reimu.admin.vo.gift;

import com.itranlin.reimu.admin.entity.SysGiftVideo;
import com.itranlin.reimu.admin.entity.SysVideo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GiftVideoVO extends SysGiftVideo {
    private SysVideo video;


    public static GiftVideoVO translate(SysGiftVideo video){
        GiftVideoVO videoVO = new GiftVideoVO();
        videoVO.setVideoId(video.getVideoId());
        videoVO.setGiftId(video.getGiftId());
        videoVO.setCreateBy(video.getCreateBy());
        videoVO.setCreateTime(video.getCreateTime());
        videoVO.setId(video.getId());
        return videoVO;
    }
}

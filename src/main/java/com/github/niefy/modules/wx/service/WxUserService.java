package com.github.niefy.modules.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.niefy.modules.wx.entity.WxUser;

import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;
import java.util.Map;

public interface WxUserService extends IService<WxUser> {
    /**
     * 分页查询用户数据
     * @param params
     * @return
     */
    IPage<WxUser> queryPage(Map<String, Object> params);

    /**
     * 根据openid更新用户信息
     *
     * @param openid
     * @return
     */
    WxUser refreshUserInfo(String openid);

    /**
     * 异步批量更新用户信息
     * @param openidList
     */
    void refreshUserInfoAsync(String[] openidList);

    /**
     * 数据存在时更新，否则新增
     *
     * @param user
     */
    void updateOrInsert(WxUser user);

    /**
     * 取消关注，更新关注状态
     *
     * @param openid
     */
    void unsubscribe(String openid);
    /**
     * 同步用户列表
     */
    void syncWxUsers();
    
    /**
     * 通过传入的openid列表，同步用户列表
     * @param openids
     */
    void syncWxUsers(List<String> openids) throws WxErrorException;

}

package com.github.niefy.modules.wx.handler;

import com.github.niefy.modules.wx.service.WeixinMsgService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang
 */
@Component
public class ScanHandler extends AbstractHandler {
    @Autowired
    WeixinMsgService weixinMsgService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        //扫码事件处理
        this.logger.info("用户扫描带参二维码 OPENID: " + wxMpXmlMessage.getFromUser());
        weixinMsgService.wxReplyMsg(true, wxMpXmlMessage.getFromUser(), wxMpXmlMessage.getEventKey());

        return null;
    }
}

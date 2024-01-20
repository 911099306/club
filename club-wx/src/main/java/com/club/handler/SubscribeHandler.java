package com.club.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/19
 **/
@Slf4j
@Component
public class SubscribeHandler implements WxChatMsgHandler{
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.SUBSCRIBE;
    }
    @Override
    public String dealMsg(Map<String, String> messageMap) {
        log.info("触发用户关注事件！");
        String toUserName = messageMap.get("ToUserName");
        String fromUserName = messageMap.get("FromUserName");
        String subscribeContent = "感谢您的关注，我是monou, 叫我好哥哥~~";

        String content = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + subscribeContent + "]]></Content>\n" +
                "</xml>";
        return content;
    }
}

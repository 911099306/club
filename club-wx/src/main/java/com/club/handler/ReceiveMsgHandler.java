package com.club.handler;

import jdk.internal.dynalink.beans.StaticClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/19
 **/
@Slf4j
@Component
public class ReceiveMsgHandler implements WxChatMsgHandler {

    private static final String KeyWord = "验证码";
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(Map<String, String> messageMap) {
        log.info(("用户发送消息来了 "));
        String content = messageMap.get("Content");
        log.info("传过来的数据是：{}",content);
        String toUserName = messageMap.get("ToUserName");
        String fromUserName = messageMap.get("FromUserName");
        if (!KeyWord.equals(content)) {
            return "<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[无事请勿骚扰]]></Content>\n" +
                    "</xml>";
        }

        Random random = new Random();
        int num = random.nextInt(1000);
        String numContent = "没事不要找我 啊  \n你的验证码是:"+num;

        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + numContent + "]]></Content>\n" +
                "</xml>";
    }
}

package com.club.handler;

import java.util.Map;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/19
 **/
public class OtherMsgHandler implements WxChatMsgHandler{
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return null;
    }

    @Override
    public String dealMsg(Map<String, String> map) {
        return null;
    }
}

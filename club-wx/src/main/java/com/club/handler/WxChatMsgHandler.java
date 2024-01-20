package com.club.handler;

import java.util.Map;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/19
 **/
public interface WxChatMsgHandler {
    WxChatMsgTypeEnum getMsgType();

    String dealMsg(Map<String ,String > map);
}

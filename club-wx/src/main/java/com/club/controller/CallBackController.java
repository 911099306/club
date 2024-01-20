package com.club.controller;

import com.club.handler.WxChatMsgFactory;
import com.club.handler.WxChatMsgHandler;
import com.club.utils.MessageUtil;
import com.club.utils.SHA1;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @author serendipity
 * @version 1.0
 * @date 2024/1/19
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
public class CallBackController {
    private static final String token = "club_Oxzncujasdj";

    private final WxChatMsgFactory wxChatMsgFactory;

    @RequestMapping("/test")
    public String test() {
        return "hello World";
    }

    /**
     * 回调消息校验
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature:{}, timestamp:{}, nonce:{}, echostr:{}, ",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        log.info("shaStr: {}", shaStr);
        assert shaStr != null;
        if (shaStr.equals(signature)) {
            return echostr;
        }
        return "unknown";
    }


    @PostMapping(value = "/callback", produces = "application/xml;charset=UTF-8")
    public String callback(@RequestBody String requstBody,
                           @RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam(value = "msg_signature", required = false) String msg_signature) {
        log.info("接受到微信的请求：requstBody：{}，", requstBody);
        Map<String, String> msgMap = MessageUtil.parseXml(requstBody);
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");
        log.info("msgType: {}, event: {}", msgType, event);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(msgType);
        if (!"".equals(event)) {
            stringBuilder.append(".");
            stringBuilder.append(event);
        }
        String msgTypeKey = stringBuilder.toString();

        WxChatMsgHandler handlerByMsgType = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);
        String replyContent = handlerByMsgType.dealMsg(msgMap);
        if (Objects.isNull(replyContent)) {
            return "unknow";
        }
        log.info("replyContent:{}",replyContent);

        return replyContent;

    }

}

package com.ying.chat.message.controller;

import com.ying.chat.message.model.Message;
import com.ying.core.er.Jsoner;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bvvy
 * @date 2018/8/19
 */
@Controller
@RequestMapping("/'v1/message")
@Api(tags = "聊天")
public class MessageController extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> CHATS = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Message msg = Jsoner.from(payload, Message.class);
        Integer toId = msg.getToId();
        WebSocketSession webSocketSession = CHATS.get(String.valueOf(toId));
        webSocketSession.sendMessage(new TextMessage(msg.getContent()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CHATS.put(session.getId(), session);
    }

}

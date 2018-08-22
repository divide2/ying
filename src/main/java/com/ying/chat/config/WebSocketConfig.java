package com.ying.chat.config;

import com.ying.chat.controller.MessageController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author bvvy
 * @date 2018/8/19
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MessageController messageController;

    public WebSocketConfig(MessageController messageController) {
        this.messageController = messageController;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(messageController, "/myHandler")
                .addInterceptors(new ChatInterceptor())
                .setAllowedOrigins("http://localhost:8080","http://www.blue-zero.com");

    }


}

package com.soulware.tcompro.order.interfaces.websocket.websocket.config;

import com.soulware.tcompro.iam.infrastructure.configuration.AuthChannelInterceptor;
import com.soulware.tcompro.iam.infrastructure.configuration.TokenHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final JwtDecoder jwtDecoder;
    private final TokenHandshakeInterceptor tokenInterceptor;

    public WebSocketConfig(JwtDecoder jwtDecoder,
                           TokenHandshakeInterceptor tokenInterceptor) {
        this.jwtDecoder = jwtDecoder;
        this.tokenInterceptor = tokenInterceptor;
    }

    /**
     * Configure the message broker STOMP.
     * "/app" is the prefix for the client messages to the backend
     * "/topic" is the prefix for the server messages to the client
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
        System.out.println(">>> SENDING");
    }

    /**
     * Intercept entry messages to validate with JWT
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new AuthChannelInterceptor(jwtDecoder));
    }

    /**
     * Registry the unique web socket endpoint with SockJS support.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/wss")
                .setAllowedOriginPatterns("*")
                .addInterceptors(tokenInterceptor)
                .withSockJS();
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .addInterceptors(tokenInterceptor);
    }
}
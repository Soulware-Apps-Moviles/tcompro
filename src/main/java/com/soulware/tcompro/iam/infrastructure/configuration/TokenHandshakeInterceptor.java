package com.soulware.tcompro.iam.infrastructure.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;


@Component
public class TokenHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private static final Logger log = LoggerFactory.getLogger(TokenHandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        //Extracts the token of the query string (?api_key=ABC)
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(request.getURI());
        String token = uriBuilder.build().getQueryParams().getFirst("api_key");

        if (token != null && !token.isEmpty()) {
            attributes.put("api_key_session", token);
            log.debug("JWT token attached to WebSocket session attributes");
        } else {
            log.warn("No 'api_key' found in WebSocket handshake request. Connection may be rejected later.");
        }

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
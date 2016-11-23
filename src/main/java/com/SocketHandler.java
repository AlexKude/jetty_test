package com;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Created by Main Server on 22.11.2016.
 */
public class SocketHandler extends WebSocketHandler {

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.register(CustomWebSocket.class);
    }
}

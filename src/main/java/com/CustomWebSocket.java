package com;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Main Server on 22.11.2016.
 */
@WebSocket
public class CustomWebSocket {

    @OnWebSocketConnect
    public void onConnect(Session session) {
        try {
            System.out.println("Client connected " + session.getRemoteAddress().toString());
            session.getRemote().sendString("You are connected now. Please go ahead.......");
        } catch (IOException e) {
            System.out.println("Failed to connect client " + session.getRemoteAddress().toString());
        }
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String msg) {
        try {
            String msg1 = msg;
            if (msg1.equals("stop")){
                session.close();
                return;
            }
            System.out.println("Received message from client - " + session.getRemoteAddress().toString() + ":  "  + msg);
            System.out.println("Enter your message");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            if (message.equals("stop")) {
                session.close();
            } else if (message.equals("stop server")) {
                System.exit(0);
            } else {
                session.getRemote().sendString(message);
            }

        } catch (IOException e) {
            System.out.println("Failed to send message to client  "+ session.getRemoteAddress().toString());

        }
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("disconnect:::cliecnt -> " + session.getRemoteAddress().toString());
        System.out.println("disconnect:::reason ->" + reason + " " + "status Code -> " + statusCode);
    }
}

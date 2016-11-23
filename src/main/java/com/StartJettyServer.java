package com;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Created by Main Server on 22.11.2016.
 */
public class StartJettyServer {
    public static void main(String[] args) {
        try {
            Server server = new Server();

            ServerConnector serverConnector = new ServerConnector(server);
            serverConnector.setHost("localhost");
            serverConnector.setPort(9998);
            server.addConnector(serverConnector);

            ServerConnector serverConnector1 = new ServerConnector(server);
            serverConnector1.setHost("localhost");
            serverConnector1.setPort(9999);
            server.addConnector(serverConnector1);

            ResourceHandler resourceHandler = new ResourceHandler();
            resourceHandler.setResourceBase("src\\main\\webapp");
            resourceHandler.setWelcomeFiles(new String[]{"index.html"});

            HandlerList handlerList = new HandlerList();
            handlerList.setHandlers(new Handler[]{new SocketHandler(), resourceHandler});
            server.setHandler(handlerList);

            server.start();

            server.join();

            } catch (Exception e) {
            System.out.println("Server fail to start. Please try again");
            System.exit(0);
            }
    }
}

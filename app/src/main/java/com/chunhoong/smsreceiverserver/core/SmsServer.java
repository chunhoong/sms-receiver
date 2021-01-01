package com.chunhoong.smsreceiverserver.core;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsServer {

    private static final Logger logger = LoggerFactory.getLogger(SmsServer.class);
    private static final Server jettyServer = new Server();

    public static void start() {
        jettyServer.addConnector(configureConnector());
        jettyServer.setHandler(configureContextHandler());

        try {
            jettyServer.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static ServerConnector configureConnector() {
        ServerConnector connector = new ServerConnector(jettyServer);
        connector.setPort(80);
        return connector;
    }

    private static ContextHandler configureContextHandler() {
        final ServletContextHandler context = new ServletContextHandler(
                jettyServer, "/rest");

        context.addServlet(new ServletHolder(new ServletContainer(new SmsServerApplication())), "/*");

        return context;
    }

}

package com.chunhoong.smsreceiver.core;

import com.chunhoong.smsreceiver.core.exceptions.ServerException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsReceiverServer {

    private static final Logger logger = LoggerFactory.getLogger(SmsReceiverServer.class);
    private static final Server jettyServer = new Server();

    public static void start() {
        start(80);
    }

    public static void start(int portNumber) {
        jettyServer.addConnector(configureConnector(portNumber));
        jettyServer.setHandler(configureContextHandler());

        try {
            jettyServer.start();
            logger.info("Sms receiver server is up and running on port: {}", portNumber);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    public static void stop() {
        try {
            jettyServer.stop();
            logger.info("Sms receiver server is stopped gracefully");
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    public static boolean isRunning() {
        return jettyServer.isRunning();
    }

    private static ServerConnector configureConnector(int portNumber) {
        ServerConnector connector = new ServerConnector(jettyServer);
        connector.setPort(portNumber);
        return connector;
    }

    private static ContextHandler configureContextHandler() {
        final ServletContextHandler context = new ServletContextHandler(jettyServer, "/rest");
        context.addServlet(new ServletHolder(new ServletContainer(new SmsReceiverRestApplication())), "/*");
        return context;
    }

}

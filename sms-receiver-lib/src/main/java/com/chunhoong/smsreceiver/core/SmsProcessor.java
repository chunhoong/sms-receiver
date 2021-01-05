package com.chunhoong.smsreceiver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.*;
import org.smslib.modem.SerialModemGateway;

import java.io.IOException;

public class SmsProcessor {

    private static final Logger logger = LoggerFactory.getLogger(SmsProcessor.class);
    private static Service service;


    public static void start(int comPortNumber) {
        SerialModemGateway gateway = new SerialModemGateway("", "COM" + comPortNumber, 9600, "", "");
        gateway.setInbound(true);
        gateway.setOutbound(true);

        service = Service.getInstance();
        service.setInboundMessageNotification(new SmsReceiverHandler());

        try {
            service.addGateway(gateway);
            service.startService();
            logger.info("Sms processor is started by listening to dongle via {}", comPortNumber);
        } catch (SMSLibException | IOException | InterruptedException e) {
            throw new SmsLibException(e);
        }
    }

    public static void stop() {
        try {
            service.stopService();
        } catch (SMSLibException | IOException | InterruptedException e) {
            throw new SmsLibException(e);
        }
    }

}

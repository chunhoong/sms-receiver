package com.chunhoong.smsreceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleApplication {

    private static final Logger logger = LoggerFactory.getLogger(SampleApplication.class);

    public static void main(String[] args) {
        SmsReceiver.addListener(sms -> logger.info("From listener: {}", sms));
        SmsReceiver.start();
    }
}

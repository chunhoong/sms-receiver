package com.chunhoong.smsreceiver.core;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class SmsReceiverRestApplication extends ResourceConfig {

    public SmsReceiverRestApplication() {
        register(new ApplicationBinder());
        packages("com.chunhoong.smsreceiver.rest");
    }

}

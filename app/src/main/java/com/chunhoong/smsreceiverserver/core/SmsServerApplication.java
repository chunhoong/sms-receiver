package com.chunhoong.smsreceiverserver.core;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class SmsServerApplication extends ResourceConfig {

    public SmsServerApplication() {
        register(new ApplicationBinder());
        packages("com.chunhoong.smsreceiverserver.rest");
    }

}

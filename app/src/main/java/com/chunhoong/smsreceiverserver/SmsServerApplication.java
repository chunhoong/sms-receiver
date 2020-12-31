package com.chunhoong.smsreceiverserver;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class SmsServerApplication extends ResourceConfig {

    public SmsServerApplication() {
        packages("com.chunhoong.smsreceiverserver");
    }

}

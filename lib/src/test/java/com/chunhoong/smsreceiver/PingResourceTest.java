package com.chunhoong.smsreceiver;


import com.chunhoong.smsreceiver.rest.PingResource;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingResourceTest extends JerseyTest {

    private static final Logger logger = LoggerFactory.getLogger(PingResourceTest.class);

    @Test
    public void testPing() {
        String response = target("ping").request().get(String.class);
        logger.info(response);
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(PingResource.class);
    }

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}

package com.chunhoong.smsreceiver;

import com.chunhoong.smsreceiver.subject.SmsReceivedEvent;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

public class SmsListenerTest {

    private static final Logger logger = LoggerFactory.getLogger(SmsListenerTest.class);

    @Test
    public void test() {
        final String SAMPLE_CONTENT = "<content>";
        final String SAMPLE_SENDER = "<sender>";

        AtomicReference<String> content = new AtomicReference<>();
        AtomicReference<String> sender = new AtomicReference<>();

        SmsReceiver.addListener(sms -> {
            logger.info("From listener: {}", sms);
            content.set(sms.getContent());
            sender.set(sms.getSender());
        });

        Sms state = new Sms();
        state.setContent(SAMPLE_CONTENT);
        state.setSender(SAMPLE_SENDER);

        SmsReceivedEvent.setState(state);

        Assert.assertEquals(SAMPLE_CONTENT, content.get());
        Assert.assertEquals(SAMPLE_SENDER, sender.get());
    }

}

package com.chunhoong.smsreceiver.core;

import com.chunhoong.smsreceiver.Sms;
import com.chunhoong.smsreceiver.subject.SmsReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.*;

import java.io.IOException;

public class SmsReceiverHandler implements IInboundMessageNotification {

    private static final Logger logger = LoggerFactory.getLogger(SmsReceiverHandler.class);

    @Override
    public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage inboundMessage) {
        logger.info(inboundMessage.toString());

        try {
            aGateway.deleteMessage(inboundMessage);
        } catch (TimeoutException | GatewayException | IOException | InterruptedException e) {
            logger.error(e.getMessage(), e);
            return;
        }

        Sms sms = new Sms();
        sms.setContent(inboundMessage.getText());
        sms.setSender(inboundMessage.getOriginator());

        SmsReceivedEvent.setState(sms);
    }

}

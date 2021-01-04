package com.chunhoong.smsreceiver;

import com.chunhoong.smsreceiver.repository.IncomingSmsEntity;
import com.chunhoong.smsreceiver.repository.IncomingSmsRepository;
import com.chunhoong.smsreceiver.service.IncomingSmsDto;
import com.chunhoong.smsreceiver.service.IncomingSmsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
public class SmsListenerTest {

    private static final Logger logger = LoggerFactory.getLogger(SmsListenerTest.class);

    @Mock
    private IncomingSmsRepository incomingSmsRepository;

    @InjectMocks
    private IncomingSmsService incomingSmsService;

    @Test
    public void test() {
        Mockito.doNothing().when(incomingSmsRepository).saveIncomingSms(Mockito.isA(IncomingSmsEntity.class));

        AtomicReference<String> content = new AtomicReference<>();
        AtomicReference<String> sender = new AtomicReference<>();

        SmsReceiver.addListener(sms -> {
            logger.info("From listener: {}", sms);
            content.set(sms.getContent());
            sender.set(sms.getSender());
        });

        IncomingSmsDto incomingSmsDto = new IncomingSmsDto();
        incomingSmsDto.setContent("<some-content>");
        incomingSmsDto.setSender("<from-a-sender>");

        incomingSmsService.saveIncomingSms(incomingSmsDto);

        Assertions.assertEquals("<some-content>", content.get());
        Assertions.assertEquals("<from-a-sender>", sender.get());
    }

}

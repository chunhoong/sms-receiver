package com.chunhoong.smsreceiver.service;

import com.chunhoong.smsreceiver.Sms;
import com.chunhoong.smsreceiver.repository.IncomingSmsEntity;
import com.chunhoong.smsreceiver.repository.IncomingSmsRepository;
import com.chunhoong.smsreceiver.subject.SmsReceivedEvent;
import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;

@Service
public class IncomingSmsService {

    @Inject
    private IncomingSmsRepository incomingSmsRepository;

    public void saveIncomingSms(IncomingSmsDto incomingSmsDto) {
        IncomingSmsEntity incomingSmsEntity = new IncomingSmsEntity();
        incomingSmsEntity.setContent(incomingSmsDto.getContent());
        incomingSmsEntity.setSender(incomingSmsDto.getSender());
        incomingSmsRepository.saveIncomingSms(incomingSmsEntity);

        Sms smsState = new Sms();
        smsState.setContent(incomingSmsDto.getContent());
        smsState.setSender(incomingSmsDto.getSender());
        SmsReceivedEvent.setState(smsState);
    }

}

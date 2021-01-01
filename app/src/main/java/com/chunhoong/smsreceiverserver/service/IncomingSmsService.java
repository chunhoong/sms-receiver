package com.chunhoong.smsreceiverserver.service;

import com.chunhoong.smsreceiverserver.repository.IncomingSmsEntity;
import com.chunhoong.smsreceiverserver.repository.IncomingSmsRepository;
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
    }

}

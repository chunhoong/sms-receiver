package com.chunhoong.smsreceiver.core;

import com.chunhoong.smsreceiver.repository.IncomingSmsRepository;
import com.chunhoong.smsreceiver.service.IncomingSmsService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindAsContract(IncomingSmsRepository.class);
        bindAsContract(IncomingSmsService.class);
    }

}

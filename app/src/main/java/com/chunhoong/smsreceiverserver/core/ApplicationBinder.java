package com.chunhoong.smsreceiverserver.core;

import com.chunhoong.smsreceiverserver.repository.IncomingSmsRepository;
import com.chunhoong.smsreceiverserver.service.IncomingSmsService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindAsContract(IncomingSmsRepository.class);
        bindAsContract(IncomingSmsService.class);
    }
}

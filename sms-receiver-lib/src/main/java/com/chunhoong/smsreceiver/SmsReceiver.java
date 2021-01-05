package com.chunhoong.smsreceiver;

import com.chunhoong.smsreceiver.core.SmsProcessor;
import com.chunhoong.smsreceiver.subject.SmsReceivedEvent;

import java.util.function.Consumer;

public class SmsReceiver {

    public static void start(int comPortNumber) {
        SmsProcessor.start(comPortNumber);
    }

    public static void stop() {
        SmsProcessor.stop();
    }

    public static void addListener(Consumer<Sms> smsListener) {
        SmsReceivedEvent.attach(smsListener);
    }

    public static void removeAllListeners() {
        SmsReceivedEvent.detachAllListener();
    }

}

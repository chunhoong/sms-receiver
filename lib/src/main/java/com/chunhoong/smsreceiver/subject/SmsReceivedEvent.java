package com.chunhoong.smsreceiver.subject;

import com.chunhoong.smsreceiver.Sms;
import com.chunhoong.smsreceiver.SmsListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SmsReceivedEvent {

    private static final List<Consumer<Sms>> smsListeners = new ArrayList<>();
    private static Sms state;

    public static Sms getState() {
        return state;
    }

    public static void setState(Sms sms) {
        state = sms;
        smsListeners.forEach(smsListener -> smsListener.accept(sms));
    }

    public static void attach(Consumer<Sms> smsListener) {
        smsListeners.add(smsListener);
    }

    public static void detachAllListener() {
        smsListeners.clear();
    }

}

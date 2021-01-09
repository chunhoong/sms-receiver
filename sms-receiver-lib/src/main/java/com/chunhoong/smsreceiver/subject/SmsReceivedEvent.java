package com.chunhoong.smsreceiver.subject;

import com.chunhoong.smsreceiver.Sms;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SmsReceivedEvent {

    private static final List<Consumer<Sms>> listeners = new ArrayList<>();
    private static final List<Consumer<Sms>> oneTimeListeners = new ArrayList<>();
    private static Sms state;

    public static Sms getState() {
        return state;
    }

    public static void setState(Sms sms) {
        state = sms;
        listeners.forEach(listener -> listener.accept(sms));
        oneTimeListeners.forEach(consumer -> consumer.accept(sms));
        oneTimeListeners.clear();
    }

    public static int addListener(Consumer<Sms> smsListener) {
        listeners.add(smsListener);
        return listeners.size() - 1;
    }

    public static void addOneTimeListener(Consumer<Sms> consumer) {
        oneTimeListeners.add(consumer);
    }

    public static void removeListener(int listenerId) {
        if (listenerId < listeners.size()) {
            listeners.set(listenerId, null);
        }
    }

    public static void removeAllListener() {
        listeners.clear();
    }

}

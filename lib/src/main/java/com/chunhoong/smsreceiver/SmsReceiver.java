package com.chunhoong.smsreceiver;

import com.chunhoong.smsreceiver.core.SmsReceiverServer;
import com.chunhoong.smsreceiver.subject.SmsReceivedEvent;

import java.util.function.Consumer;

public class SmsReceiver {

    public static void start() {
        SmsReceiverServer.start();
    }

    public static void start(int portNumber) {
        SmsReceiverServer.start(portNumber);
    }

    public static void stop() {
        SmsReceiverServer.stop();
    }

    public static void addListener(Consumer<Sms> smsListener) {
        SmsReceivedEvent.attach(smsListener);
    }

    public static void removeAllListeners() {
        SmsReceivedEvent.detachAllListener();
    }

    public static boolean isRunning() {
        return SmsReceiverServer.isRunning();
    }

}

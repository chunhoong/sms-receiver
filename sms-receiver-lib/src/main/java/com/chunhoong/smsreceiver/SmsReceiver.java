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

    /**
     * Add listener to handle all incoming sms until the listeners are removed.
     * To handle incoming sms for exactly only once, use {@link #once(Consumer)}
     *
     * <p>The listeners can be removed by invoking {@link #removeAllListeners()}
     *
     * @param listener listener to handle incoming sms
     * @return listenerId
     */
    public static int addListener(Consumer<Sms> listener) {
        return SmsReceivedEvent.addListener(listener);
    }

    /**
     * Add one time listener to handle incoming sms for exactly once.
     *
     * <p>
     * For long term listener, use {@link #addListener(Consumer)}
     *
     * <p>
     * Invocation of {@link #removeAllListeners()} is not required for this listener, as it will be removed
     * automatically after consumed.
     *
     * @param listener listener to handle incoming sms
     */
    public static void once(Consumer<Sms> listener) {
        SmsReceivedEvent.addOneTimeListener(listener);
    }

    public static void removeListener(int listenerId) {
        SmsReceivedEvent.removeListener(listenerId);
    }

    /**
     * Remove all the listener added via {@link #addListener(Consumer)}
     */
    public static void removeAllListeners() {
        SmsReceivedEvent.removeAllListener();
    }

}

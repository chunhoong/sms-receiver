package com.chunhoong.smsreceiver;

import com.chunhoong.smsreceiver.subject.SmsReceivedEvent;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class SmsReceiverTest {

    private static final Logger logger = LoggerFactory.getLogger(SmsReceiverTest.class);
    private static final String FIRST_CONTENT = "<first-content>";
    private static final String FIRST_SENDER = "<first-sender>";
    private static final String SECOND_CONTENT = "<second-content>";
    private static final String SECOND_SENDER = "<second-sender>";

    /**
     * Verify if listeners are triggered on state change.
     */
    @Test
    public void testListener() {
        List<Consumer<Sms>> consumers = Whitebox.getInternalState(SmsReceivedEvent.class, "listeners");

        final AtomicReference<String> currentContent = new AtomicReference<>();
        final AtomicReference<String> currentSender = new AtomicReference<>();

        SmsReceiver.addListener(sms -> {
            logger.info("From listener: {}", sms);
            Assert.assertEquals(currentContent.get(), sms.getContent());
            Assert.assertEquals(currentSender.get(), sms.getSender());
        });

        Assert.assertEquals(1, consumers.size());

        currentContent.set(FIRST_CONTENT);
        currentSender.set(FIRST_SENDER);

        updateState(FIRST_CONTENT, FIRST_SENDER);

        currentContent.set(SECOND_CONTENT);
        currentSender.set(SECOND_SENDER);

        updateState(SECOND_CONTENT, SECOND_SENDER);
    }

    /**
     * Verify if one time listeners are only triggered exactly once on state change
     */
    @Test
    public void testOneTimeListener() {
        List<Consumer<Sms>> oneTimeListeners = Whitebox.getInternalState(SmsReceivedEvent.class, "oneTimeListeners");

        SmsReceiver.once(sms -> {
            logger.info("From consumer: {}", sms);
            Assert.assertEquals(FIRST_CONTENT, sms.getContent());
            Assert.assertEquals(FIRST_SENDER, sms.getSender());
        });

        Assert.assertEquals(1, oneTimeListeners.size());

        updateState(FIRST_CONTENT, FIRST_SENDER);

        Assert.assertTrue(oneTimeListeners.isEmpty());
    }

    /**
     * Verify remove listener.
     */
    @Test
    public void testRemoveListener() {
        List<Consumer<Sms>> consumers = Whitebox.getInternalState(SmsReceivedEvent.class, "listeners");

        int listenerIdOne = SmsReceiver.addListener(sms -> { /* Do something */ });
        int listenerIdTwo = SmsReceiver.addListener(sms -> { /* Do something */ });
        int listenerIdThree = SmsReceiver.addListener(sms -> { /* Do something */ });

        SmsReceiver.removeListener(listenerIdThree);

        Assert.assertEquals(3, consumers.size());

        Assert.assertNotNull(consumers.get(listenerIdOne));
        Assert.assertNotNull(consumers.get(listenerIdTwo));
        Assert.assertNull(consumers.get(listenerIdThree));
    }

    /**
     * Verify remove all listeners.
     */
    @Test
    public void testRemoveAllListeners() {
        List<Consumer<Sms>> consumers = Whitebox.getInternalState(SmsReceivedEvent.class, "listeners");

        SmsReceiver.addListener(sms -> { /* Do something */ });
        SmsReceiver.addListener(sms -> { /* Do something */ });
        SmsReceiver.addListener(sms -> { /* Do something */ });

        SmsReceiver.removeAllListeners();

        Assert.assertEquals(0, consumers.size());
    }

    private void updateState(String content, String sender) {
        Sms state = new Sms();

        state.setContent(content);
        state.setSender(sender);

        SmsReceivedEvent.setState(state);
    }

}

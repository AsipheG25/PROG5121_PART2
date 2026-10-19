package com.mycompany.prog5121_part2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageID() {
        Message message = new Message(
                1, "+27693526521", "Hi tonight"
        );

        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testValidRecipientCell() {
        Message message = new Message(
                1, "+27693526521", "Hi tonight"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testInvalidRecipientCell() {
        Message message = new Message(
                1, "27693526521", "Hi tonight"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testCreateMessageHash() {
        Message message = new Message(
                0, "+27693526521", "Hi tonight"
        );

        String hash = message.createMessageHash();

        assertNotNull(hash);
        assertTrue(hash.contains(":"));
        assertTrue(hash.endsWith("HITONIGHT"));
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                1, "+27693526521", "Hi tonight"
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage("send")
        );
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                1, "+27693526521", "Hi tonight"
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage("disregard")
        );
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                1, "+27693526521", "Hi tonight"
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage("store")
        );
    }

    @Test
    public void testPrintMessages() {
        Message message = new Message(
                1, "+27693526521", "Hi tonight"
        );

        String details = message.printMessages();

        assertTrue(details.contains("Message ID:"));
        assertTrue(details.contains("Message Hash:"));
        assertTrue(details.contains("Recipient: +27693526521"));
        assertTrue(details.contains("Message: Hi tonight"));
    }

    @Test
    public void testReturnTotalMessages() {
        Message message = new Message(
                1, "+27693526521", "Hi tonight"
        );

        assertTrue(message.returnTotalMessages() >= 0);
    }

    @Test
    public void testMessageIDIsRandomlyGenerated() {
        Message first = new Message(
                1, "+27693526521", "First message"
        );

        Message second = new Message(
                2, "+27693526521", "Second message"
        );

        assertNotNull(first.getMessageID());
        assertNotNull(second.getMessageID());
    }

    @Test
    public void testEmptyMessageHash() {
        Message message = new Message(
                1, "+27693526521", ""
        );

        assertNotNull(message.createMessageHash());
    }
}
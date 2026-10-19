package com.mycompany.prog5121_part2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    private static int totalMessages = 0;

    // Constructor
    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generate a random 10-digit message ID.
    private String generateMessageID() {
        Random random = new Random();

        long id = 1_000_000_000L
                + (long) (random.nextDouble() * 9_000_000_000L);

        return String.valueOf(id);
    }

    // Check that the message ID contains no more than 10 characters.
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Validate the recipient's cellphone number.
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+\\d{10,15}$")) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Create the message hash without using arrays.
    public String createMessageHash() {
        String firstWord = "";
        String lastWord = "";

        if (message != null && !message.trim().isEmpty()) {
            String trimmedMessage = message.trim();

            int firstSpace = trimmedMessage.indexOf(' ');
            int lastSpace = trimmedMessage.lastIndexOf(' ');

            if (firstSpace == -1) {
                firstWord = trimmedMessage;
                lastWord = trimmedMessage;
            } else {
                firstWord = trimmedMessage.substring(0, firstSpace);
                lastWord = trimmedMessage.substring(lastSpace + 1);
            }
        }

        String firstTwoIDNumbers = messageID.substring(0, 2);

        return firstTwoIDNumbers + ":"
                + messageNumber + ":"
                + firstWord.toUpperCase().replaceAll("[^A-Z0-9]", "")
                + lastWord.toUpperCase().replaceAll("[^A-Z0-9]", "");
    }

    // Process the user's decision about the message.
    public String SentMessage(String choice) {
        if (choice == null) {
            return "Invalid option.";
        }

        switch (choice.toLowerCase()) {
            case "send":
                totalMessages++;
                return "Message successfully sent.";

            case "disregard":
                return "Press 0 to delete the message.";

            case "store":
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    // Display the message details.
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Return the total number of messages sent.
    public int returnTotalMessages() {
        return totalMessages;
    }

    // Store the message as JSON in the Part 2 project folder.
    public void storeMessage() {
        File file = new File(
                "C:\\Users\\asiph\\OneDrive\\Documentos\\NetBeansProjects\\PROG5121_PART2\\messages.json"
        );

        try (FileWriter writer = new FileWriter(file, true)) {
            String json = "{"
                    + "\"messageID\":\"" + escapeJSON(messageID) + "\","
                    + "\"messageNumber\":" + messageNumber + ","
                    + "\"recipient\":\"" + escapeJSON(recipient) + "\","
                    + "\"message\":\"" + escapeJSON(message) + "\","
                    + "\"messageHash\":\"" + escapeJSON(messageHash) + "\""
                    + "}";

            writer.write(json + System.lineSeparator());

            System.out.println("Message successfully stored.");
            System.out.println("Saved to: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Escape special characters for JSON strings.
    private String escapeJSON(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }
}
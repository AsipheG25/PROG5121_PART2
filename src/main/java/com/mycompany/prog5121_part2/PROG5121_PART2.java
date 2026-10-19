package com.mycompany.prog5121_part2;

import java.util.Scanner;

public class PROG5121_PART2 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("       WELCOME TO QUICKCHAT       ");
        System.out.println("==================================");

        System.out.println("\n========== USER REGISTRATION ==========");

        System.out.print("Enter your first name: ");
         String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        String username;
        String password;
        String cellPhoneNumber;
        boolean validUsername;
        boolean validPassword;
        boolean validCellPhoneNumber;

// Username validation
        do {
        System.out.print("Enter your username: ");
        username = input.nextLine();

        Login user = new Login(username, "", "", firstName, lastName);
         validUsername = user.checkUserName();

         if (validUsername) {
         System.out.println("Username successfully captured.");
          } else {
         System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
      }

       } while (!validUsername);

// Password validation
         do {
         System.out.print("Enter your password: ");
          password = input.nextLine();

         Login user = new Login(username, password, "", firstName, lastName);
         validPassword = user.checkPasswordComplexity();

         if (validPassword) {
    System.out.println("Password successfully captured.");
} else {
    System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
}


} while (!validPassword);

// Cellphone validation
do {
System.out.print("Enter your cell phone number: ");
cellPhoneNumber = input.nextLine();

Login user = new Login(username, password, cellPhoneNumber, firstName, lastName);
validCellPhoneNumber = user.checkCellPhoneNumber();

if (validCellPhoneNumber) {
    System.out.println("Cell phone number successfully added.");
} else {
    System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
}

} while (!validCellPhoneNumber);

Login user = new Login(
username, password, cellPhoneNumber, firstName, lastName
);

System.out.println(user.registerUser());

// Login
System.out.println("\n========== LOGIN ==========");

System.out.print("Enter your username to login: ");
String loginUsername = input.nextLine();

System.out.print("Enter your password to login: ");
String loginPassword = input.nextLine();

boolean loggedIn = user.loginUser(loginUsername, loginPassword);

System.out.println(user.returnLoginStatus(loggedIn));

if (!loggedIn) {
System.out.println("Please log in before using QuickChat.");
input.close();
return;
}


        System.out.println("\nWelcome to QuickChat!");

        System.out.print("How many messages would you like to enter? ");

        int numberOfMessages;

        try {
            numberOfMessages = Integer.parseInt(input.nextLine());

            if (numberOfMessages < 0) {
                System.out.println("Please enter a valid number of messages.");
                input.close();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            input.close();
            return;
        }

        int messagesProcessed = 0;

        while (messagesProcessed < numberOfMessages) {

            System.out.println("\n----------------------------------");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("----------------------------------");

            System.out.print("Choose an option: ");
            String choice = input.nextLine();

            switch (choice) {

                case "1": {
                    System.out.print("Enter recipient cellphone number: ");
                    String recipient = input.nextLine();

                    Message temporaryMessage =
                            new Message(messagesProcessed + 1, recipient, "");

                    String recipientValidation =
                            temporaryMessage.checkRecipientCell();

                    if (!recipientValidation.startsWith(
                            "Cell phone number successfully captured.")) {
                        System.out.println(recipientValidation);
                        break;
                    }

                    String messageText;

                    while (true) {
                        System.out.print(
                                "Enter your message (maximum 250 characters): ");
                        messageText = input.nextLine();

                        if (messageText.length() > 250) {
                            int excessCharacters = messageText.length() - 250;

                            System.out.println(
                                    "Message exceeds 250 characters by "
                                    + excessCharacters
                                    + " characters; please reduce the size."
                            );
                        } else {
                            System.out.println("Message ready to send.");
                            break;
                        }
                    }

                    Message message = new Message(
                            messagesProcessed + 1,
                            recipient,
                            messageText
                    );

                    System.out.println(
                            "\nChoose what to do with your message:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");

                    System.out.print("Enter your choice: ");
                    String messageChoice = input.nextLine();

                    String actionResult;

                    switch (messageChoice) {
                        case "1":
                            actionResult = message.SentMessage("send");
                            break;

                        case "2":
                            actionResult = message.SentMessage("disregard");
                            break;

                        case "3":
                            message.storeMessage();
                            actionResult = "";
                            break;

                        default:
                            actionResult = "Invalid option.";
                    }

                    if (!actionResult.isEmpty()) {
                        System.out.println(actionResult);
                    }

                    if (messageChoice.equals("1")) {
                        System.out.println("\n" + message.printMessages());
                    }

                    messagesProcessed++;
                    break;
                }

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    System.out.println("\nThank you for using QuickChat!");
                    System.out.println("Messages sent: "
                            + new Message(
                                    0, "+27123456789", "Total"
                            ).returnTotalMessages());
                    input.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("\n==================================");
        System.out.println("Messages sent: "
                + new Message(
                        0, "+27123456789", "Total"
                ).returnTotalMessages());
        System.out.println("Thank you for using QuickChat!");
        System.out.println("==================================");

        input.close();
    }
}
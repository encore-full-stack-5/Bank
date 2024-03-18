package common.util;

import java.util.Scanner;

public class ConsoleUtility {
    private static final Scanner scanner = new Scanner(System.in); // Singleton Scanner

    // Private constructor to prevent instantiation
    private ConsoleUtility() {}

    // Static method to prompt user input
    public static String promptForInput(String message) {
        System.out.println(message);
        return scanner.next();
    }

    // Static method to prompt user input
    public static int promptForInt(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    public static void systemMessage(String message) {
        System.out.println();
        System.out.println("========================================================================================================================");
        System.out.println(message);
        System.out.println("========================================================================================================================");
        System.out.println();
    }

    // Static method to repeatedly prompt for a choice until a valid one is made
    public static int promptForChoice(String message, int start, int end) throws Exception {
        int choice;
        do {
            choice = promptForInt(message); // Reuse promptForInt for integer input
            if (choice < start || choice > end) {
                System.out.println("선택지가 아닙니다. 다시 선택해주세요");
            }
            if(choice == 0) throw new Exception("");
        } while (choice < start || choice > end);
        return choice;
    }
}
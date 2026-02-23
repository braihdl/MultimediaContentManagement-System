package view;

import model.Content;
import model.MediaType;
import model.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    public void printTitle(String title) {
        System.out.println("\n==============================");
        System.out.println(title);
        System.out.println("==============================");
    }

    public String prompt(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(">> " + message);
    }

    public int mainMenu() {
        System.out.println("\n1) Upload Content");
        System.out.println("2) View All Content");
        System.out.println("3) Delete Content");
        System.out.println("4) View Notifications");
        System.out.println("5) Reports");
        System.out.println("6) Backup (Admin)");
        System.out.println("0) Exit");
        System.out.print("Choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void showContentList(List<Content> list) {
        if (list.isEmpty()) {
            System.out.println("No content available.");
            return;
        }

        for (Content c : list) {
            System.out.println(c);
        }
    }

    public void showReport(int total, long size, Map<MediaType, Integer> byType) {
        System.out.println("Total files: " + total);
        System.out.println("Total size (bytes): " + size);

        for (Map.Entry<MediaType, Integer> entry : byType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
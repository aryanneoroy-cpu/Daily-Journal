import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "my_journal.txt";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean running = true;

        System.out.println("This is your personal journal");

        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Write a new entry");
            System.out.println("2. Read past entries");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    writeEntry(in);
                    break;
                case "2":
                    readJournal();
                    break;
                case "3":
                    System.out.println("Goodbye! Have a great day.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }
        in.close();
    }

    public static void writeEntry(Scanner scanner) {
        System.out.print("\nType your journal entry here:\n> ");
        String entry = scanner.nextLine();

        LocalDate date = LocalDate.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("[" + date + "]");
            writer.newLine();
            writer.write(entry);
            writer.newLine();
            writer.write("---------------------------------------------");
            writer.newLine();

            System.out.println("Entry saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving entry: " + e.getMessage());
        }
    }


    public static void readJournal() {
        System.out.println("\n*** Your Past Journal Entries ***");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasContent = false;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                hasContent = true;
            }

            if (!hasContent) {
                System.out.println("Your journal is empty.");
            }
        } catch (IOException e) {
            System.out.println("No journal file found yet. Write a new entry first.");
        }
    }
}

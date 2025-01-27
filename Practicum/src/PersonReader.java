import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the Person Data File");

        if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting program.");
            return;
        }

        File file = fileChooser.getSelectedFile();

        if (!file.exists() || !file.isFile()) {
            System.out.println("Invalid file selected. Exiting program.");
            return;
        }

        ArrayList<Person> personList = new ArrayList<>(); // Store Person objects
        System.out.printf("%-10s %-15s %-15s %-10s %-6s\n", "ID", "First Name", "Last Name", "Title", "YOB");
        System.out.println("------------------------------------------------------------");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length == 5) {
                    try {
                        String id = data[0].trim();
                        String firstName = data[1].trim();
                        String lastName = data[2].trim();
                        String title = data[3].trim();
                        int yob = Integer.parseInt(data[4].trim());

                        Person person = new Person(id, firstName, lastName, title, yob);
                        personList.add(person);


                        System.out.println(person.toString());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid YOB format for record: " + line);
                    }
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        if (SafeInput.getYNConfirm(new Scanner(System.in), "Would you like to process another file?")) {
            main(args);
        } else {
            System.out.println("Exiting program.");
        }
    }
}


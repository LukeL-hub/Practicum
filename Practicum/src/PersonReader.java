import javax.swing.*;
import java.io.*;
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


        System.out.printf("%-10s %-15s %-15s %-10s %-6s\n", "ID", "First Name", "Last Name", "Title", "YOB");
        System.out.println("------------------------------------------------------------");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");


                if (data.length == 5) {
                    System.out.printf("%-10s %-15s %-15s %-10s %-6s\n",
                            data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim());
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }


        if (!SafeInput.getYNConfirm(new Scanner(System.in), "Would you like to process another file?")) {
            System.out.println("Exiting program.");
        }
    }
}

import javax.swing.*;
import java.io.*;
import java.util.*;

public class ProductReader {
    public static void main(String[] args) {
        // Use JFileChooser to select the product data file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        // If a file is selected
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                List<String[]> products = new ArrayList<>();

                // Read each line from the file
                while ((line = reader.readLine()) != null) {
                    // Split the line into parts by comma
                    String[] productData = line.split(",");
                    products.add(productData);
                }

                // Print the product data in a formatted way
                System.out.printf("%-10s %-20s %-30s %-10s\n", "ID", "Name", "Description", "Cost");
                System.out.println("---------------------------------------------------------------");

                // Loop through the product list and print each entry
                for (String[] product : products) {
                    String id = product[0];
                    String name = product[1];
                    String description = product[2];
                    String cost = product[3];

                    System.out.printf("%-10s %-20s %-30s $%-10s\n", id, name, description, cost);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}

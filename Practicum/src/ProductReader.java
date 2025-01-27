import javax.swing.*;
import java.io.*;
import java.util.*;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                List<Product> products = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    String[] productData = line.split(",");
                    if (productData.length == 4) {
                        try {
                            String id = productData[0].trim();
                            String name = productData[1].trim();
                            String description = productData[2].trim();
                            double cost = Double.parseDouble(productData[3].trim());

                            Product product = new Product(id, name, description, cost);
                            products.add(product);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid cost format in line: " + line);
                        }
                    } else {
                        System.out.println("Invalid record format: " + line);
                    }
                }

                System.out.printf("%-10s %-20s %-30s %-10s\n", "ID", "Name", "Description", "Cost");
                System.out.println("---------------------------------------------------------------");

                for (Product product : products) {
                    System.out.printf("%-10s %-20s %-30s $%-10.2f\n",
                            product.getID(),
                            product.getName(),
                            product.getDescription(),
                            product.getCost());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;

public class ProductGenerator {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\productData.txt");

        boolean done = false;

        do {
            String ID = SafeInput.getNonZeroLenString(in, "Enter the product ID (6 digits):");
            String name = SafeInput.getNonZeroLenString(in, "Enter the product name:");
            String description = SafeInput.getNonZeroLenString(in, "Enter the product description:");
            double cost = SafeInput.getDouble(in, "Enter the product cost (e.g., 19.99):");

            Product product = new Product(ID, name, description, cost);
            products.add(product);

            done = SafeInput.getYNConfirm(in, "Are you done adding products?");
        } while (!done);

        for (Product p : products) {
            System.out.println(p.toCSV());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
            for (Product product : products) {
                writer.write(product.toCSV()); // Using Product's toCSV method
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Product {
    private String ID;
    private String name;
    private String description;
    private double cost;

    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    // Getters
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        if (cost >= 0) {
            this.cost = cost;
        } else {
            throw new IllegalArgumentException("Cost must be non-negative.");
        }
    }

    // Methods
    public String toCSV() {
        return ID + "," + name + "," + description + "," + cost;
    }

    public String toJSON() {
        return String.format("{\"ID\":\"%s\", \"name\":\"%s\", \"description\":\"%s\", \"cost\":%.2f}",
                ID, name, description, cost);
    }

    public String toXML() {
        return String.format("<Product><ID>%s</ID><Name>%s</Name><Description>%s</Description><Cost>%.2f</Cost></Product>",
                ID, name, description, cost);
    }

    @Override
    public String toString() {
        return name + " (" + ID + "): " + description + " - $" + cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.cost, cost) == 0 &&
                ID.equals(product.ID) &&
                name.equals(product.name) &&
                description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, description, cost);
    }
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductGenerator {
    public static void main(String[] args)
    {
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\product" +
                "Data.txt");

        boolean done = false;

        do {

            String ID = SafeInput.getNonZeroLenString(in, "Enter the product ID (6 digits): ");
            String name = SafeInput.getNonZeroLenString(in, "Enter the product name: ");
            String description = SafeInput.getNonZeroLenString(in, "Enter the product description: ");
            double cost = SafeInput.getDouble(in, "Enter the product cost (e.g., 19.99): ");


            String productRecord = ID + "," + name + "," + description + "," + cost;
            products.add(productRecord);


            done = SafeInput.getYNConfirm(in, "Are you done adding products?");
        } while (!done);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile())))
        {

            // Finally can write the file LOL!

            for(String rec : products )
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

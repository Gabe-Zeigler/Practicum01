import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

public class ProductWriter
{
    public static void main(String[] args)
    {
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        /* ID
            Name
            Description
            Cost
         */
        String productRec;
        String ID;
        String name;
        String description;
        double cost;
        boolean done;

        File workingDirectory = new File (System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(),"\\src\\productData.txt");

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]");
            name = SafeInput.getNonZeroLenString(in, "Enter the Product Name");
            description = SafeInput.getNonZeroLenString(in, "Enter the Product Description");
            cost = SafeInput.getRangedDouble(in, "Enter the cost", 0, 100000);

            productRec = ID + "," + name + "," + description + "," + cost;
            products.add(productRec);

            System.out.println("\nRecord added: " + productRec);

            done = SafeInput.getYNConfirm(in, "Are you done entering products?");


        }while (!done);

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE, WRITE, TRUNCATE_EXISTING));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : products)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();

            System.out.println("\nData file written: " + file.toAbsolutePath());
            System.out.println(products.size() + " record saved.");
        }
        catch (IOException e) {
            System.out.println("Something went wrong while writing records!");
            e.printStackTrace();
        }
    }
}
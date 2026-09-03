import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class ProductReader
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean anotherFile;

        /*

        Here is the data file we are reading:
        000001, Pipeweed, Long Bottom Leaf, 600.0
        000002, Lembas, Elven Wayfare Bread, 200.0
        000003, Wine, Woodland Elf Wine, 400.0
        000004, Mushrooms, Farmer Took’s Finest, 125.0
        000005, Mithril, Enchanted Dwarven Armor, 3000.0


        */

        final int FIELDS_LENGTH = 4;

        do
        {
            JFileChooser chooser = new JFileChooser();
            File selectedFile;
            String rec;
            ArrayList<String> lines = new ArrayList<>();

            String id, name, description;
            double cost = 0;

            try
            {
                // use the toolkit to get the current working directory of the IDE
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();

                    // Typical java pattern of inherited classes: wrap a
                    // BufferedReader around a lower level BufferedInputStream.
                    // No open-options are needed here since we are only reading
                    // a file that the user already selected and that already exists.
                    InputStream in2 =
                            new BufferedInputStream(Files.newInputStream(file));
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(in2));

                    int line = 0;
                    while (reader.ready())
                    {
                        rec = reader.readLine();
                        lines.add(rec);
                        line++;
                        System.out.printf("\nLine %4d %-60s ", line, rec);
                    }
                    reader.close();
                    System.out.println("\n\nData file read!");

                    // Print a header for the formatted display
                    System.out.println();
                    System.out.printf("%-10s %-15s %-30s %-10s%n", "ID#", "Name", "Description", "Cost");
                    System.out.println("==================================================================");

                    // Split each line into fields by comma and trim whitespace.
                    // yob is the only numeric field; everything else stays a String.
                    String[] fields;
                    for (String l : lines)
                    {
                        fields = l.split(",");

                        if (fields.length == FIELDS_LENGTH)
                        {
                            id        = fields[0].trim();
                            name = fields[1].trim();
                            description  = fields[2].trim();
                            cost = Double.parseDouble(fields[3].trim());
                            System.out.printf("%-10s %-15s %-30s $%-9.2f%n", id, name, description, cost);
                        }
                        else
                        {
                            System.out.println("Found a record that may be corrupt: ");
                            System.out.println(l);
                        }
                    }
                }
                else // user closed the file dialog without choosing
                {
                    System.out.println("Failed to choose a file to process");
                }
            }
            catch (IOException e)
            {
                System.out.println("File could not be read!!!");
                e.printStackTrace();
            }

            anotherFile = SafeInput.getYNConfirm(in, "\nWould you like to open another file?");

        } while (anotherFile);

        System.out.println("Goodbye!");
    }
}
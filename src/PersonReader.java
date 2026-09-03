import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonReader
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean anotherFile;

        /*

        Here is the data file we are reading:
        000001, Bilbo, Baggins, Esq., 1060
        000002, Frodo, Baggins, Esq., 1120
        000003, Samwise, Gamgee, Esq., 1125
        000004, Peregrin, Took, Esq., 1126
        000005, Meridoc, Brandybuck, Esq., 1126

        */

        final int FIELDS_LENGTH = 5;

        do
        {
            JFileChooser chooser = new JFileChooser();
            File selectedFile;
            String rec;
            ArrayList<String> lines = new ArrayList<>();

            String id, firstName, lastName, title;
            int yob;

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
                    System.out.printf("%-10s %-15s %-15s %-8s %-6s%n", "ID#", "Firstname", "Lastname", "Title", "YOB");
                    System.out.println("========================================================");

                    // Split each line into fields by comma and trim whitespace.
                    // yob is the only numeric field; everything else stays a String.
                    String[] fields;
                    for (String l : lines)
                    {
                        fields = l.split(",");

                        if (fields.length == FIELDS_LENGTH)
                        {
                            id        = fields[0].trim();
                            firstName = fields[1].trim();
                            lastName  = fields[2].trim();
                            title     = fields[3].trim();
                            yob       = Integer.parseInt(fields[4].trim());
                            System.out.printf("%-10s %-15s %-15s %-8s %-6d%n", id, firstName, lastName, title, yob);
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
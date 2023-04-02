/*
 * Lleyton Damon: 1585670
 * Jake Postlewaight: 1590698
 */

import java.io.File;
import java.io.*;

public class MergeRuns {

    /**
     * Specifies how many files are used, converts unsorted file into
     * multiple smaller merged files specified in args.
     * @param args This specifies the heap size, cannot be higher than 10 and defaults to 2
     */
    public static void main(String[] args) {
        try {
            // Timer to check how long the program takes to run
            long start = System.currentTimeMillis();
            int runsCount = 2;
            try {
                if (args.length > 0) {
                    runsCount = Integer.parseInt(args[0]) > 10 ? 10 : (Integer.parseInt(args[0]) < 1 ? 2 : Integer.parseInt(args[0]));// Saves the specified
                                                                                               // heapsize into heapSize int
                } else {
                    System.err.println("Error: Unspecified heap size"); // Specifies error and exits program
                    runsCount = 2;
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid integer " + args[0]); // If integer is incorrectly formated exits program
                runsCount = 2;
            }
            
            // Declare variables
            DistributeRuns DR = new DistributeRuns(runsCount);
            File outputFile = DR.distribute();
            BufferedReader reader = new BufferedReader(new FileReader(outputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // Close the reader and delete th extra file
            reader.close();
            outputFile.delete();
            // Print the time taken to run
            long finish = System.currentTimeMillis();
            System.err.println("Elapsed Time: " + ((finish - start)/1000) + " Seconds");
        } catch (Exception ex){
            System.err.println(ex);
        }
        return;
    }
}

/*
 * Lleyton Damon: 1585670
 * Jake Postlewaight: 1590698
 */

import java.io.File;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class DistributeRuns {
    // Declare variables
    public File originalFolder;
    public File targetFolder;
    public String targetFolderPath;
    public int input;
    public boolean isEmpty;
    List<File> files = new ArrayList<>();
    File currentFile = null;
    int kFiles = 2;

    /**
     * Specifies how many files are used when merging, 
     * will default to 2 if input is less than 1 otherwise
     * will use the specified value
     * @param count The amount of files used to merge
     */
    public DistributeRuns(int count) {
        System.err.println("Started Distribute Runs");
        kFiles = count > 1 ? count : 2;
    }

    /**
     * Distribute the stream input into files
     * @return the final merged file
     */
    public File distribute() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        // BufferedWriter writer = new BufferedWriter(new
        // OutputStreamWriter(System.out));
        String line = "";

        int fileCount = 0;
        try {
            // Read line until the end of file
            while ((line = br.readLine()) != null) {
                // System.err.println(line);
                if ((fileCount == 0)) {
                    System.err.println("Creating new File for line:" + line);
                    currentFile = createTemp();
                    files.add(currentFile);
                    fileCount++;
                }
                if (line.compareTo("||") == 0) {
                    // System.err.println("Got Split");
                    fileCount = 0;
                    
                    if (files.size() == kFiles) { // If there are "k" files merge
                        File mergedFile = mergeFiles(files);
                        if (mergedFile == null) {
                            System.err.println("Merge files was null");
                            System.exit(-1);
                        }
                        files = new ArrayList<>();
                        files.add(mergedFile);
                        currentFile = mergedFile;
                    }
                    continue;
                } else {
                    if (!(line.isBlank())) {
                        writeLineToFile(line, currentFile);
                    }
                }
            }
            // If multiple files merge
            if (files.size() > 1) {
                File mergedFile = mergeFiles(files);
                if (mergedFile == null) {
                    System.err.println("Merge files was null");
                    System.exit(-1);
                }
                files = new ArrayList<>();
                files.add(mergedFile);
                return mergedFile;
            } else {
                return files.get(0);
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * 
     * Writes the contents of a MyMinHeap object to a specified file.
     * 
     * @param file the File object representing the file to write to
     * @param heap the MyMinHeap object containing the data to be written to the
     *             file
     */
    public void writeHeapToFile(File file, MyMinHeap heap) {
        // Initialize a string variable to store the removed elements from the heap
        String removed = "";

        // Loop through the heap and remove each element, writing it to the file
        while ((removed = heap.remove()) != null) {
            // Write the removed element to the file using the writeLineToFile() method
            writeLineToFile(removed, file);
        }
    }

    /**
     * 
     * Writes a single line of text to a specified file.
     * @param line the String representing the line of text to be written to the
     *             file
     * @param file the File object representing the file to write to
     */
    public static void writeLineToFile(String line, File file) {
        try {
            // Create a FileWriter object to write to the specified file
            FileWriter myWriter = new FileWriter(file, true);
            // Write the line of text to the file, followed by a line separator
            myWriter.write(line);
            myWriter.write(System.getProperty("line.separator"));

            // Close the FileWriter object to save the changes to the file
            myWriter.close();
        } catch (Exception ex) {
            // If an exception occurs, print the error message to the console
            System.err.println(ex);
        }
    }
    
    /**
     * Generates a random char string, used for file generation
     * @param length size of the randomized string
     * @return the randomized string
     */
    private static String generateRandomString(int length) {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    /**
     * Creates a temp file with a randomized name using generateRandomString()
     * @return a created file
     */
    public File createTemp() {
        try {
            File myObj = new File("temp" + generateRandomString(5) + ".txt");
            if (myObj.createNewFile()) {
                // System.err.println("File created: " + myObj.getName());
                return myObj;
            }
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     *  Creates a temp file with a specified prefix and a randomized suffix using generateRandomString()
     * @param name Filename prefix
     * @return a created File
     */
    public File createTemp(String name) {
        try {
            String fileName = name + generateRandomString(5);
            System.err.println("Creating File: " + fileName);
            File myObj = new File(fileName + ".txt");
            if (myObj.createNewFile()) {
                // System.err.println("File created: " + myObj.getName());
                return myObj;
            }
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * Reads the next line from a scanner
     * @param reader the scanner to read from
     * @return The line the scanner reads
     */
    public static String readLine(Scanner reader) {
        try {
            return reader.nextLine();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    /**
     * Merge all the files in a File List
     * @param files a list of Files to merge
     * @return A single merged file
     */
    public File mergeFiles(List<File> files) {
        try {
            // Crate a heap with a maximum size of 31
            MyMinHeap mergeHeap = new MyMinHeap(31);
            File outputFile = createTemp("Merge");

            System.err.println("Merging " + files.size() + " files");

            // Create a list of scanners and lines depending on the amount of files being merged
            List<Scanner> scanners = new ArrayList<>();
            List<String> lines = new ArrayList<>();
            for (File file : files) {
                scanners.add(new Scanner(file));
            }
            for (Scanner scan : scanners) {
                lines.add(readLine(scan));
            }

            // #region
            // File file1OBJ = files.get(0);
            // File file2OBJ = files.get(1);
            // System.err.println("Merging: " + file1OBJ.getName() + " and " +
            // file2OBJ.getName() + " into " + outputFile.getName());
            // Scanner file1Reader = new Scanner(file1OBJ);
            // Scanner file2Reader = new Scanner(file2OBJ);
            // String line1 = readLine(file1Reader);
            // String line2 = readLine(file2Reader);
            // #endregion

            boolean hasNonNull = lines.stream().anyMatch(Objects::nonNull);
            while (hasNonNull = lines.stream().anyMatch(Objects::nonNull)) {

                // Loop over lines array, while maintaining the index and the smallest line of
                // said index
                String smallest = "";
                int index = 0;
                int smallestIndex = 0;
                // Compare a single line from each file, the highest one's priority (alphabetical) and index is saved
                for (String line : lines) {
                    if (line != null) {
                        if (line.compareTo(smallest) < 0 || smallest.isBlank()) {
                            smallest = line; 
                            smallestIndex = index;
                        }
                    }
                    index++;
                }
                lines.set(smallestIndex, readLine(scanners.get(smallestIndex))); // The file with highest priority moves to it's next line
                Boolean inserted = mergeHeap.insert(smallest); // Insert
                if (!inserted) {
                    writeHeapToFile(outputFile, mergeHeap);
                    mergeHeap = new MyMinHeap(31);
                    mergeHeap.insert(smallest);
                }
            }
            // Write the heap to the output file
            writeHeapToFile(outputFile, mergeHeap);
            for (File file : files) {
                String name = file.getName();
                boolean isDeleted = file.delete();
                if (isDeleted)
                    System.err.println("Deleted: " + name);
            }
            return outputFile;
            // Catch errors
        } catch (Exception ex) {
            StackTraceElement[] stackTrace = ex.getStackTrace();
            if (stackTrace.length > 0) {
                int lineNumber = stackTrace[0].getLineNumber();
                System.err.println("Exception thrown on line " + lineNumber);
            }
        }

        return null;
    }

}

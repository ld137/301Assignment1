import java.io.*; // Import the File class
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Scanner; // Import the Scanner class to read text files
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class heapsort {
    public static void main(String[] args) {
         CreateRuns(30);
        // basicTest();
        mergeDirectory("Temp");
    }

    public static void basicTest() {
        MyMinHeap heap = new MyMinHeap(31);
        heap.insert("The");
        heap.print();
        heap.insert("Quick");
        heap.print();
        heap.insert("Brown");
        heap.print();
        heap.insert("Fox");
        heap.print();
        heap.insert("Jumps");
        heap.print();
        heap.insert("Over");
        heap.print();
        heap.insert("A");
        heap.print();
        heap.insert("Lazy");
        heap.print();
        heap.insert("Dog");
        heap.print();
        System.out.println("-----------------------");
        System.out.println("Replacing 'Lazy' with 'Incompentent'");
        heap.replace("Lazy", "Incompentent");
        heap.print();
        System.out.println("-----------------------");
        String removed = heap.remove();
        while (removed != null) {
            System.out.println(removed);
            removed = heap.remove();
        }
    }

    public static void CreateRuns(int length) {
        // Load in the file (Convert this to standard output later)
        int tempFileCount = 0;
        String tempFileName = "tmp";
        String tempFileextension = ".txt";
        int heapSize = length > 31 ? 31 : length; // length may not exceed 31 for heap size
        MyMinHeap heap = new MyMinHeap(heapSize);
        try {
            File myObj = new File("MobyDick.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                boolean inserted = heap.insert(data);
                if (!inserted) {
                    // write heap to file
                    createFile(heap, tempFileName + tempFileCount + tempFileextension, "Temp");
                    // Create a new heap
                    tempFileCount++;
                    heap = new MyMinHeap(heapSize);
                    heap.insert(data);
                    // Insert line we just used;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        createFile(heap, tempFileName + tempFileCount + tempFileextension, "Temp");
        tempFileCount++;
    }

    public static void createFile(MyMinHeap heap, String filename, String folder) {
        try {
            String fullPath = folder + "\\" + filename;
            File myObj = new File(fullPath);
            if (myObj.createNewFile()) {
                System.err.println("File created: " + myObj.getName());
                writeToFile(heap, fullPath);
            } else {
                // System.err.println("File already exists.");
                writeToFile(heap, fullPath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(MyMinHeap heap, String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            String writeValue = null;
            while ((writeValue = heap.remove()) != null) {
                if (writeValue.isBlank())
                    continue;
                myWriter.write(writeValue);
                myWriter.write(System.getProperty("line.separator"));
            }
            myWriter.close();
            System.err.println("Successfully wrote to the file. " + filename);
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void mergeDirectory(String directory) {
        int tempFileCount = 0;
        String tempFileName = "merge";
        String tempFileextension = ".txt";
        File dir = new File(directory);
        File[] directoryListing = dir.listFiles();
        String[] tempFiles = new String[2];
        int fileCount = 0;
        int fileCounter = 0;
        System.err.println("Found: " + directoryListing.length + " Files");
        if (directoryListing != null) {
            for (File child : directoryListing) {
                System.err.println("File: " + fileCounter + "/" + directoryListing.length);
                fileCounter++;
                    tempFiles[fileCount] = child.toPath().toString();
                    fileCount++;
                if (fileCount >= 2) {
                    mergeFiles(tempFiles[0], tempFiles[1], tempFileName + tempFileCount + tempFileextension);
                    fileCount = 0;
                    tempFileCount++;
                }
            }
            if ((fileCounter % 2) == 0) {
                System.err.println("Copying last file");
                try {
                    Files.copy(Paths.get(tempFiles[0]),
                            Paths.get("Merge\\" + tempFileName + tempFileCount + tempFileextension), REPLACE_EXISTING);
                } catch (IOException ex) {
                    System.err.println("Failed to copy the last file or something?");
                }
            }

        }
    }

    public static void mergeFiles(String file1, String file2, String outputFile) {
        System.err.println("Merging files");
        try {
            File file1OBJ = new File(file1);
            File file2OBJ = new File(file2);
            Scanner file1Reader = new Scanner(file1OBJ);
            Scanner file2Reader = new Scanner(file2OBJ);
            String line1 = readLine(file1Reader);
            String line2 = readLine(file2Reader);
            ;

            String tmpFileFolder = "Merge";
            MyMinHeap mergeHeap = new MyMinHeap(31);
            while (line1 != null || line2 != null) {
                if (line1 == null) {
                    // just write line 2
                    Boolean inserted = mergeHeap.insert(line2);
                    if (!inserted) {
                        createFile(mergeHeap, outputFile, tmpFileFolder);
                        mergeHeap = new MyMinHeap(31);
                        mergeHeap.insert(line2);
                    }
                    line2 = readLine(file2Reader);
                    continue;
                }
                if (line2 == null) {
                    // just write line 1
                    Boolean inserted = mergeHeap.insert(line1);
                    if (!inserted) {
                        createFile(mergeHeap, outputFile, tmpFileFolder);
                        mergeHeap = new MyMinHeap(31);
                        mergeHeap.insert(line1);
                    }
                    line1 = readLine(file1Reader);
                    continue;
                }
                if (line1.compareTo(line2) < 0) {
                    // write line 1
                    Boolean inserted = mergeHeap.insert(line1);
                    if (!inserted) {
                        createFile(mergeHeap, outputFile, tmpFileFolder);
                        mergeHeap = new MyMinHeap(31);
                        mergeHeap.insert(line1);
                    }
                    line1 = readLine(file1Reader);
                    continue;
                } else {
                    // write line 2
                    Boolean inserted = mergeHeap.insert(line2);
                    if (!inserted) {
                        createFile(mergeHeap, outputFile, tmpFileFolder);
                        mergeHeap = new MyMinHeap(31);
                        mergeHeap.insert(line2);
                    }
                    line2 = readLine(file2Reader);
                    continue;
                }

            }
            createFile(mergeHeap, outputFile, tmpFileFolder);
            file1Reader.close();
            file2Reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static String readLine(Scanner reader) {
        try {
            return reader.nextLine();
        } catch (NoSuchElementException ex) {

            // System.err.println("An error occurred.");
            // ex.printStackTrace();
            return null;
        }
    }
}

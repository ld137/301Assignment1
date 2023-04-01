import java.io.File;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class DistributeRuns {
    // For some reason this is not working so for now I will hardcode it
    public File originalFolder;
    public File targetFolder;
    public String targetFolderPath;
    public int input;
    public boolean isEmpty;
    List<File> files = new ArrayList<>();
    File currentFile = null;
    int kFiles = 2;

    public DistributeRuns(int count) {
        System.err.println("Started Distribute Runs");
        kFiles = count > 1 ? count : 2;
    }

    public File distribute(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = "";

        int fileCount = 0;
        try {
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
                    if (files.size() == 2) {
                        File mergedFile = mergeFiles(files);
                        files = new ArrayList<>();
                        files.add(mergedFile);
                        currentFile = mergedFile;
                    }
                    continue;
                } else {
                    if (!(line.isBlank())){
                        writeLineToFile(line, currentFile);
                    }
                }
            }
            if (files.size() == kFiles){
                File mergedFile = mergeFiles(files);
                files = new ArrayList<>();
                files.add(mergedFile);
                return mergedFile;
            }else {
                return files.get(0);
            }
            
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void writeHeapToFile(File file, MyMinHeap heap) {
        String removed = "";
        while ((removed = heap.remove()) != null) {
            writeLineToFile(removed, file);
        }
    }

    public static void writeLineToFile(String line, File file) {
        try {
            FileWriter myWriter = new FileWriter(file, true);
            myWriter.write(line);
            myWriter.write(System.getProperty("line.separator"));
            myWriter.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private static String generateRandomString(int length) {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

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

    public static String readLine(Scanner reader) {
        try {
            return reader.nextLine();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public File mergeFiles(List<File> files) {
        try {
            MyMinHeap mergeHeap = new MyMinHeap(31);
            File outputFile = createTemp("Merge");
            List<Scanner> scanners = new ArrayList<>();
            List<String> lines = new ArrayList<>();
            for(File file : files){
                scanners.add(new Scanner(file));
            }
            for(Scanner scan : scanners){
                lines.add(readLine(scan));
            }


            File file1OBJ = files.get(0);
            File file2OBJ = files.get(1);
            System.err.println("Merging: " + file1OBJ.getName() + " and " + file2OBJ.getName() + " into " + outputFile.getName());
            Scanner file1Reader = new Scanner(file1OBJ);
            Scanner file2Reader = new Scanner(file2OBJ);
            String line1 = readLine(file1Reader);
            String line2 = readLine(file2Reader);
            boolean hasNonNull = lines.stream().anyMatch(Objects::nonNull);
            while (hasNonNull = lines.stream().anyMatch(Objects::nonNull)) {

                //Loop over lines array, while maintaining the index and the smallest line of said index
                String smallest = null;
                int index = 0;
                for(String line : lines){
                    if (line.compareTo(smallest) < 0 ){
                        smallest = line;
                    }
                    index++;
                }
                lines.set(index, readLine(scanners.get(index)));
                Boolean inserted = mergeHeap.insert(smallest);
                if (!inserted){
                    writeHeapToFile(outputFile, mergeHeap);
                    mergeHeap = new MyMinHeap(31);
                    mergeHeap.insert(smallest);
                }
                


                if (line1 == null) {
                    // just write line 2
                    Boolean inserted = mergeHeap.insert(line2);
                    if (!inserted) {
                        // createFile(mergeHeap, outputFile, tmpFileFolder);
                        writeHeapToFile(outputFile, mergeHeap);
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
                        // createFile(mergeHeap, outputFile, tmpFileFolder);
                        writeHeapToFile(outputFile, mergeHeap);
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
                        // createFile(mergeHeap, outputFile, tmpFileFolder);
                        writeHeapToFile(outputFile, mergeHeap);
                        mergeHeap = new MyMinHeap(31);
                        mergeHeap.insert(line1);
                    }
                    line1 = readLine(file1Reader);
                    continue;
                } else {
                    // write line 2
                    Boolean inserted = mergeHeap.insert(line2);
                    if (!inserted) {
                        // createFile(mergeHeap, outputFile, tmpFileFolder);
                        writeHeapToFile(outputFile, mergeHeap);
                        mergeHeap = new MyMinHeap(31);
                        mergeHeap.insert(line2);
                    }
                    line2 = readLine(file2Reader);
                    continue;
                }

            }
            writeHeapToFile(outputFile, mergeHeap);
            for (File file : files) {
                boolean isDeleted = file.delete();
                //if (isDeleted)
                    //System.err.println("Deleted Temp File");
            }
            return outputFile;
        } catch (Exception ex) {

        }

        return null;
    }

    public DistributeRuns(int input, String originalFolderPath, String targetFolderPath) {
        this.originalFolder = new File(originalFolderPath);
        this.targetFolderPath = targetFolderPath;
        if (!originalFolder.exists()) {
            System.err.println("Folder does not exist: " + originalFolderPath);
        }
        this.targetFolder = new File(targetFolderPath);
        if (!targetFolder.exists()) {
            System.err.println("Folder does not exist: " + targetFolderPath);
        }
        File[] fileCheck = originalFolder.listFiles();
        if (fileCheck.length == 0) {
            System.err.println("Folder is empty: " + originalFolderPath);
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        this.input = input;
    }

    // public void distribute (int input){
    public void Distribute() {

        if (input > 1) {

            // Get all the files in the original folder
            File[] files = originalFolder.listFiles();

            // Move first file to the target folder if targer folder is empty
            if (targetFolder.listFiles().length == 0 && files.length >= 2) {
                files[1].renameTo(new File(targetFolder.getAbsolutePath() + files[1].separator + files[1].getName()));
                System.err.println("-----     Moved: 1 " + files[1].getName());
            }

            if (files.length == 0) {
                isEmpty = true;
                return;
            }

            // Move the second file to the target folder
            System.err.println("-----     Moved: 0 " + files[0].getName());
            files[0].renameTo(new File(targetFolder.getAbsolutePath() + files[0].separator + files[0].getName()));
            return;
        } else {
            return;
        }
    }

}

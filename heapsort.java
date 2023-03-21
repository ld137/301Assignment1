import java.io.*; // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files

public class heapsort {
    public static void main(String[] args) {
        CreateRuns(30);
        //basicTest();
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
                System.err.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writeToFile(MyMinHeap heap, String filename){
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            String writeValue = null;
            while ((writeValue = heap.remove()) != null){
                if (writeValue.isBlank()) continue;
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
}

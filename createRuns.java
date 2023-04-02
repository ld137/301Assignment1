import java.io.*;

public class createRuns {
    public static void main(String[] args) {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = "";

        int heapSize = 0;
        try {
            if (args.length > 0) {
                heapSize = Integer.parseInt(args[0]) > 31 ? 31
                        : (Integer.parseInt(args[0]) < 1 ? 31 : Integer.parseInt(args[0]));// Saves the specified
                                                                                           // heapsize into heapSize int
            } else {
                System.err.println("Error: Unspecified heap size"); // Specifies error and exits program
                heapSize = 31;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid integer " + args[0]); // If integer is incorrectly formated exits program
            heapSize = 31;
        }

        if (heapSize == 0) {
            System.err.println("Error: Insufficient heap size"); // Specifies error and exits program
            System.exit(0);
        }
        try {
            System.err.println("Creating " + heapSize + " Sized runs");
            MyMinHeap sortedOutput = new MyMinHeap(heapSize);
            while ((line = br.readLine()) != null) {
                boolean inserted = sortedOutput.insert(line);
                if (!inserted) {
                    String removed = "";
                    while ((removed = sortedOutput.remove()) != null) {
                        //System.out.println(removed);
                        writer.write(removed);
                        writer.newLine();
                    }
                    //System.out.println("||");
                    writer.write("||");
                    writer.newLine();
                    sortedOutput = new MyMinHeap(heapSize);
                    sortedOutput.insert(line);
                }
            }
            if (sortedOutput.getSize() > 0){
                String removed = "";
                    while ((removed = sortedOutput.remove()) != null) {
                        //System.out.println(removed);
                        writer.write(removed);
                        writer.newLine();
                    }
            }
            writer.flush();
            writer.close();
            br.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        // MyMinHeap initialHeap = new MyMinHeap(heapSize);
        // MyMinHeap secondHeap = new MyMinHeap(heapSize);

        // MyMinHeap sortedHeap = new MyMinHeap(heapSize * 2 - 1);

        // while (!initialHeap.peek().isEmpty() || !secondHeap.peek().isEmpty()) {
        //     if (initialHeap.peek().compareTo(secondHeap.peek()) < 0) {
        //         // Initial heap value goes next
        //         sortedHeap.insert(initialHeap.peek());
        //         initialHeap.remove();
        //     } else if (initialHeap.peek().compareTo(secondHeap.peek()) > 0) {
        //         sortedHeap.insert(secondHeap.peek());
        //         secondHeap.remove();
        //     }
        // }

    }
}

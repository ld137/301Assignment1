import java.io.File;
public class MergeRuns {

    // Moves two files two Merge then sort, then keep moving one temp to merge and sort till no more temp left then move to sort.

    public static void main(String[] args) {
   //     if (args.length == 0){ // change to args.length == 1 when done testing

            // DistributeRuns runs = new DistributeRuns(Integer.parseInt(args[0]), "Temp", "Merge");
            DistributeRuns runs = new DistributeRuns(2, "Temp", "Merge");
            // DistributeRuns runBack = new DistributeRuns(2, "Merge2", "Temp");
            Integer mergeCount = 0;


            // while(!runs.isEmpty){
            while(!runs.isEmpty){
                runs.Distribute();
                heapsort.mergeDirectory(runs.targerFolderPath);

                // File tempMerge = new File("Merge/merge0.txt");
                // tempMerge.renameTo(new File("Merge/temp.txt"));

                File[] mergeFiles = new File(runs.targerFolderPath).listFiles();
                for (File file : mergeFiles) {
                    file.renameTo(new File("Merge2" + file.separator + "merge" + mergeCount + ".txt"));
                }
                mergeCount++;
            }
                // mergeCount = 0;
                // while(!runBack.isEmpty){
                //     runBack.Distribute();
                //     heapsort.mergeDirectory(runBack.targerFolderPath);

                //     // File tempMerge = new File("Merge/merge0.txt");
                //     // tempMerge.renameTo(new File("Merge/temp.txt"));

                //     File[] mergeFilesBack = new File(runBack.targerFolderPath).listFiles();
                //     for (File file : mergeFilesBack) {
                //         file.renameTo(new File("Merge2" + file.separator + "merge" + mergeCount + ".txt"));
                //     }
                //     mergeCount++;
                // }
            // }
            // runs = new DistributeRuns(Integer.parseInt(args[0]), "Merge", "Sort");
            // runs = new DistributeRuns(2, "Merge", "Sort");
            // while(!runs.isEmpty){
            //     runs.Distribute();
            // }
        // }
        // else{
        //     System.err.println("Insufficient arguments");
        //     System.exit(1);
        // }
    }

    

}

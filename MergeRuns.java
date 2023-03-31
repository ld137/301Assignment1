import java.io.File;
public class MergeRuns {

    // Moves two files two Merge then sort, then keep moving one temp to merge and sort till no more temp left then move to sort.

    public static void main(String[] args) {
   //     if (args.length == 0){ // change to args.length == 1 when done testing

            DistributeRuns runs = new DistributeRuns(2, "Temp", "Merge");
            Integer mergeCount = 0;


            // while(!runs.isEmpty){
            while(!runs.isEmpty){
                // Grabs two files from temp folder
                runs.Distribute();

                // If one of the files is names merge0 rename it because it causes errors
                File tempMerge = new File("Merge/merge0.txt");
                if(tempMerge.exists()){
                    tempMerge.renameTo(new File("Merge/Merge.txt"));
                }

                // If there is two files in merge then merge them
                File[] mergeFiles = new File(runs.targetFolderPath).listFiles();

                if(mergeFiles.length == 2){
                    heapsort.mergeDirectory(runs.targetFolderPath);
                }

                
                
                // Move the files in the Merge folder to the Merge2 folder
                for (File file : mergeFiles) {
                    file.renameTo(new File("Merge2" + file.separator + "merge" + mergeCount + ".txt"));
                }
                // Increment the merge count
                mergeCount++;

               
            }

        // }
        // else{
        //     System.err.println("Insufficient arguments");
        //     System.exit(1);
        // }
    }

    

}

import java.io.File;
import java.io.*;

public class MergeRuns {

    // Moves two files two Merge then sort, then keep moving one temp to merge and sort till no more temp left then move to sort.

    public static void main(String[] args) {
        try {

            DistributeRuns DR = new DistributeRuns(0);
            File outputFile = DR.distribute();
            BufferedReader reader = new BufferedReader(new FileReader(outputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            outputFile.delete();
        } catch (Exception ex){
            System.err.println(ex);
        }
        return;
   //     if (args.length == 0){ // change to args.length == 1 when done testing
        //     Integer mergeCount = 0;
        //     boolean sorted = false;
        //     InputStreamReader isr = new InputStreamReader(System.in);
        //     BufferedReader br = new BufferedReader(isr);
        //     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        //     String line = "";
        //     try {
        //     while((line = br.readLine()) != null){

        //         DistributeRuns runs = new DistributeRuns(2, "Temp", "Merge");
        //         while(!runs.isEmpty){
        //             // Grabs two files from temp folder
        //             runs.Distribute();

        //             // If one of the files is names merge0 rename it because it causes errors
        //             File tempMerge = new File("Merge/merge0.txt");
        //             if(tempMerge.exists()){
        //                 tempMerge.renameTo(new File("Merge/Merge.txt"));
        //             }

        //             // If there is two files in merge then merge them
        //             File[] mergeFiles = new File(runs.targetFolderPath).listFiles();

        //             if(mergeFiles.length == 2){
        //                 heapsort.mergeDirectory(runs.targetFolderPath);
        //             }

        //             // Reset mergeFiles after the merging
        //             mergeFiles = new File(runs.targetFolderPath).listFiles();

        //             // Move the files in the Merge folder to the Merge2 folder
        //             for (File file : mergeFiles) {
        //                 file.renameTo(new File("Merge2" + file.separator + "merge" + mergeCount + ".txt"));
        //             }
        //             // Increment the merge count
        //             mergeCount++;
        //         }

        //         // Count how many files are left after merging
        //         File[] returnFiles = new File("Merge2").listFiles();
                
        //         // If there are more than 1 files left then put them back into temp to merge again
        //         if(returnFiles.length > 1){
        //             for (File file : returnFiles) {
        //             file.renameTo(new File("Temp" + file.separator + file.getName()));
        //             }
        //         }
        //         // If there is only 1 file left then put it in the sorted folder (For this to finish running it takes about 1 minute)
        //         else if(returnFiles.length == 1){
        //             returnFiles[0].renameTo(new File("Sorted" + returnFiles[0].separator + "Sorted.txt"));
        //             sorted = true;
        //         }
        //         mergeCount = 0;
        //     } // All code below this is for setting arguments
        // } catch (Exception ex){

        // }
            // else{ 
        //     System.err.println("Insufficient arguments");
        //     System.exit(1);
        // }
    }

    

}

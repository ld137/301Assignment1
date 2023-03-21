import java.io.File;

public class DistributeRuns {

    public static void DistributeRuns (int input){
        if (input > 1) {
            
            // Create a File object for the folder
            File originFolder = new File("Temp");
            if(originFolder.exists()){
                System.out.println("true");
            }

            File targetFolder = new File("Merge");
            if(originFolder.exists()){
                System.out.println("true");
            }
            
            // Get all the files in the folder
            File[] files = originFolder.listFiles();
            
            files[0].renameTo(new File(targetFolder.getAbsolutePath() + files[0].separator + files[0].getName()));

            // Loop through each file in the folder
            for (File file : files) {
                if (file.isFile()) {
                    // File targetFile = new File(targetFolder.getAbsolutePath() + file.separator + file.getName());
                    // // Do something with the file
                    // file.renameTo(targetFile);
                    file.renameTo(new File(targetFolder.getAbsolutePath() + file.separator + file.getName()));
                }
            }
            return;
        }
        else{
            return;
        }
    }
    
}

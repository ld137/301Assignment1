import java.io.File;

public class DistributeRuns {
    //For some reason this is not working so for now I will hardcode it
    public File originalFolder;
    public File targetFolder;
    public int input;

    public DistributeRuns(int input, String originalFolderPath, String targetFolderPath) {
        this.originalFolder = new File(originalFolderPath);
        if(!originalFolder.exists()){
            System.err.println("Folder does not exist: " + originalFolderPath);
        }
        this.targetFolder = new File(targetFolderPath);
        if(!targetFolder.exists()){
            System.err.println("Folder does not exist: " + targetFolderPath);
        }
        this.input = input;
    }

    //public void distribute (int input){
    public void Distribute() {

        if (input > 1) {
            
            // Get all the files in the original folder
            File[] files = originalFolder.listFiles();
            
            // Move first file to the target folder if targer folder is empty
            if(targetFolder.listFiles().length == 0)
            {
                files[0].renameTo(new File(targetFolder.getAbsolutePath() + files[0].separator + files[0].getName()));
            }

            // Move the second file to the target folder
            files[1].renameTo(new File(targetFolder.getAbsolutePath() + files[1].separator + files[1].getName()));
            return;
        }
        else{
            return;
        }
    }
    
}

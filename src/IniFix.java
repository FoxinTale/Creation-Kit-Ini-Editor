import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IniFix {
    public static void writeIni(){
        File customINI = new File(System.getProperty("user.dir") + File.separator + "CreationKitCustom.ini");
        String customINIContents[] = {"[General]", "bAllowMultipleMasterLoads=1", "[Grass]", "bAllowCreateGrass=1", "bAllowLoadGrass=0"};

        try {
            FileWriter customINIWriter = new FileWriter(customINI);

            for (String customINIContent : customINIContents) {
                customINIWriter.write(customINIContent + "\n");
            }
            customINIWriter.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}

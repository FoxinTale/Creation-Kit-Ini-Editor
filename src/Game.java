import java.io.File;

public class Game {

    public static void findFoldersAndFiles(){
        File skyrim = new File ("F:\\Steam\\steamapps\\common\\Skyrim Special Edition\\"); //Make sure to change this, as not everyone has the same install location.
        File creationKit;
        File creationKitConfig;
        File gameData;

        if(skyrim.exists()){
            creationKit = new File(skyrim.getAbsolutePath() + "CreationKit.exe"); // Sanity check here.
            if(creationKit.exists()){
                creationKitConfig = new File(skyrim.getAbsolutePath() + "CreationKitPrefs.ini");
                gameData = new File(skyrim.getAbsolutePath() + "Data");
            } else{
                System.out.println("Creation Kit was not found.");
            }
        } else {
            System.out.println("Could not find Skyrim Special Edition!");
        }
    }
}

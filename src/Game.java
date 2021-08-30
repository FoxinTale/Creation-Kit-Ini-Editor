import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    public static void findFoldersAndFiles() {
        File skyrim = new File("F:\\Steam\\steamapps\\common\\Skyrim Special Edition"); //Make sure to change this, as not everyone has the same install location.
        File creationKit;
        File creationKitConfig;
        File gameData;

        List<Path> gameDataContents = null;
        ArrayList<String> dataBSAs = new ArrayList<>();


        if (skyrim.exists()) {
            creationKit = new File(skyrim.getAbsolutePath() + File.separator +  "CreationKit.exe"); // Sanity check here.
            if (creationKit.exists()) {
                creationKitConfig = new File(skyrim.getAbsolutePath() + File.separator + "CreationKitPrefs.ini");
            } else {
                System.out.println("Creation Kit was not found.");
            }
            gameData = new File(skyrim.getAbsolutePath() + File.separator +"Data");
            try {
                gameDataContents = findBSAFiles(gameData.toPath());
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

            for (Path gameDataContent : gameDataContents) {
                dataBSAs.add(gameDataContent.getFileName().toString());
            }

            // Remove the base game files from the list.
            dataBSAs = removeBaseGameFilesFromList(dataBSAs);

            Debug.printArrayList(dataBSAs);
        } else {
            System.out.println("Could not find Skyrim Special Edition!");
        }
    }


    public static List<Path> findBSAFiles(Path path) throws IOException {
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }
        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isRegularFile)   // is a file
                    .filter(p -> p.getFileName().toString().endsWith(".bsa"))
                    .collect(Collectors.toList());
        }
        return result;
    }


    // Removes the base game's files from the list. they're already loaded.
    public static ArrayList<String> removeBaseGameFilesFromList(ArrayList<String> dataBSAs){
        ArrayList<Integer> baseGameBSAs = new ArrayList<>();

        for(int i = 0; i < dataBSAs.size(); i++){
            switch(dataBSAs.get(i)){
                case "Skyrim - Animations.bsa":
                case "Skyrim - Meshes1.bsa":
                case "Skyrim - Meshes0.bsa":
                case "Skyrim - Interface.bsa":
                case "Skyrim - Patch.bsa":
                case "Skyrim - Sounds.bsa":
                case "Skyrim - Textures0.bsa":
                case "Skyrim - Textures1.bsa":
                case "Skyrim - Textures2.bsa":
                case "Skyrim - Textures3.bsa":
                case "Skyrim - Textures4.bsa":
                case "Skyrim - Textures5.bsa":
                case "Skyrim - Textures6.bsa":
                case "Skyrim - Textures7.bsa":
                case "Skyrim - Textures8.bsa":
                case "Skyrim - Voices_en0.bsa":
                    baseGameBSAs.add(i);
                    break;
                default:
                    //Do nothing. More information about the Voices file may be needed. I don't know what other languages exist out there.
            }
            //  baseGameBSAs.add(42);
        }

        List<String> tempList = dataBSAs.subList(baseGameBSAs.get(0), baseGameBSAs.get(baseGameBSAs.size() - 1));
        dataBSAs.removeAll(tempList);
        dataBSAs.remove("Skyrim - Voices_en0.bsa");

        return dataBSAs;
    }
}

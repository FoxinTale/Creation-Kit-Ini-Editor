import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOps {
    public static void readLang(File langFile){
        String currentLine;
        ArrayList<String> strings = new ArrayList<>();

        try{
            Scanner langReader = new Scanner(langFile);
            while(langReader.hasNext()){
                currentLine = langReader.nextLine();
                strings.add(currentLine);
            }
            langReader.close();
        } catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        Strings.parseLangArray(strings);
    }

    public static void readValveLibrary(File libraryFile){
        ArrayList<String> libraryContents = new ArrayList<>();
        String currentLine;

        try{
            Scanner langReader = new Scanner(libraryFile);
            while(langReader.hasNext()){
                currentLine = langReader.nextLine();
                libraryContents.add(currentLine);
            }
            langReader.close();
        } catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        Strings.parseValveLibrary(libraryContents);
    }

    public static ArrayList<String> readCreationKitConfig(File ckIni){
        String currentLine;
        ArrayList<String> ckConfigContents = new ArrayList<>();

        try{
            Scanner configReader = new Scanner(ckIni);
            while(configReader.hasNext()){
                currentLine = configReader.nextLine();
                ckConfigContents.add(currentLine);
            }

        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        return ckConfigContents;
    }

    public static void findSkyrim(ArrayList<String> folders){
        ArrayList<File> steamFolders = new ArrayList<>();
        String name;
        String[] contents;
        for (String folder : folders) {
            name = folder.replace("\"", " ").strip();
            steamFolders.add(new File(name + "\\steamapps\\common\\"));
        }

        for (File steamFolder : steamFolders) {
            contents = steamFolder.list();
            for (String content : contents) {
                if (content.equals("Skyrim Special Edition")) {
                    Main.skyrimInstall = steamFolder + "\\Skyrim Special Edition\\";
                    break;
                }
            }
        }


        //Debug.printList(List.of(contents));
    }
}

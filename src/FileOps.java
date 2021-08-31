import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
}

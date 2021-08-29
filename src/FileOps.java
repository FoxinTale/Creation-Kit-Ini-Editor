import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOps {
    public static void ReadLang(File langFile){
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
}

import java.io.File;
import java.util.Locale;

public class Main {

    public static void main(String[] args){
        //GetSystemLang();
        //IniFix.writeIni();
        Game.findFoldersAndFiles();
    }

    public static void GetSystemLang(){
        String language = Locale.getDefault().toString();

        File langFile;
        String langFolder = System.getProperty("user.dir") + File.separator + "lang" + File.separator;

        // More languages can be added easily this way.
        switch(language){
            case "en_UK":
                langFile = new File (langFolder + "en_UK.txt");
                break;
            default:
                langFile = new File (langFolder + "en_US.txt");
        }
        FileOps.ReadLang(langFile);
    }
}

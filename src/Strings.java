import java.util.ArrayList;

public class Strings {

    public static String windowTitle;
    public static String mainButtonText;
    public static String generalTabText;
    public static String mainTabText;
    public static String displayTabText;


    public static void parseLangArray(ArrayList<String> list){
        ArrayList<String> langStrings = new ArrayList<>();
        String temp;

        for(int i = 0; i < list.size(); i++){
            temp = ""; // Clear the string.
            temp = list.get(i).split("=")[1].replaceAll("\"", " ").strip(); // A somewhat messy one-liner.
            langStrings.add(temp);
        }
        setStrings(langStrings);
    }

    public static void parseValveLibrary(ArrayList<String> libraryContents){
        ArrayList<String> libraryFolders = new ArrayList<>();

        for (String libraryContent : libraryContents) {
            if (libraryContent.contains("\"path\"")) {
                libraryFolders.add(libraryContent);
            }
        }
        String[] path;

        for(int i = 0; i < libraryFolders.size(); i++){
            path = libraryFolders.get(i).split("\t\t");
            libraryFolders.set(i, path[2]);
        }
        FileOps.findSkyrim(libraryFolders);
    }

    public static void setStrings(ArrayList<String> langStrings){
        windowTitle = langStrings.get(0);
        mainButtonText = langStrings.get(1);
        generalTabText = langStrings.get(2);
        //launchGUI(); // Now that all our strings are initialised, we can launch the GUI.
    }

    // No, I don't know why I have to do it this way. Java complains if I have the call outside here.
    //public static void launchGUI(){
      //  GUI.MakeGUI();
  //  }
}

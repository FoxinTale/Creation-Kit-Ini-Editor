import java.util.ArrayList;

public class Strings {

    public static String windowTitle;

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

    public static void setStrings(ArrayList<String> langStrings){
        windowTitle = langStrings.get(0);
        launchGUI(); // Now that all our strings are initialised, we can launch the GUI.
    }

    // No, I don't know why I have to do it this way. Java complains if I have the call outside here.
    public static void launchGUI(){
        GUI.MakeGUI();
    }
}

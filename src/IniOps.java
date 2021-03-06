import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IniOps {
    public static List<String> general;
    public static List<String> main;
    public static List<String> display;
    public static List<String> previewMovement;
    public static List<String> messages;
    public static List<String> movement;
    public static List<String> archive;
    public static List<String> papyrus;
    public static List<String> flowchart;


    public static void processConfigFile(File config){
        ArrayList<String> configContents = FileOps.readCreationKitConfig(config);
        ArrayList<Integer> points = new ArrayList<>();
        String item = "";
        boolean multipleMastersCheck = false;

        for(int i = 0; i < configContents.size(); i++){
            item = configContents.get(i);
            switch (item) {
                case "[General]", "[MAIN]", "[Display]", "[PreviewMovement]", "[MESSAGES]", "[Movement]", "[Archive]", "[Papyrus]", "[Flowchart]" ->  //8
                        points.add(i);
            }
        }

        general = configContents.subList(points.get(0), points.get(1) - 1); // Because there's a whitespace right before, subtract 1.
        main = configContents.subList(points.get(1), points.get(2) - 1);
        display = configContents.subList(points.get(2 + 1), points.get(3));
        previewMovement = configContents.subList(points.get(3), points.get(4) - 1);
        messages = configContents.subList(points.get(4), points.get(5) - 1);
        movement = configContents.subList(points.get(5), points.get(6) - 1);
        archive = configContents.subList(points.get(6), points.get(7) - 1);
        papyrus = configContents.subList(points.get(7), points.get(8) - 1);
        flowchart = configContents.subList(points.get(8), configContents.size());

    //    for (String s : general) {
      //      item = s;
       //     if (item.contains("bAllowMultipleMasterLoads")) {
        //        multipleMastersCheck = true;
          //      break;
           // }
        //}

       // if(!multipleMastersCheck){
        //    general.add("bAllowMultipleMasterLoads=1");
        //}

    //    GUI.iniGUI();
     //   Debug.printList(display);
    }



    public static void addBSAsToArchive(ArrayList<String> selected){
        String archiveTwo = "";
        int pos = 0;
        for(int  i = 0; i < archive.size(); i++){
            if(archive.get(i).contains("SResourceArchiveList2")){
                archiveTwo = archive.get(i);
                pos = i;
            }
        }
        StringBuilder archiveBuilder = new StringBuilder();
        archiveBuilder.append(archiveTwo);

        if(selected.size() > 0){
            for (String s : selected) {
                archiveBuilder.append(", ").append(s);
            }
            archive.set(pos, archiveBuilder.toString());
            writeNewIni();
        } else {
            System.out.println("Nothing is selected.");
        }
    }

    public static void writeNewIni(){
        ArrayList<Object[]> contents = new ArrayList<>();
     //   File newIni = new File("CreationKit.ini");
        contents.add(general.toArray());
        contents.add(main.toArray());
        contents.add(display.toArray());
        contents.add(previewMovement.toArray());
        contents.add(messages.toArray());
        contents.add(movement.toArray());
        contents.add(archive.toArray());
        contents.add(papyrus.toArray());
        contents.add(flowchart.toArray());
        try {
            FileWriter iniWriter = new FileWriter("CreationKit.ini");

            for(int i = 0; i < contents.size(); i ++){
                for(int j = 0; j < contents.get(i).length; j++){
                    iniWriter.write(contents.get(i)[j].toString() + "\n");
                }
                iniWriter.write("\n");
            }
          //  listWriter.write(list.get(i));
            iniWriter.close();
            System.out.println("Write complete");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


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

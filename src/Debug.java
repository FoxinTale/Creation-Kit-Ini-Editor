import java.util.ArrayList;
import java.util.List;

public class Debug {
    // Temporary, just for debugging the program.
    public static void printArrayList(ArrayList<String> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public static void printList(List<String> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}

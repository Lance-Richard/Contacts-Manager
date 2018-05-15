//import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import util.Input;

public class ContactsManager {
    public static void main(String[] args) {
        String directory = "data";
        String filename = "contacts.txt";

        createFileIfNonExists(directory, filename);
    }

    private static void createFileIfNonExists(String directory, String filename) {
        ArrayList<String> names = makeList();
        try{
            writeListToFile(names, directory, filename);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        try {
            readLines(directory, filename);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        try {
            if(Files.notExists(dataDirectory)){
                Files.createDirectories(dataDirectory);
            }

            if(Files.notExists(dataFile)){
                Files.createFile(dataFile);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void writeListToFile(ArrayList<String> names, String directory, String filename) throws IOException {
        Path filepath = Paths.get(directory, filename);
        Files.write(filepath, names, StandardOpenOption.APPEND);
    }

    private static void readLines(String directory, String filename) throws IOException{
        Path filepath = Paths.get(directory, filename);
        List<String> list = Files.readAllLines(filepath);
        for(String name : list){
            System.out.println(name);
        }
    }
    private static ArrayList<String> makeList() {
        ArrayList<String> list = new ArrayList<>();
        Input input = new Input();
        String name;
        do{
            name = input.getString("Please enter a new name and phone number");
            list.add(name);
        }while (input.yesNo("Do you want to add another name to the list?"));
        return list;
    }
}

//import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.Input;

public class ContactsManager {
    public static void main(String[] args) {
        String directory = "data";
        String filename = "contacts.txt";
        Input userChoice = new Input();

        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        int select = userChoice.getInt();

        if(select == 1){
            try {
                readLines(directory, filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println(filename);
        }
        if(select == 2){
            createContact();
        }
        if(select == 3){
            searchbyName(directory, filename);
            try {
                readLines(directory, filename);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
//        createFileIfNoneExists(directory, filename);
    }

    private static void createFileIfNoneExists(String directory, String filename) {
        ArrayList<String> names = createContact();
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
    private static ArrayList<String> createContact() {
        ArrayList<String> list = new ArrayList<>();
        Input input = new Input();
        String name;
        String phoneNumber;
        do {
            name = input.getString("Please enter a new name");
            list.add(name);
            phoneNumber = input.getString("Please enter a phone number for " + name + ".");
            list.add(phoneNumber);

        }while(input.yesNo("Do you want to add another name and number"));
        return list;
    }

    private static void searchbyName(String directory, String filename) {
        String userName = getString();
        if (userName.equalsIgnoreCase()) {
            System.out.println(userName);
        }else
            System.out.println("Contact not found");
    }

    private static String getString() {
        Input input = new Input();
        String userName = input.getString("Please enter a contact name");
        return userName;
    }

    }

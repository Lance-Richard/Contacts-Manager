import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import util.Input;

public class ContactsManager {

    public static ArrayList<Contact> newArrayList = new ArrayList<Contact>();
    public static String directory = "data";
    public static String filename = "contacts.txt";

    public static void main(String[] args) {
        appBody();
        createFileIfNoneExists(directory, filename);
    }

    public static void appBody() {
        Input userChoice = new Input();
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        int select = userChoice.getInt(0,6);
        if (select == 1) {
            try {
                viewContacts(directory, filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (select == 2) {
            createContact();
        }
        if (select == 3) {
            try {
                searchByName(directory, filename);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if (select == 4) {
            try {
                removeByName(directory, filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (select == 5) {
            System.out.println("Thank you for using Contacts Manager");
            System.exit(0);
        }
        appBody();
    }

    public static void createFileIfNoneExists(String directory, String filename) {
        ArrayList<String> names = createContact();
        try {
            writeListToFile(names, directory, filename);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            viewContacts(directory, filename);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
            if (Files.notExists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeListToFile(ArrayList<String> names, String directory, String filename) throws IOException {
        Path filepath = Paths.get(directory, filename);
        Files.write(filepath, names, StandardOpenOption.APPEND);
    }


    public static void reWriteListToFile(ArrayList<String> names, String directory, String filename) throws IOException {
        Path filepath = Paths.get(directory, filename);
        Files.write(filepath, names);
    }

    public static void viewContacts(String directory, String filename) throws IOException {
        Path filepath = Paths.get(directory, filename);
        List<String> list = Files.readAllLines(filepath);
        System.out.println("Name       |  Phone Number");
        System.out.println("--------------------------");
        try {
            for (int i = 0; i <= list.size(); i++) {
                if (i % 2 == 0) {
                    System.out.println(list.get(i) + "       |    " + list.get(i + 1));
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> createList(String directory, String filename) throws IOException {
        Path filepath = Paths.get(directory, filename);
        ArrayList<String> list = (ArrayList<String>) Files.readAllLines(filepath);
        return list;
    }

    public static ArrayList<String> createContact() {
        ArrayList<String> list = new ArrayList<>();
        Input input = new Input();
        String name;
        String phoneNumber;
        do {
            name = input.getString("Please enter a new name");
            phoneNumber = input.getString("Please enter a phone number for " + name + ".");
            list.add(name);
            list.add(phoneNumber);
            System.out.println(name);
        } while (input.yesNo("Do you want to add another name and number"));
        try {
            writeListToFile(list, directory, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void searchByName(String directory, String filename) throws IOException {
        ArrayList<String> list = createList(directory, filename);
        String userName = getString();
        try {
            for (int i = 0; i <= list.size(); i++) {
                if (!userName.equalsIgnoreCase(list.get(i))) {
                    continue;
                }
                if (userName.equalsIgnoreCase(list.get(i))) {
                    System.out.println(list.get(i));
                    System.out.println(list.get(i + 1));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("User not found");
            searchByName(directory, filename);
        }
    }

    public static void removeByName(String directory, String filename) throws IOException {
        ArrayList<String> list = createList(directory, filename);
        String userName = getString();
        try {
            for (int i = 0; i <= list.size(); i++) {
                if (!userName.equalsIgnoreCase(list.get(i))) {
                    continue;
                }
                if (userName.equalsIgnoreCase(list.get(i))) {
                    list.remove(i);
                    list.remove(i);
                    System.out.println(list);
                    recreateFile(directory, filename, list);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("User not found");
            searchByName(directory, filename);
        }
    }

    private static void recreateFile(String directory, String filename, ArrayList list) {
        ArrayList<String> names = list;
        Path filepath = Paths.get(directory, filename);
        try {
            Files.write(filepath, names, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reWriteListToFile(names, directory, filename);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            viewContacts(directory, filename);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getString() {
        Input input = new Input();
        String userName = input.getString("Please enter a contact name");
        return userName;
    }
}
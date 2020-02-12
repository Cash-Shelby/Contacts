package ContactManager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static List<Contact> contactObject = new ArrayList<>();
    private static List<String> contactList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Application application = new Application();
        Contact contact = new Contact("Cash", "000-000-0000");
        contactList.add(contact.setContactString());
        newContactFile();
        application.menuMethod();

        System.out.println(contactList);
    }

    public void menuMethod() {
        boolean shouldContinue = true;
        int option = 1;
        while (shouldContinue) {
            System.out.println("1. View Contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit");
            System.out.println("Enter an option (1, 2, 3, 4, or 5): ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    writeContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    writeContacts();
                    shouldContinue = false;
                    break;
            }
        }


    }

//    public void input() {
//        this.scanner = new Scanner(System.in);
//    }


    static void newContactFile() {
        String directory = "contacts";
        String contactFileName = "Contacts.txt";

        Path contactsDirectory = Paths.get(directory);
        System.out.println(contactsDirectory.toAbsolutePath());

        Path contactFile = Paths.get(directory, contactFileName);

        try {
            if (Files.notExists(contactsDirectory)) {
                Files.createDirectories(contactsDirectory);
                System.out.println("Directory Created");
            }
            if (!Files.exists(contactFile)) {
                Files.createFile(contactFile);
                System.out.println("File Created");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    static void writeContacts() {
        try {
            Path contactsPath = Paths.get("contacts", "Contacts.txt");
            Files.write(contactsPath, contactList);
            for (int i = 0; i < contactList.size(); i += 1) {
                System.out.println((i + 1) + ": " + contactList.get(i));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void addContact() {
//        boolean error = true;
//        scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter phone number: ");
        String number = scanner.next();
        Contact newContact = new Contact(name, number);
        contactList.add(newContact.setContactString());
        writeContacts();
    }

    public void deleteContact() {
        System.out.println("Enter a number to delete: ");
        int choice = scanner.nextInt();
        contactList.remove(choice - 1);
//        writeContacts();
    }

    public void searchContact() {
        System.out.println("Enter a name to search for: ");
        String names = scanner.nextLine();
        if (contactList.contains(names)) {
            System.out.println("");


        }
    }


}



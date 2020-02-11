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
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Contact contact = new Contact("Cash", "000-000-0000", "123@gmail.com");
        contactList.add(contact.setContactString());
        newContactFile();
        System.out.println(contactList);
        writeContacts();
        addContact();

    }


    static void newContactFile(){
        String directory = "contacts";
        String contactFileName = "Contacts.txt";

        Path contactsDirectory = Paths.get(directory);
        System.out.println(contactsDirectory.toAbsolutePath());

        Path contactFile = Paths.get(directory, contactFileName);

        try{
            if(Files.notExists(contactsDirectory)){
                Files.createDirectories(contactsDirectory);
                System.out.println("Directory Created");
            }
            if(!Files.exists(contactFile)){
                Files.createFile(contactFile);
                System.out.println("File Created");
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    static void writeContacts(){
        try{
            Path contactsPath = Paths.get("contacts", "Contacts.txt");
            Files.write(contactsPath, contactList);
            for(int i = 0; i < contactList.size(); i += 1){
                System.out.println((i + 1) + ": " + contactList.get(i));
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void addContact(){
//        boolean error = true;
//        scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String number = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        Contact newContact = new Contact(name, number, email);
        contactList.add(newContact.setContactString());
        writeContacts();
    }

    public static void deleteContact(){
//        System.out.println("Enter name to delete: ");
//        String name = scanner.nextLine();
//        System.out.println("Enter phone number to delete: ");
//        String number = scanner.nextLine();
//        System.out.println("Enter email: ");
//        String email = scanner.nextLine();
//        Contac
    }


}

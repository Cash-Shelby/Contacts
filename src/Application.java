package src;

import javax.naming.Name;
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
        Contact contact = new Contact("Cash", "000-000-0000");
        Contact contact2 = new Contact("Jack Blank", "210-567-8923");
        Contact contact3 = new Contact("Jane Doe", "234-789-8902");
        Contact contact4 = new Contact("Sam Space", "210-581-8123");
        contactList.add(contact.setContactString());
        contactList.add(contact2.setContactString());
        contactList.add(contact3.setContactString());
        contactList.add(contact4.setContactString());
        newContactFile();
//        System.out.println(contactList);
//        writeContacts();
//        addContact();
        boolean shouldContinue = true;
        int option = 1;
        while(shouldContinue){
            System.out.println("1. View contacts.\n2. Add a new contact. \n3. Search a contact by name. \n4. Delete an existing contact. \n5. Exit \nEnter an option (1, 2, 3, 4 or 5): ");
            option = scanner.nextInt();
            switch(option){
                case 1:
                    System.out.println("Name \t | Phone Number |");
                    System.out.println("---------------------------");
                    writeContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContact(contactObject);
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

    public static void writeContacts(){
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
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String number = scanner.nextLine();
        Contact newContact = new Contact(name, number);
        contactList.add(newContact.setContactString());
        writeContacts();
    }

    public static void deleteContact(){
        System.out.println("Enter a number to delete: ");
        int choice = scanner.nextInt();
        contactList.remove(choice - 1);
        writeContacts();
    }

    public static Contact searchContact(final List<Contact> contactList){
        System.out.println("Enter a name to search for: ");
        String names = scanner.nextLine();
        for(Contact contact : contactList){
            if(contact != null && contact.getName().equals(names)){
                return contact;
            }
        }
        return null;
    }


}

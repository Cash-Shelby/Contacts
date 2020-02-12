package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private static ArrayList<Contact> contactObject = loadContacts();

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean shouldContinue = true;
        contactObject = loadContacts();
        int option = 1;
        while(shouldContinue){
            System.out.println("1. View contacts.\n2. Add a new contact. \n3. Search a contact by name. \n4. Delete an existing contact. \n5. Exit \nEnter an option (1, 2, 3, 4 or 5): ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch(option){
                case 1:
                    System.out.println("Name \t | Phone Number |");
                    System.out.println("---------------------------");
                    for(Contact c : contactObject) {
                        System.out.println(c.getName() + " | " + c.getPhone());
                    }
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    System.out.println("Enter a name to search for: ");
                    String names = scanner.nextLine();
                    System.out.println(searchContact(names));
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

    private static ArrayList<Contact> loadContacts() {
        ArrayList<Contact> tmpList = new ArrayList<Contact>();
        try {
            File fileobj = new File("contacts/contacts.txt");
            Scanner reader = new Scanner(fileobj);
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                tmpList.add(new Contact(line[0], line[1]));
            }
            reader.close();
        } catch (FileNotFoundException exc) {
            newContactFile();
            return new ArrayList<Contact>();
        }
        return  tmpList;
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
            FileWriter writer = new FileWriter("contacts/contacts.txt");
            for(Contact c : contactObject) {
                writer.write(c.toString());
                writer.write("\n");
            }
            writer.close();
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
        contactObject.add(newContact);
    }

    public static void deleteContact(){
        System.out.println("Enter the name of the contact you would like to delete: ");
        Contact choice = searchContact(scanner.nextLine());
        if(choice != null) {
            contactObject.remove(choice);
        } else {
            System.out.println("Invalid contact.");
            deleteContact();
        }
    }

    public static Contact searchContact(String names){
        for(Contact contact : contactObject){
            if(contact != null && contact.getName().equalsIgnoreCase(names)){
                return contact;
            }
        }
        return null;
    }
}

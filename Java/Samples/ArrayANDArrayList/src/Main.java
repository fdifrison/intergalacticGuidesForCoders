import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Contact> myContactList = new ArrayList<Contact>();
        MobilePhone myPhone = new MobilePhone("3386241039", myContactList);

        Contact luca = Contact.createContact("Luca", "123456789");
        Contact marco = Contact.createContact("Marco", "123456789");
        Contact filippo = Contact.createContact("Filippo", "123456789");
        Contact serena = Contact.createContact("Serena", "123456789");


        myPhone.addNewContact(luca);
        myPhone.addNewContact(marco);
        myPhone.addNewContact(serena);
        // test duplicate
        myPhone.addNewContact(luca);
        // remove non exist
        myPhone.removeContact(filippo);
        // remove exist
        myPhone.removeContact(marco);
        // query exist
        myPhone.queryContact("Serena");
        // query not exist
        myPhone.queryContact("Giovanni");
        // print contacts
        myPhone.printContact();

    }
}
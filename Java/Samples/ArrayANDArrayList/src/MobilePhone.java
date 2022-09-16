import java.util.ArrayList;

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber, ArrayList<Contact> myContacts) {
        this.myNumber = myNumber;
        this.myContacts = myContacts;
    }

    public int findContact(Contact contact) {
        if (myContacts.contains(contact)) {
            System.out.println("Found contact " + contact.getName());
            return myContacts.indexOf(contact);
        } else return -1;
    }

    public boolean addNewContact(Contact contact) {
        if (myContacts.contains(contact)) {
            System.out.println("Contact " + contact.getName() + " already present");
            return  false;
        } else {
            System.out.println("Adding contact for " + contact.getName());
            myContacts.add(contact);
            return true;
        }
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int indexOf = findContact(oldContact);
        if ( indexOf >= 0) {
            myContacts.set(indexOf, newContact);
            System.out.println("Contact updated: " + oldContact.getName());
            return true;
        } else {
            System.out.println("Can't update, contact doesn't exists");
            return false;
        }
    }

    public boolean removeContact(Contact contact) {
        int indexOf = findContact(contact);
        if ( indexOf != -1) {
            myContacts.remove(indexOf);
            System.out.println("Contact removed: " + contact.getName());
            return true;
        } else {
            System.out.println("Can't remove, contact doesn't exists");
            return false;
        }
    }

    public Contact queryContact(String name) {
        for (int i = 0; i<myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            if (contact.getName().equals(name)) {
                return myContacts.get(i);
            }
        }
        return null;
    }

    public void printContact() {
        for (int i = 0; i<myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            System.out.println(i+1 + ". " + contact.getName() + " -> " + contact.getPhoneNumber());
            }
    }




}

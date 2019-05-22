
package phonebook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author kubaokleja
 */
public class TeleBook implements Iterable<Contact>{
    
    private Map <String, Contact> contacts= new TreeMap<>(); //map where name is a Key and contact is a value
    public TeleBook(Map contacts)
    {
        this.contacts=contacts;
    }

    TeleBook() {
        
    }
    //methods to service application options
    
    public boolean addNewContact(String name, String phoneNumber)
    {
        //name and number connot be a null
        if(name==null||phoneNumber==null)
        {
            throw new NullPointerException("Name and/or phone number are null.");
            
        }
        if(name.equals("")||phoneNumber.equals(""))
        {
            throw new IllegalArgumentException("Name and/or phone number are empty");
        }
        //names cannot repeat (it is not a best option, because it is not an unique parameter)
        if(!contacts.containsKey(name))
        {
            contacts.put(name, new Contact (name, phoneNumber));
            return true;
        }
           return false; 
    }
    public boolean deleteContact(String name)
    {
       if(contacts.containsKey(name)) 
       {
       contacts.remove(name);
       return true;
       }
       else 
       return false;
       
    }
    public List<Contact> searchByName(String str)
    {
        List<Contact> foundedNames = new ArrayList<>();
        for(Map.Entry<String, Contact> entry : contacts.entrySet())
        {
            //if name contains parameter than add that person to new list with that persons
            if(entry.getKey().contains(str))
                foundedNames.add(entry.getValue());
        }
        //returns list with matching persons
       return foundedNames; 
    }
    //same as above method, but parameter is number
    public List<Contact> searchByNumber(String str)
    {
        List<Contact> foundedNames = new ArrayList<>();
        for(Map.Entry<String, Contact> entry : contacts.entrySet())
        {
            if(entry.getValue().getNumber().contains(str))
                foundedNames.add(entry.getValue());
        }
       return foundedNames;
    }

    @Override
    public Iterator<Contact> iterator() {
        return contacts.values().iterator();
    }
}

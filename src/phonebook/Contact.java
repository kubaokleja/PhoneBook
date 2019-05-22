
package phonebook;
// Class which contains one contact of my book
public class Contact implements Comparable<Contact>{
    private String name;
    private String number;
    public Contact(String name, String number)
    {
        this.name=name;
        this.number=number;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getNumber()
    {
        return this.number;
    }
    public void setNumber(String number)
    {
        this.number=number;
    }
    @Override
    public String toString()
    {
        return "Name: "+this.getName()+" Number: "+this.getNumber();            
    }
    //Comparison contacts by names like in every phonebook
    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.name);
    }
}

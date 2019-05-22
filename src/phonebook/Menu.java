
package phonebook;

import java.util.NoSuchElementException;

//menu of apllication, options of program
public enum Menu {
    ADD(1, "Add contact"),
    SEARCH_NAME(2, "Search contact"),
    SEARCH_NUMBER(3, "Search number"),
    REMOVE(4, "Remove contact"),
    PRINT(5, "Show all phone book"),
    CLOSE(6, "Close");
    private int id;
    private String menuOption;
    
    Menu(int id,String menuOption)
    {
        this.id=id;
        this.menuOption=menuOption;
    }
    public int getId()
    {
        return id;
    }
    public String getMenuOption()
    {
        return menuOption;
    }
    //returns the array conteining the constants of this enum type
    public static Menu convertToOption(int number) { 
        if(number >= values().length || number < 0)
            throw new NoSuchElementException();
        return values()[number];
    }
    @Override
    public String toString()
    {
        return id+". "+menuOption;
    }
    
}

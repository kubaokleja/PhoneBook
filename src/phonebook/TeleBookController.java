
package phonebook;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.Scanner;



//class to interraction with user , class shows you menu and allows choose some option
public class TeleBookController {
    
    
    private TeleBook teleBook; 
    private Scanner input = new Scanner(System.in);
    public TeleBookController() throws IOException {
        //read telebook from file
        teleBook = SaveReadFile.read();
    }
    //main loop of program
    public void application() throws IOException
    {
        do{
            printMenu();
            try{
            Menu menu = chooseOption();
            doOption(menu);
            clrscr(); 
              } catch(NoSuchElementException e) {
            System.out.println("Incorrect Option");
        }
        }while(true);
    }
    private void printMenu()
    {
          System.out.println("Options --->");
          System.out.println(Menu.ADD.toString());
          System.out.println(Menu.SEARCH_NAME.toString());
          System.out.println(Menu.SEARCH_NUMBER.toString());
          System.out.println(Menu.REMOVE.toString());
          System.out.println(Menu.PRINT.toString());
          System.out.println(Menu.CLOSE.toString());
    }
    private Menu chooseOption()
    {   
        
        int option = input.nextInt()-1;
        return Menu.convertToOption(option);      
    }
    //service chosen option
    private void doOption(Menu menu) throws IOException
    {
        switch (menu)
        {
            case ADD:
                addContact();
                break;
            case REMOVE:
                removeContact();
                break;
            case SEARCH_NUMBER:
                searchByTelephone();
                break;
            case SEARCH_NAME:
                searchByName();
                break;
            case PRINT: 
                System.out.println(teleBook);
                break;
            case CLOSE:
                try{
                SaveReadFile.save(teleBook);
                System.out.println("Zapisano zmiany.");
                }
                catch(IOException e){
                    System.err.println("Nie udało się zapisać");
                }
                System.out.println("Bye bye.");
                System.exit(0);
            default :
                System.out.println("You did something wrong.. ");
            
        }
    } 
    //adding contact
    private void addContact() {
        try{
        System.out.println("Name: ");
        String name=input.next();
        System.out.println("Number: ");
        String phoneNumber=input.next();
        boolean removed = teleBook.addNewContact(name,phoneNumber);
        if(removed)
        {
            System.out.println("Added correctly!");
        }
        }
        catch(InputMismatchException e)
        {
            e.getMessage();
        }
        
        
    }
    //removing contact
    private void removeContact() {
        try{
        System.out.println("Name: ");
        String name=input.next();
        boolean removed = teleBook.deleteContact(name);
        if(removed)
        {
            System.out.println("Removed correctly!");
        }
        
        }
        catch(InputMismatchException e)
        {
            e.getMessage();
        }
        
    }
    private void searchByTelephone() {
        try{
        System.out.println("What number do I have to look for?");
        String str=input.next();
        List <Contact> foundedNames = teleBook.searchByNumber(str);
        System.out.println(foundedNames);
        }
        catch(InputMismatchException e)
        {
            e.getMessage();
        }
        
    }
    private void searchByName() {
        try{
        System.out.println("What name do I have to look for?");
        String str=input.next();
        List <Contact> foundedNames = teleBook.searchByName(str);
        System.out.println(foundedNames);
        }
        catch(InputMismatchException e)
        {
            e.getMessage();
        }
    }

   public static void clrscr(){
    //Clears Screen in java (more trying than doing)
    try {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    } catch (IOException | InterruptedException ex) {}
}
   
    
}

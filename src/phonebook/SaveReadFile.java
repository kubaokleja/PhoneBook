/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author kubaokleja
 */
//file service , read phonebook from file and save to file updated contacts
public class SaveReadFile {
   private static final String FILE_NAME = "telebook.csv";

    public static void save(TeleBook teleBook) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        //iterator implemented in telebook class makes me possible to use for each loop
        for (Contact contact : teleBook) {
            writer.write(contact.getName()+","+contact.getNumber());
            writer.newLine();
        }
        writer.close();
    }

    public static TeleBook read() {
        TeleBook book = null;
        try {
           BufferedReader buffReader = new BufferedReader(new FileReader(FILE_NAME));
            Map<String, Contact> contacts = buffReader.lines() //return stream with lines of file
                    .map(line -> line.split(",")) //transform to array
                    .map(split -> new Contact(split[0], split[1])) //transform to Contact object
                    .collect(Collectors.toMap(Contact::getName, Function.identity())); //add objects to map
            book = new TeleBook(new TreeMap<>(contacts));
        } catch (FileNotFoundException e) {
            //ignore, just create empty TeleBook
        }
        return book != null? book : new TeleBook(); // if file is empty create new TeleBook :) 
    }
}

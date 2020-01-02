package main;

import java.util.Scanner;
import java.io.*;

/**
 * UserInput.java
 * Class for getting input from the user
 * @author Team7
 */
public class UserInput {

	InputStream input_stream;
    /**
     * Default constructor does nothing
     */
    public UserInput() {
    	this.input_stream = System.in;
    }
    
    public UserInput(InputStream input_stream) {
    	this.input_stream = input_stream;
    }

    /**
     * Gets the name of the election file
     * @return a valid fileName if file is valid; null otherwise
     */
    public String getFileName() {
        Scanner stdin = new Scanner(this.input_stream);  // Create a Scanner object
        System.out.println("Enter Election File Name: ");
        String fileName = stdin.nextLine();  // Read user input
        FileNameValidator fileNameValidator = new FileNameValidator();
        if (!fileNameValidator.validate(fileName)) {
            System.out.println("Invalid Election File Name.");
            fileName = null;
        }
        stdin.close();
        return fileName;
    }
}

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ElectionFile.java
 * A class that allows for reading of election files
 * @author Team7
 */
public class ElectionFile {

    private String file_name;
    private File election_file;
    private Scanner scanner;
    private ElectionType election_type;

    /**
     *
     * @param file_name name of the election file it's assumed to be valid
     * Constructor for class
     */
    public ElectionFile(String file_name) {
        this.file_name = file_name;
        this.election_file = new File(this.file_name);
    }

    /**
     * Reads the files first line to set the election type
     */
    public void initialize() {
        // should never happen as file has been validated
        try {
            scanner = new Scanner(election_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        election_type = ElectionType.valueOf(scanner.nextLine());
    }

    /**
     *
     * @return\ The election type election OPL or CPL
     */
    public ElectionType getElectionFileType() {
        return election_type;
    }

    /**
     *
     * @return If the file has another line that has not been read
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     *
     * @return the value of the next line or in the case of there being no more lines null
     */
    public String getNextLine() {
        return scanner.nextLine();
    }
    
    public int getNextInt() {
    	return (int) Integer.parseInt(getNextLine());
    }
}

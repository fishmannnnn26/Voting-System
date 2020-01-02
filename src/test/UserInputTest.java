package test;

import main.UserInput;
import org.junit.Test;
import static org.junit.Assert.*;
import mock.TestFiles;

import java.io.*;

/**
 * Class for testing the user input class
 */
public class UserInputTest {

    /**
     * Tests if a valid filename is entered it will be returned
     */
    @Test
    public void getFileNameOnValidFileName() {
        ByteArrayInputStream stream = new ByteArrayInputStream(TestFiles.OPL_ELECTION_TYPE_TEST_FILE.getBytes());
        UserInput userInput = new UserInput(stream);
        assertEquals(TestFiles.OPL_ELECTION_TYPE_TEST_FILE, userInput.getFileName());
    }
    
    /**
     * Tests if an invalid filename is entered it will return null
     */
    @Test
    public void getNullOnInvalidFileName() {
        ByteArrayInputStream stream = new ByteArrayInputStream(TestFiles.TESTING_DIRECTORY.getBytes());
        UserInput userInput = new UserInput(stream);
        assertNull(userInput.getFileName());
    }
}
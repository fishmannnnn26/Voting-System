package test;

import main.FileNameValidator;
import mock.TestFiles;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Junit test class for the FileNameValidator class
 */

public class FileNameValidatorTest {

	FileNameValidator validator;

	public FileNameValidatorTest() {
		validator = new FileNameValidator();
	}

    /**
     * Checks if null file returns false
     */
    @Test
    public void nullFileShouldReturnFalse() {
        assertFalse(validator.validate(null));
    }

    /**
     * Checks if empty filename returns false
     */
    @Test
    public void emptyFileNameShouldReturnFalse() {
        assertFalse(validator.validate(""));
    }

    /**
     * Checks if non keyboard filename returns false
     */
    @Test
    public void nonKeyboardFileNameShouldReturnFalse() {
        assertFalse(validator.validate("\n"));
    }

    /**
     * Checks if a non existent file returns false
     */
    @Test
    public void testIfNonExistentFileReturnsFalse() {
        assertFalse(validator.validate("I do not exist"));
    }

    /**
     * Checks if existent file returns true
     */
    @Test
    public void existentFileShouldReturnTrue() {
        assertTrue(validator.validate(TestFiles.SMALL_CPL));
    }

    /**
     * Checks if directory returns false
     */
    @Test
    public void existentDirectoryReturnsFalse() {
        assertFalse(validator.validate(TestFiles.TESTING_DIRECTORY));
    }
}
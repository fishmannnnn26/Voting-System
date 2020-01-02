package main;

import java.io.File;
/**
 * FileNameValidator.java
 * Class for validating files existence
 * @author Team7
 */

public class FileNameValidator {

    /**
     * Default constructor dose nothing
     */
    public FileNameValidator() {

    }

    /**
     *
     * @param file_name the name file of the file being validated
     * @return true if file exists false if it dose nor
     */
    public boolean validate(String file_name) {
        return (validateString(file_name) && validateFilePath(file_name));
    }

    /**
     *
     * @param file_name the name file of the file being validated
     * @return true if file_name is at least length 1 and has only valid chars false otherwise
     */
    private boolean validateString(String file_name) {
        if(file_name == null) {
            return false;
        }

        if(file_name.length() < 1) {
            return false;
        }

        for (int i = 0; i < file_name.length() ; i++) {
            int ascii = file_name.charAt(i);

            if (ascii < 32 || 126 < ascii) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param file_name the name file of the file being validated
     * @return true if file exists on system and is a File false otherwise
     */
    private boolean validateFilePath(String file_name) {
        File file = new File(file_name);
        return file.isFile();
    }
}

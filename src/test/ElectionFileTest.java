package test;

import main.ElectionFile;
import main.ElectionType;
import mock.TestFiles;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for testing the ElectionFile class
 */
public class ElectionFileTest {
	public static final String OPL_FILE = "testing/OPLTestFile.csv";
	public static final String CPL_FILE = "testing/CPLTestFile.csv";
	public static final String OPL_SMALL = "testing/smallOPL.csv";
	public static final String CPL_SMALL = "testing/smallCPL.csv";
	
    /**
     * Test if class can detect a OPL file
     */
    @Test
    public void testGetFileTypeForOPLElectionFileIsOPL() {
        ElectionFile opl_election_file = getOPLElectionFile();
        assertEquals(opl_election_file.getElectionFileType(), ElectionType.OPL);
    }

    /**
     * Test if class can detect a CPL file
     */
    @Test
    public void testGetFileTypeForCPLElectionFileIsCPL() {
        ElectionFile cpl_election_file = getCPLElectionFile();
        assertEquals(cpl_election_file.getElectionFileType(), ElectionType.CPL);
    }
    
    /**
     * Test if, when there is a next line, has next line is true
     */
    @Test
    public void testHasNextLineIsTrueWhenNextLine() {
        ElectionFile election_file = getElectionFile(TestFiles.SMALL_CPL);
        assertTrue(election_file.hasNextLine());
    }

    /**
     * Test if class returns correct value for hasNextLine when there is not one
     */
    @Test
    public void testHasNextLineIsFalseWhenNoNextLine() {
        ElectionFile election_file = getCPLElectionFile();
        assertFalse(election_file.hasNextLine());
    }

    /**
     * Test if class returns the nextLine when there is one
     */
    @Test
    public void testGetNextLine() {
        ElectionFile election_file = getElectionFile(TestFiles.OPL_TWO_LINES);
        assertEquals(election_file.getNextLine(), "This is the second line.");
    }

    private ElectionFile getOPLElectionFile() {
    	return getElectionFile(TestFiles.OPL_ELECTION_TYPE_TEST_FILE);
    }

    private ElectionFile getCPLElectionFile() {
    	return getElectionFile(TestFiles.CPL_ELECTION_TYPE_TEST_FILE);
    }

    public static ElectionFile getElectionFile(String file_name) {
    	ElectionFile election_file = new ElectionFile(file_name);
    	election_file.initialize();
    	return election_file;
    }
}
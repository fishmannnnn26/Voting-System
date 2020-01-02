package test;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import main.Audit;
import main.Teller;
import mock.TestFiles;

/**
 * Class for testing the Audit class
 */
public class AuditTest {
    @Test
	public void testWriteTellerTestWilthOPLTeller() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException{
    	testTeller(ElectionFileParserTest.getOPLTeller(), TestFiles.SMALL_OPL_TELLER);
	}
    
    @Test
	public void testWriteTellerTestWilthCPLTeller() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException{
    	testTeller(ElectionFileParserTest.getCPLTeller(), TestFiles.SMALL_CPL_TELLER);
	}

    private void testTeller(Teller teller, String file_name) throws FileNotFoundException, IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		Audit audit = new Audit();
		audit.initialize(output);
    	audit.writeTeller(teller);
    	String found_content = output.toString();
    	String desired_content = new String(Files.readAllBytes(Paths.get(file_name)));
    	assertTrue(found_content.contentEquals(desired_content));
    }
    
    @Test
    public void testWriteBallotWithWinner() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		Audit audit = new Audit();
		audit.initialize(output);
		String ballot = ",,,,,1,,";
		String winner = "Bob";
		audit.writeBallotWithWinner(ballot, winner);
		String found = output.toString();
		String desired = ",,,,,1,,  (Bob)\n";
		assertTrue(  found.contentEquals(desired)  );
    }
}
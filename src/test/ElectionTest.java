package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import main.Election;
import mock.TestFiles;

/**
 * Class for testing the Audit class
 */
public class ElectionTest {
    @Test
	public void testWriteTellerTestWilthOPLTeller() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException{
    	Election.runElection(TestFiles.SMALL_OPL);
	}
}
package test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import main.Audit;
import main.CPLElectionFileParser;
import main.CPLTeller;
import main.ElectionFile;
import main.ElectionFileParser;
import main.OPLElectionFileParser;
import main.OPLTeller;
import main.Teller;
import mock.TestFiles;

/**
 * Class for testing the ElectionFile class
 */
public class ElectionFileParserTest {

	@Test
	public void testGetParserOnOPLElectionReturnsOPLElectionFileParser() {
    	assertThat(getOPLElectionFileParser(), instanceOf(OPLElectionFileParser.class));
	}

	@Test
	public void testGetParserOnCPLElectionReturnsCPLElectionFileParser() {
    	assertThat(getCPLElectionFileParser(), instanceOf(CPLElectionFileParser.class));
	}

	@Test
	public void testGetTellerOfOPLElectionFileParser() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertThat(getOPLTeller(), instanceOf(OPLTeller.class));
	}

	@Test
	public void testGetTellerOfCPLElectionFileParser() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertThat(getCPLTeller(), instanceOf(CPLTeller.class));
	}
	
	@Test
	public void testParseOfOPLElectionFileParser() throws IOException {
		boolean b = doParseTest(getOPLElectionFileParser(), TestFiles.SMALL_OPL_AUDIT);
		assertTrue(b);
	}
	
	@Test
	public void testParseOfCPLElectionFileParser() throws IOException {
		boolean b = doParseTest(getCPLElectionFileParser(), TestFiles.SMALL_CPL_AUDIT);
		assertTrue(b);
	}
	
	private boolean doParseTest(ElectionFileParser parser, String file_name) throws IOException {
		Audit audit = new Audit();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		audit.initialize(output);
		parser.parse(audit);
		String desired = output.toString();
		String found = new String(Files.readAllBytes(Paths.get(file_name)));
		return found.contentEquals(desired);
	}
	
	public static Teller getOPLTeller() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getTeller(getOPLElectionFileParser());
	}

	public static OPLElectionFileParser getOPLElectionFileParser() {
		return (OPLElectionFileParser) getElectionFileParser(TestFiles.SMALL_OPL);
	}

	public static Teller getCPLTeller() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getTeller(getCPLElectionFileParser());
	}

	public static CPLElectionFileParser getCPLElectionFileParser() {
		return (CPLElectionFileParser) getElectionFileParser(TestFiles.SMALL_CPL);
	}

	public static ElectionFileParser getElectionFileParser(String file_name) {
    	ElectionFile election_file = ElectionFileTest.getElectionFile(file_name);
    	ElectionFileParser parser = ElectionFileParser.getParser(election_file);
    	return parser;
	}

	public static Teller getTeller(ElectionFileParser parser) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = ElectionFileParser.class.getDeclaredMethod("parseTeller");
		method.setAccessible(true);
		Teller teller = (Teller) method.invoke(parser);
		return teller;
	}
}

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Test;

import main.ElectionFile;
import main.Teller;
import mock.TestFiles;
import mock.TestResults;

public class TellerTest {
	@Test
	public void testCPLTellerToString() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Teller teller = ElectionFileParserTest.getCPLTeller();
		String desired = getCPLTellerString();
		String found = teller.toString();
		assertTrue(  desired.contentEquals(found)  );
	}
	
	@Test
	public void testOPLTellerToString() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Teller teller = ElectionFileParserTest.getOPLTeller();
		String found = teller.toString();
		String desired = getOPLTellerString();
		assertTrue(  desired.contentEquals(found)  );
	}

	private String getCPLTellerString() throws IOException {
		return getTellerString(TestFiles.SMALL_CPL_TELLER);
	}

	private String getOPLTellerString() throws IOException {
		return getTellerString(TestFiles.SMALL_OPL_TELLER);
	}

	private String getTellerString(String file_name) throws IOException {
	    String content = new String(Files.readAllBytes(Paths.get(file_name)));
	    return content;
	}
	
	@Test
	public void testOPLTellerTallyBallotTalliesCorrectly() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		ElectionFile election_file = ElectionFileTest.getElectionFile(TestFiles.SMALL_OPL);
		Teller teller = ElectionFileParserTest.getOPLTeller();
		Map<String, Integer> tallies = doTallies(election_file, 1, teller);
		assertTrue(  tallies.equals((Map<String, Integer>)TestResults.SMALL_OPL_TALLIES)  );
	}
	
	@Test
	public void testCPLTellerTallyBallotTalliesCorrectly() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		ElectionFile election_file = ElectionFileTest.getElectionFile(TestFiles.SMALL_CPL);
		Teller teller = ElectionFileParserTest.getCPLTeller();
		Map<String, Integer> tallies = doTallies(election_file, 3, teller);
		assertTrue(  tallies.equals((Map<String, Integer>)TestResults.SMALL_CPL_TALLIES)  );
	}
	
	@Test
	public void testOPLTellerGetPartyTallies() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = doOPLTallies();
		Map<String, Integer> found = teller.getPartyTallies();
		Map<String, Integer> desired = TestResults.SMALL_OPL_PARTY_TALLIES;
		assertTrue(  found.equals(desired)  );
	}
	
	@Test
	public void testCPLTellerGetPartyTallies() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = doCPLTallies();
		Map<String, Integer> found = teller.getPartyTallies();
		Map<String, Integer> desired = TestResults.SMALL_CPL_PARTY_TALLIES;
		assertTrue(  found.equals(desired)  );
	}
	
	@Test
	public void testOPLTellerSeatCount() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = doOPLTallies();
		int desired = 5;
		assertEquals(desired, teller.getSeatCount());
	}
	
	@Test
	public void testCPLTellerSeatCount() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = doCPLTallies();
		int desired = 5;
		assertEquals(desired, teller.getSeatCount());
	}
	
	@Test
	public void testCPLTellerGetPartyCandidates() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = doCPLTallies();
		assertTrue(teller.getPartyCandidates().equals(TestResults.SMALL_CPL_PARTY_CANDIDATES));
	}

	@Test
	public void testOPLTellerGetPartyCandidates() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = doOPLTallies();
		assertTrue(teller.getPartyCandidates().equals(TestResults.SMALL_OPL_PARTY_CANDIDATES));
	}

	public static Teller doOPLTallies() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		ElectionFile election_file = ElectionFileTest.getElectionFile(TestFiles.SMALL_OPL);
		Teller teller = ElectionFileParserTest.getOPLTeller();
		doTallies(election_file, 1, teller);
		return teller;
	}

	public static Teller doCPLTallies() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		ElectionFile election_file = ElectionFileTest.getElectionFile(TestFiles.SMALL_CPL);
		Teller teller = ElectionFileParserTest.getCPLTeller();
		doTallies(election_file, 3, teller);
		return teller;
	}

	public static Map<String, Integer> doTallies(ElectionFile election_file, int skip, Teller teller) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		int B = fastForward(election_file, skip);
		for (int i = 0; i < B; i++)
			teller.tallyBallot(election_file.getNextLine());
		Map<String, Integer> tallies = getTallies(teller);
		return tallies;
	}
	
	public static int fastForward(ElectionFile election_file, int skip) {
		for (int i = 0; i < skip; i++)
			election_file.getNextLine();
		int B = election_file.getNextInt();
		int C = election_file.getNextInt();
		for (int i = 0; i < C; i++)
			election_file.getNextLine();
		return B;
	}

	public static Map<String, Integer> getTallies(Teller teller) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = Teller.class.getDeclaredField("tallies");
		field.setAccessible(true);
		Map<String, Integer> tallies = (Map<String, Integer>) field.get(teller);
		return tallies;
	}
}

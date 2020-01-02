package main;

import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Audit.java
 * Represents an audit file used to display detailed information about the election
 * @author team 7
 *
 */
public class Audit {
	/**
	 * audit file name and writers to print to audit file
	 */
	private FileWriter file;
	private PrintWriter writer;
	private String file_name;
	
	/**
	 * default constructor
	 */
	public Audit() {}
	
	/**
	 * constructor with audit file name
	 * @param file_name name of new audit file that will be created. can also be existing audit file
	 */
	public Audit(String file_name) {
		this.file_name = file_name;
	}
	
	/**
	 * creates the writers necessary to output to the specified audit file
	 * @throws Exception if file or writer aren't created
	 */
	public void initialize() throws Exception {
		file = new FileWriter(file_name);
		writer = new PrintWriter(file);
	}
	
	/**
	 * creates a writer from an existing output stream
	 * @param out
	 */
	public void initialize(OutputStream out) {
		writer = new PrintWriter(out);
	}

	/**
	 * prints the teller information to the audit file
	 * general information about the election
	 * @param teller Teller object. Either CPL or OPL teller
	 */
	public void writeTeller(Teller teller) {
		writer.print(teller.toString());
		writer.flush();
	}
	
	/**
	 * prints individual ballot information to the audit file
	 * @param ballot string indicating which candidate was voted for. format: ,,,1,,,
	 * @param winner candidate name that was voted for
	 */
	public void writeBallotWithWinner(String ballot, String winner) {
		writer.println(ballot + "  (" + winner + ")");
		writer.flush();
	}
	
	public void writeResults(String results) {
		writer.print("\n***Results***\n");
		writer.print(results);
		writer.flush();
	}
}

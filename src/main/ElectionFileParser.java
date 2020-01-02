package main;

/*
 * ElectionFileParser.java
 * This class its an abstract class that will parse an election file 
 * @author Team 7
 */
public abstract class ElectionFileParser {
	ElectionFile election_file;
	
	/**
	 * gets the right election file parser according to the type of election file
	 * @param file election file object
	 * @return specific type of file parser
	 */
	public static ElectionFileParser getParser(ElectionFile file) {
		switch (file.getElectionFileType()) {
		case OPL:
			return new OPLElectionFileParser(file);
		case CPL:
			return new CPLElectionFileParser(file);
		}
		return null;
	}
	
	/**
	 * sets the election file
	 * @param election_file
	 */
	public ElectionFileParser(ElectionFile election_file) {
		this.election_file = election_file;
	};
	
	/**
	 * 
	 * @param audit audit file that information is printed to
	 * @return teller teller object
	 */
	public Teller parse(Audit audit) {
		Teller teller = parseTeller();
		audit.writeTeller(teller);
		for (int i = 0; i < teller.getBallotCount() && election_file.hasNextLine(); i++) {
			String ballot = election_file.getNextLine();
			String winner = teller.tallyBallot(ballot);
			audit.writeBallotWithWinner(ballot, winner);
		}
		return teller;
	};
	
	protected abstract Teller parseTeller();
}

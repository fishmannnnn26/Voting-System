package main;

import java.util.ArrayList;

/**
 * OPLElectionFileParser.java
 * Class for Parsing an OPL election file
 * @author Team7
 */
public class OPLElectionFileParser extends ElectionFileParser {

	/**
	 * Calls the parent Constructor
	 * @param election_file the OPL election file
	 */
	public OPLElectionFileParser(ElectionFile election_file) {
		super(election_file);
	}

	/**
	 *
	 * @return an OPL teller object for the parsed election information
	 */
	@Override
	protected Teller parseTeller() {
		int seat_count = election_file.getNextInt();
		int ballot_count = election_file.getNextInt();
		int candidate_count = election_file.getNextInt();
		OPLCandidate[] candidates = new OPLCandidate[candidate_count];
		java.util.ArrayList<String> parties = new ArrayList<>();
		int i = 0;
		while (election_file.hasNextLine() && i < candidate_count) {
			String next_line = election_file.getNextLine();
			if (next_line.trim().isEmpty())
				continue;
			next_line = next_line.substring(1, next_line.length() - 1);
			String[] parts = next_line.split(",");
			String name = parts[0];
			String party = parts[1];
			if (!parties.contains(party)) {
				parties.add(party);
			}
			OPLCandidate candidate = new OPLCandidate(name, party);
			candidates[i++] = candidate;
		}
		
		int party_count = parties.size();
		String[] party_array = new String[party_count];
		for (i = 0; i < party_count; i++)
			party_array[i] = parties.get(i);
		Teller teller = new OPLTeller(party_count, party_array, seat_count,
							 ballot_count, candidate_count, candidates);
		return teller;
	}
}

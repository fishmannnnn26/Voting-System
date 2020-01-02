package main;

import java.util.ArrayList;

/**
 * CPLElectionFileParser.java
 * election file parser specific to CPL elections
 * @author team 7
 *
 */
public class CPLElectionFileParser extends ElectionFileParser {

	public CPLElectionFileParser(ElectionFile election_file) {
		super(election_file);
	}
	
	/**
	 * Teller that parses through the file and collects the information
	 * @return teller CPL teller that holds the parsed information
	 */
	@Override
	protected Teller parseTeller() {
		int party_count = election_file.getNextInt();
		String party_from_file = election_file.getNextLine();
		int seat_count = election_file.getNextInt();
		int ballot_count = election_file.getNextInt();
		int candidate_count = election_file.getNextInt();
		
		CPLCandidate[] candidates = new CPLCandidate[candidate_count];
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
			Integer rank = Integer.valueOf(parts[2]);
			
			if (!parties.contains(party)) {
				parties.add(party);
			}
			
			CPLCandidate candidate = new CPLCandidate(name, party, rank);
			candidates[i++] = candidate;
		}
		
		String parties_actual = party_from_file.substring(1, party_from_file.length()-1);
		String[] party_array = parties_actual.split(",");
		
		Teller teller = new CPLTeller(party_count, party_array, seat_count,
				 ballot_count, candidate_count, candidates);
		return teller;
	}

}

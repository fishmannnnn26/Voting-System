package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * CPLTeller.java
 * CPL teller that holds CPL election information
 * @author team 7
 *
 */
public class CPLTeller extends Teller{
	private int party_count;
	private int seat_count;
	private int ballot_count;
	private int candidate_count;
	private CPLCandidate[] candidates;
	private String[] parties;
	private Map<String, String> candidate2party;

	/**
	 * constructor
	 * @param party_count number of parties
	 * @param parties string array of parties (names)
	 * @param seat_count number of seats
	 * @param ballot_count total number of ballots
	 * @param candidate_count number of candidates
	 * @param candidates string array of candidates (names)
	 */
	public CPLTeller(int party_count, String[] parties, int seat_count,
			  int ballot_count, int candidate_count, CPLCandidate[] candidates) {
		super();
		this.party_count = party_count;
		this.parties = parties;
		this.seat_count = seat_count;
		this.ballot_count = ballot_count;
		this.candidate_count = candidate_count;
		this.candidates = candidates;
		this.candidate2party = new HashMap<String, String>();
		for (CPLCandidate candidate : candidates)
			this.candidate2party.put(candidate.name, candidate.party);
	}
	
	/**
	 * getter
	 * @return total number of ballots in election 
	 */
	@Override
	public int getBallotCount() {
		// TODO Auto-generated method stub
		return this.ballot_count;
	}

	/**
	 * converts the parsed information to a string
	 * @return formatted string containing all general information of the election
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Party count: " + this.party_count + "\n");
		builder.append("Parties: " + String.join(",", this.parties) + "\n");
		builder.append("Seat Count: " + this.seat_count + "\n");
		builder.append("Ballot Count: " + this.ballot_count + "\n");
		builder.append("Candidate Count: " + this.candidate_count+ "\n");
		builder.append("\n");
		builder.append("Candidates:\n");
		for (CPLCandidate candidate : this.candidates) {
			builder.append(candidate.name + ", " + candidate.party + ", " + candidate.rank + "\n");
		}
		return builder.toString();
	}
	
	/**
	 * assigns ballot counts to the candidates
	 * @param ballot a line in the election file, contains the candidate that the ballot is voting for
	 * @return name name of candidate
	 */
	@Override
	public String tallyBallot(String ballot) {
		String[] parts = ballot.split(",");
		for (int i = 0; i < parts.length; i++) {
			if (!parts[i].trim().isEmpty()) {
				CPLCandidate candidate = this.candidates[i];
				String name = candidate.name;
				Integer count = this.tallies.containsKey(name)? this.tallies.get(name): 0;
				this.tallies.put(name, count + 1);
				return name;
			}
		}
		return null;
	}

	/**
	 * getter
	 * @return map of parties and their ballot counts
	 */
	@Override
	public Map<String, Integer> getPartyTallies() {
		return computePartyTallies(this.candidate2party);
	}

	/**
	 * getter
	 * @return number of seats
	 */
	@Override
	public int getSeatCount() {
		return seat_count;
	}

	/**
	 * getter
	 * @return map of parties and their candidates
	 */
	@Override
	public Map<String, ArrayList<String>> getPartyCandidates() {
		return buildPartyCandidates(this.candidate2party);
	}
}

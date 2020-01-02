package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * OPLTeller.java
 * Class for calculating the total ballot counts for each candidate and party
 * @author Team7
 */
public class OPLTeller extends Teller {

	private int party_count;
	private int seat_count;
	private int ballot_count;
	private int candidate_count;
	private OPLCandidate[] candidates;
	private String[] parties;
	private Map<String, String> candidate2party;

	/**
	 * Constructor for an OPL Teller
	 * @param party_count number of parties
	 * @param parties array of parties
	 * @param seat_count number of seats
	 * @param ballot_count number of ballots
	 * @param candidate_count number of candidates
	 * @param candidates array of candidates
	 */
	public OPLTeller(int party_count, String[] parties, int seat_count,
			  int ballot_count, int candidate_count, OPLCandidate[] candidates) {
		super();
		this.party_count = party_count;
		this.parties = parties;
		this.seat_count = seat_count;
		this.ballot_count = ballot_count;
		this.candidate_count = candidate_count;
		this.candidates = candidates;
		this.candidate2party = new HashMap<String, String>();
		for (OPLCandidate candidate : candidates)
			this.candidate2party.put(candidate.name, candidate.party);
	}

	/**
	 * Getter for ballot_count
	 * @return the total number of ballots
	 */
	@Override
	public int getBallotCount() {
		return this.ballot_count;
	}

	/**
	 * Converts parsed information to a string
	 * @return A string representing data about the election
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
		for (OPLCandidate candidate : this.candidates) {
			builder.append(candidate.name + ", " + candidate.party + "\n");
		}
		return builder.toString();
	}

	/**
	 * give the ballot to the appropriate candidate, add to their count
	 * @param ballot a line in a ballot file
	 * @return name of the candidate voted for on the ballot
	 */
	@Override
	public String tallyBallot(String ballot) {
		String[] parts = ballot.split(",");
		for (int i = 0; i < parts.length; i++) {
			if (!parts[i].trim().isEmpty()) {
				OPLCandidate candidate = this.candidates[i];
				String name = candidate.name;
				Integer count = this.tallies.containsKey(name)? this.tallies.get(name): 0;
				this.tallies.put(name, count + 1);
				return name;
			}
		}
		return null;
	}

	/**
	 * Gets a Hashmap of each parties and their total votes
	 * @return a mapping of parties to the number of votes received
	 */
	@Override
	public Map<String, Integer> getPartyTallies() {
		return computePartyTallies(this.candidate2party);
	}

	/**
	 * Getter for seat_count
	 * @return the number of seats at stake in the election
	 */
	@Override
	public int getSeatCount() {
		return seat_count;
	}

	/**
	 * Getter for a list of parties and their candidates
	 * @return a hashMap of parties and their candidates
	 */
	@Override
	public Map<String, ArrayList<String>> getPartyCandidates() {
		return buildPartyCandidates(this.candidate2party);
	}
}

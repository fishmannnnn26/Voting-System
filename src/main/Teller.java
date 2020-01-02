package main;

import java.util.*;
import java.util.Map.Entry;

/**
 * Teller.java
 * Abstract class for calculating ballot totals
 * @author Team7
 */
public abstract class Teller {
	protected java.util.Map<String, Integer> tallies;

	/**
	 * Constructor for class
	 */
	Teller() {
		this.tallies = new java.util.HashMap<String, Integer>();
	}

	/**
	 * Abstract method
	 * @return the number of ballots
	 */
	abstract public int getBallotCount();

	/**
	 * Abstract method
	 * @return the number of seats
	 */
	abstract public int getSeatCount();

	/**
	 * Abstract method
	 * @return the number of votes each party got
	 */
	abstract public java.util.Map<String, Integer> getPartyTallies();

	/**
	 * Abstract method
	 * @return returns the candidates in each party
	 */
	abstract public java.util.Map<String, ArrayList<String>> getPartyCandidates();

	/**
	 * Abstract method to override default toString
	 * @return string representation
	 */
	public abstract String toString();

	/**
	 * abstract method
	 * @param ballot a line in a ballot file
	 */
	public abstract String tallyBallot(String ballot);

	/**
	 * computes the total number of votes each party receives from all party candidates
	 * @param candidate2party map of candidate and their party
	 * @return map of parties and their number of votes
	 */
	protected Map<String, Integer> computePartyTallies(Map<String, String> candidate2party) {
		Map<String, Integer> party_tallies = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : tallies.entrySet()) {
			String candidate = entry.getKey();
			String party = candidate2party.get(candidate);
			int tally = party_tallies.containsKey(party)? party_tallies.get(party): 0;
			tally += this.tallies.get(candidate);
			party_tallies.put(party, tally);
		}
		return party_tallies;
	}

	/**
	 * creates a map of parties and a list of their candidates by parsing through maps of candidates and parties
	 * @param candidate2party map of candidate and their party
	 * @return party2candidates map of parties and a list of their candidates 
	 */
	public Map<String, ArrayList<String>> buildPartyCandidates(Map<String, String> candidate2party) {
		Map<String, ArrayList<String>> party2candidates = new HashMap<String, ArrayList<String>>();
		ArrayList<Entry<String, Integer>> entries = getSortedEntries();
		for (Entry<String, Integer> entry : entries) {
			String candidate = entry.getKey();
			String party = candidate2party.get(candidate);
			ArrayList<String> candidates = party2candidates.containsKey(party)? party2candidates.get(party): new ArrayList<String>();
			candidates.add(candidate);
			party2candidates.put(party, candidates);
		}
		return party2candidates;
	}

	/**
	 * creates a list of all ballot entries and sorts them 
	 * @return sorted array list of entries containing candidate names and votes
	 */
	protected ArrayList<Entry<String, Integer>> getSortedEntries() {
		ArrayList<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>();
		for (Entry<String, Integer> entry : tallies.entrySet())
			entries.add(entry);

        Comparator<Entry<String, Integer>> comparator = new Comparator<Entry<String,Integer>>() {
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                int v1 = e1.getValue();
                int v2 = e2.getValue();
                return v2 - v1;
            }
        };
        Collections.sort(entries, comparator); 
        return entries;
	}
}

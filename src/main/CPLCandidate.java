package main;

/**
 * CPLCandidate.java
 * Represents a CPL candidate and its information
 * @author team 7
 *
 */
public class CPLCandidate {
	String name;
	String party;
	Integer rank;
	/**
	 * constructor to create  CPL candidate
	 * @param name candidate name
	 * @param party candidate party
	 * @param rank candidate rank within the party
	 */
	public CPLCandidate(String name, String party, Integer rank) {
		this.name = name;
		this.party = party;
		this.rank = rank;
	}
}

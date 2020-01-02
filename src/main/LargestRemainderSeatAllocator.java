package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.util.Pair;

/**
 * LargestRemainderSeatAllocator.java
 * Allocates the seats given the ballot counts
 * @author Team 7
 *
 */
public class LargestRemainderSeatAllocator extends SeatAllocator {
	private Map<String, Integer> seats_first_round;
	private Map<String, Integer> seats_second_round;
	private Map<String, LinkedList<String>> placement;
	final private int ballot_count;
	final private int original_seat_count;
	private int seat_count;
	private int quota;
	private Map<String, Integer> tallies;
	private Map<String, ArrayList<String>> candidates;
	
	/**
	 * constructor
	 * @param teller teller with the necessary election information
	 */
	public LargestRemainderSeatAllocator(Teller teller) {
		super();
		this.ballot_count = teller.getBallotCount();
		this.seat_count = teller.getSeatCount();
		this.original_seat_count = this.seat_count;
		this.tallies = teller.getPartyTallies();
		this.candidates = teller.getPartyCandidates();
		this.quota = this.ballot_count/this.seat_count;
		this.seats_first_round = new HashMap<String, Integer>();
		this.seats_second_round = new HashMap<String, Integer>();
		this.placement = new HashMap<String, LinkedList<String>>();
	}

	/**
	 * general method to call both rounds of seat allocation
	 */
	@Override
	public void allocate() {
		allocateFirstRound();
		allocateSecondRound();
		assignSeats();
	}
	
	// *************** Getter *********************** // 
	public  Map<String, Integer> get_seats_first_round() {
		return this.seats_first_round;
	}
	public  Map<String, Integer> get_seats_second_round() {
		return this.seats_second_round;
	}
	
	public int getBallotCount() {
		return this.ballot_count;
	}
	
	public int getSeatCount() {
		return this.original_seat_count;
	}

	public int getQuota() {
		return this.quota;
	}
	
	public Map<String, ArrayList<String>> getCandidate() {
		return this.candidates;
	}
	
	public Map<String, LinkedList<String>> getPlacement() {
		return this.placement;
	}

	public Map<String, Integer> getTallies() {
		return this.tallies;
	}
	// ************************************************** // 
	private void allocateFirstRound() {
		for (Entry<String, Integer> entry : this.tallies.entrySet()) {
			String party = entry.getKey();
			Integer tally = entry.getValue();
			int won_seats = tally/this.quota;
			this.seats_first_round.put(party, won_seats);
			this.seat_count -= won_seats;
		}
	}
	/**
	 * allocates the remaining sets of seats to the rest of the candidates/parties
	 */
	private void allocateSecondRound() {
		// Build sorted list of <party, remainder> pairs, sorted by remainder descending
		LinkedList<Pair<String, Integer>> entries = new LinkedList<Pair<String, Integer>>();
		for (Entry<String, Integer> entry : this.tallies.entrySet()) {
			String party = entry.getKey();
			Integer tally = entry.getValue();
			int remainder = tally % this.quota;
			entries.add(new Pair<String, Integer>(party, remainder));
		}
		
        Comparator<Pair<String, Integer>> comparator = new Comparator<Pair<String,Integer>>() {
            @Override
            public int compare(Pair<String, Integer> e1, Pair<String, Integer> e2) {
                int v1 = e1.getValue();
                int v2 = e2.getValue();
                return v2 - v1;
            }
        };
        Collections.sort(entries, comparator);
        
        // assign seats to each party
        // for each run, find winner, assign seats, remove from run, and repeat until run is empty
        // repeat until all seats are assigned
        int i = 0;
        while (this.seat_count > 0) {
        	// cycle-back
        	if (i == entries.size())
        		i = 0;

        	// build next run
        	LinkedList<Pair<String, Integer>> run = new LinkedList<Pair<String, Integer>>();
        	run.add(entries.get(i++));
			int run_value = run.get(0).getValue();
        	while (i < entries.size()) {
        		int entry_value = entries.get(i).getValue();
        		if (run_value == entry_value)
					run.add(entries.get(i++));
        		else
        			break;
        	}

        	// allocate seats to run
        	while (this.seat_count > 0 && run.size() > 0) {
        		Pair<String, Integer> winner = getRunWinner(run);
        		String party = winner.getKey();
        		int won_seats = Math.min(winner.getValue(), this.seat_count);
        		int seats = this.seats_second_round.containsKey(party)? this.seats_second_round.get(party): 0;
        		seats += won_seats;
        		this.seats_second_round.put(party, seats);
        		this.seat_count -= won_seats;
        	}
        }
	}
	
	/**
	 * helper function for 2nd round allocation to find the winner of the remainder (run off)
	 * @param run_org list of remaining candidates/parties in the run off election
	 * @return winner of the run
	 */
	private Pair<String, Integer> getRunWinner(LinkedList<Pair<String, Integer>> run_org) {
		Pair<String, Integer> winner = null;
		
		LinkedList<Pair<String, Integer>> run = (LinkedList<Pair<String, Integer>>) run_org.clone();
		if (run.size() == 1) {
			winner = run.remove(0);
		} else {
			while (run.size() > 1) {
				Pair<String, Integer> first = run.remove(0);
				Pair<String, Integer> second = run.remove(0);
				winner = (Pair<String, Integer>) breakTie(first, second);
				run.add(0, winner);
			}
		}
		run_org.remove(winner);
		return winner;
	}
	
	/**
	 * assigns seats based on the determined seat allocation
	 */
	private void assignSeats() {
		for (String party : this.candidates.keySet()) {
			int seats = 0;
			if (this.seats_first_round.containsKey(party))
				seats += this.seats_first_round.get(party);
			if (this.seats_second_round.containsKey(party))
				seats += this.seats_second_round.get(party);
			
			ArrayList<String> candidates = this.candidates.get(party);
			if (seats > candidates.size())
				seats = candidates.size();
			this.placement.put(party, (LinkedList<String>) new LinkedList<String>(candidates.subList(0, seats)));
		}
	}
}

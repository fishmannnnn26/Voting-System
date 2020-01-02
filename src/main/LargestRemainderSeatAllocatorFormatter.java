package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 * LargestRemainderSeatAllocatorFormatter.java
 * Class to format the string of media, summary, and audit file
 * @author team 7
 *
 */

public class LargestRemainderSeatAllocatorFormatter {
	private LargestRemainderSeatAllocator allocator;
	private Map<String, Integer> tallies;
	private Map<String, Integer> seats_first_round;
	private Map<String, Integer> seats_second_round;
	private Map <String, LinkedList<String>> placement;
	private Map<String, ArrayList<String>> candidates;
	private int ballot_count;
	private int seat_count;

	/**
	 * constructor
	 * @param allocator allocates all of the information from the seat allocator
	 */
    public LargestRemainderSeatAllocatorFormatter(LargestRemainderSeatAllocator allocator) {
        this.allocator = allocator;
    	placement = allocator.getPlacement();
    	candidates = allocator.getCandidate();
    	tallies = allocator.getTallies();
    	seats_first_round = allocator.get_seats_first_round();
    	seats_second_round = allocator.get_seats_second_round();
    	ballot_count = allocator.getBallotCount();
    	seat_count = allocator.getSeatCount();
    }

    // formats the allocated seats from largest remainder approach for media file
    public String toMediaFormat() {
    	StringBuffer buffer = new StringBuffer();
        buffer.append( "Ballot Count: " + allocator.getBallotCount() + "\n" );
    	buffer.append( "Seat Count: " + seat_count + "\n" );
    	buffer.append("\nCandidates: \n");
    	for (String party : candidates.keySet())
    		buffer.append(party + ": " + candidates.get(party) + "\n");
    	buffer.append("\nSeats first round:\n");
    	for (String party : seats_first_round.keySet()) {
    		Integer seats = seats_first_round.get(party);
    		buffer.append("Party: " + party + ", Seats: " + seats + "\n");
    	}
    	
    	buffer.append("\nSeats second round:\n");
    	for (String party : seats_second_round.keySet()) {
    		Integer seats = seats_second_round.get(party);
    		buffer.append("Party: " + party + ", Seats: " + seats + "\n");
    	}
    	
    	buffer.append("\nPlacements:\n");
    	for (String party : placement.keySet()) {
    		buffer.append("Party: " + party);
    		buffer.append(", placed candidates: ");
    		LinkedList<String> candidates = placement.get(party);
    		buffer.append("[");
    		int i = 0;
    		for (String candidate : candidates) {
    			buffer.append(candidate);
    			if (i > 0)
    				buffer.append(", ");
    			i += 1;
    		}
    		buffer.append("]\n");
    	}
    	return buffer.toString();
    }


    // formats the allocated seats from largest remainder approach for summary file
    public String toSummaryFormat() {
    	StringBuffer buffer = new StringBuffer();
        buffer.append( "Ballot Count: " + allocator.getBallotCount() + "\n");
    	buffer.append( "Seat Count: " + seat_count + "\n" );
    	buffer.append("\nCandidates: \n");
    	for (String party : candidates.keySet())
    		buffer.append(party + ": " + candidates.get(party) + "\n");
    	
    	buffer.append("\nSeats:\n");
    	for (String party : seats_first_round.keySet()) {
    		Integer seats = seats_first_round.get(party);
    		if (seats_second_round.containsKey(party))
    			seats += seats_second_round.get(party);
    		buffer.append("Party: " + party + ", Seats: " + seats + "\n");
    	}

    	buffer.append("\nPlacements:\n");
    	for (String party : placement.keySet()) {
    		buffer.append("Party: " + party);
    		buffer.append(", placed candidates: ");
    		LinkedList<String> candidates = placement.get(party);
    		buffer.append("[");
    		int i = 0;
    		for (String candidate : candidates) {
    			if (i > 0)
    				buffer.append(", ");
    			buffer.append(candidate);
    			i += 1;
    		}
    		buffer.append("]\n");
    	}
    	return buffer.toString();
    }

    // formats the allocated seats from largest remainder approach for audit file
    public String toAuditFormat() {
    	return toMediaFormat();
    }

}

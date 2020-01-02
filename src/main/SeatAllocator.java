package main;

import java.security.SecureRandom;

/**
 * SeatAllocator.java
 * Parent class for Seat Allocation
 * @author Team7
 */
public abstract class SeatAllocator {

	/**
	 * Default Constructor for class
	 */
	SeatAllocator() {}

	/**
	 * abstract method to allocate seats
	 */
	public abstract void allocate();

	/**
	 * Used to break a tie between 2 candidates
	 * @param candidate1 a candidate tied with candidate2
	 * @param candidate2 a candidate tied with candidate1
	 * @return either candidate1 or candidate2 in a random fashion
	 */
	public Object breakTie(Object candidate1, Object candidate2) {
		SecureRandom random = new SecureRandom();

		if(random.nextInt(100) <= 50) {
			return candidate1;
		} else {
			return candidate2;
		}
	}
}

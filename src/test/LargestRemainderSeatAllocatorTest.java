package test;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.LargestRemainderSeatAllocator;
import main.Teller;
import mock.TestResults;

public class LargestRemainderSeatAllocatorTest {
	@Test
	public void testAllocateFirstRoundForCPL() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = TellerTest.doCPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		Map<String, Integer> seats_first_round = getSeatsFirstRound(allocator);
		assertTrue(  seats_first_round.equals(TestResults.SMALL_CPL_SEATS_FIRST_ROUND)  );
	}

	@Test
	public void testAllocateFirstRoundForOPL() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = TellerTest.doOPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		Map<String, Integer> seats_first_round = getSeatsFirstRound(allocator);
		assertTrue(  seats_first_round.equals(TestResults.SMALL_OPL_SEATS_FIRST_ROUND)  );
	}
	
	@Test
	public void testAllocateSecondRoundForCPL() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = TellerTest.doCPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		Map<String, Integer> seats_second_round = getSeatsSecondRound(allocator);
		assertTrue(  seats_second_round.equals(TestResults.SMALL_CPL_SEATS_SECOND_ROUND)  );
	}

	@Test
	public void testAllocateSecondRoundForOPL() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = TellerTest.doOPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		Map<String, Integer> seats_second_round = getSeatsSecondRound(allocator);
		assertTrue(  seats_second_round.equals(TestResults.SMALL_OPL_SEATS_SECOND_ROUND)  );
	}
	
	@Test
	public void testPlacementForOPL() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = TellerTest.doOPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		Map<String, LinkedList<String>> placement = getPlacement(allocator);
		assertTrue(  placement.equals(TestResults.SMALL_OPL_PLACEMENT)  );
	}

	@Test
	public void testPlacementForCPL() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Teller teller = TellerTest.doCPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		Map<String, LinkedList<String>> placement = getPlacement(allocator);
		assertTrue(  placement.equals(TestResults.SMALL_CPL_PLACEMENT)  );
	}
	
	public static LargestRemainderSeatAllocator getOPLSeatAllocator() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		return getSeatAllocator(TellerTest.doOPLTallies());
	}

	public static LargestRemainderSeatAllocator getCPLSeatAllocator() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		return getSeatAllocator(TellerTest.doCPLTallies());
	}

	public static LargestRemainderSeatAllocator getSeatAllocator(Teller teller) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		Map<String, LinkedList<String>> placement = getPlacement(allocator);
		return allocator;
	}

	private static Map<String, Integer> getSeatsFirstRound(LargestRemainderSeatAllocator allocator) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		return (Map<String, Integer>) doMethod(allocator, "allocateFirstRound", "seats_first_round");
	}

	private static Map<String, Integer> getSeatsSecondRound(LargestRemainderSeatAllocator allocator) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		getSeatsFirstRound(allocator);
		return (Map<String, Integer>) doMethod(allocator, "allocateSecondRound", "seats_second_round");
	}

	private static Map<String, LinkedList<String>> getPlacement(LargestRemainderSeatAllocator allocator) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		getSeatsSecondRound(allocator);
		return (Map<String, LinkedList<String>>) doMethod(allocator, "assignSeats", "placement");
	}

	private static Object doMethod(LargestRemainderSeatAllocator allocator, String method_name, String field_name) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Method method = LargestRemainderSeatAllocator.class.getDeclaredMethod(method_name);
		method.setAccessible(true);
		method.invoke(allocator);
		
		Field field = LargestRemainderSeatAllocator.class.getDeclaredField(field_name);
		field.setAccessible(true);
		return field.get(allocator);
	}
}

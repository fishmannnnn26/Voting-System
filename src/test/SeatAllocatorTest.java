package test;

import static java.lang.Math.abs;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import main.LargestRemainderSeatAllocator;
import main.SeatAllocator;
import main.Teller;

public class SeatAllocatorTest {

    @Test
    public void break_tie() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	Teller teller = ElectionFileParserTest.getCPLTeller();
        SeatAllocator seatAllocator = new LargestRemainderSeatAllocator(teller);

        int one = 0;
        int two = 0;

        for (int i = 0; i < 200; i++) {
        	String result = (String) seatAllocator.breakTie("One", "Two");
            if(result.contentEquals("One")) {
                one++;
            } else {
                two++;
            }
        }

        // needs to be within 12.5%
        assertTrue(abs(one - two) <= 25);
    }
}
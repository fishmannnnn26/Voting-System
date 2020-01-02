package test;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import main.LargestRemainderSeatAllocator;
import main.LargestRemainderSeatAllocatorFormatter;
import main.Summarizer;
import mock.TestFiles;

public class SummarizerTest {
    @Test
    public void testSummarize() throws FileNotFoundException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Summarizer summary = new Summarizer();

		ByteArrayOutputStream output1 = new ByteArrayOutputStream();
		ByteArrayOutputStream output2 = new ByteArrayOutputStream();
		summary.initialize(output1, output2);

        LargestRemainderSeatAllocator allocator = LargestRemainderSeatAllocatorTest.getOPLSeatAllocator();
        allocator.allocate();
        LargestRemainderSeatAllocatorFormatter formatter = new LargestRemainderSeatAllocatorFormatter(allocator);
    	summary.summarize(formatter);

    	String found_content1 = output1.toString();
    	String desired_content1 = new String(Files.readAllBytes(Paths.get(TestFiles.SMALL_OPL_SUMMARY)));
    	assertTrue(found_content1.contentEquals(desired_content1));

    	String found_content2 = output2.toString();
    	// TODO
    }
}
  
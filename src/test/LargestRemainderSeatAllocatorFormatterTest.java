package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import main.Audit;
import main.ElectionFile;
import main.ElectionFileParser;
import main.ElectionType;
import main.LargestRemainderSeatAllocator;
import main.LargestRemainderSeatAllocatorFormatter;
import main.Teller;
import mock.TestFiles;
import mock.TestResults;

public class LargestRemainderSeatAllocatorFormatterTest {

    @Test
    public void toMediaFormat() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
		Teller teller = TellerTest.doCPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		allocator.allocate();
        LargestRemainderSeatAllocatorFormatter formatter = new LargestRemainderSeatAllocatorFormatter(allocator);
        String desired = new String(Files.readAllBytes(Paths.get(TestFiles.SMALL_OPL_MEDIA)));
        String found = formatter.toMediaFormat();
        assertEquals(desired, found);
    }

    @Test
    public void toSummaryFormat() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
		Teller teller = TellerTest.doOPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		allocator.allocate();
        LargestRemainderSeatAllocatorFormatter formatter = new LargestRemainderSeatAllocatorFormatter(allocator);
        String desired = new String(Files.readAllBytes(Paths.get(TestFiles.SMALL_OPL_SUMMARY)));
        String found = formatter.toSummaryFormat();
        assertEquals(desired, found);
    }

    @Test
    public void toAuditFormat() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
		Teller teller = TellerTest.doCPLTallies();
		LargestRemainderSeatAllocator allocator = new LargestRemainderSeatAllocator(teller);
		allocator.allocate();
        LargestRemainderSeatAllocatorFormatter formatter = new LargestRemainderSeatAllocatorFormatter(allocator);
        String desired = new String(Files.readAllBytes(Paths.get(TestFiles.SMALL_OPL_MEDIA)));
        String found = formatter.toAuditFormat();
        assertEquals(desired, found);
    }

    private Teller getTeller() {
        ElectionFile election_file = new ElectionFile("testing/CPLParitesEqualsBallots.csv");
        election_file.initialize();
        ElectionType election_type = election_file.getElectionFileType();
        ElectionFileParser election_parser = ElectionFileParser.getParser(election_file);
        Audit audit = new Audit("audit.txt");
        try {
            audit.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Teller teller = election_parser.parse(audit);
        return teller;
    }
}
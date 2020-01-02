package main;

import java.nio.file.Paths;

/**
 * Election.java
 * Class for running an Election
 * @author Team7
 */
public class Election {

    /**
     * Runs the election
     * @param election_fn election file name
     */
	public static String runElection(String election_fn) {
		return runElection(election_fn, "audit.txt");
	}

    public static String runElection(String election_fn, String dir) {
		ElectionFile election_file = new ElectionFile(election_fn);
		election_file.initialize();

		String audit_fn = Paths.get(dir, "audit.txt").toString();
		String media_fn = Paths.get(dir, "media.txt").toString();
		String summary_fn = Paths.get(dir, "summary.txt").toString();
		Audit audit = new Audit(audit_fn);
		try {
			audit.initialize();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		ElectionFileParser election_parser = ElectionFileParser.getParser(election_file);
		Teller teller = election_parser.parse(audit);
		LargestRemainderSeatAllocator seat_allocator = new LargestRemainderSeatAllocator(teller);
		seat_allocator.allocate();

		Summarizer summarizer = new Summarizer(summary_fn, media_fn);
		try {
			summarizer.initialize();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		LargestRemainderSeatAllocatorFormatter formatter = new LargestRemainderSeatAllocatorFormatter(seat_allocator);
		summarizer.summarize(formatter);
		audit.writeResults(formatter.toAuditFormat());
		
		return formatter.toSummaryFormat();
    }
}

package mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TestResults {
	public static final Map<String, Integer> SMALL_OPL_TALLIES = new HashMap<String, Integer>() {{
		put("Ann", 7);
		put("Cat", 5);
		put("Deutsch", 11);
		put("Foster", 8);
		put("Pike", 9);
	}};
	
	public static final Map<String, Integer> SMALL_OPL_PARTY_TALLIES = new HashMap<String, Integer>() {{
		put("R", 11);
		put("C", 7);
		put("D", 17);
		put("I", 5);
	}};
	
	public static final Map<String, Integer> SMALL_CPL_TALLIES = new HashMap<String, Integer>() {{
		put("Deutsch", 8);
		put("Foster", 10);
		put("Jones", 5);
		put("Pike", 17);
	}};

	public static final Map<String, Integer> SMALL_CPL_PARTY_TALLIES = new HashMap<String, Integer>() {{
		put("R", 10);
		put("D", 17);
		put("G", 8);
		put("I", 5);
	}};

	public static final Map<String, ArrayList<String>> SMALL_CPL_PARTY_CANDIDATES = new HashMap<String, ArrayList<String>>() {{
		put("R", new ArrayList() {{ add("Foster"); }});
		put("D", new ArrayList() {{ add("Pike"); }}  );
		put("G", new ArrayList() {{ add("Deutsch"); }});
		put("I", new ArrayList() {{ add("Jones"); }});
	}};

	public static final Map<String, ArrayList<String>> SMALL_OPL_PARTY_CANDIDATES = new HashMap<String, ArrayList<String>>() {{
		put("R", new ArrayList() {{ add("Deutsch"); }});
		put("D", new ArrayList() {{ add("Pike"); add("Foster"); }}  );
		put("C", new ArrayList() {{ add("Ann"); }});
		put("I", new ArrayList() {{ add("Cat"); }});
	}};

	public static final Map<String, Integer> SMALL_CPL_SEATS_FIRST_ROUND = new HashMap<String, Integer>() {{
		put("R", 1);
		put("D", 2);
		put("G", 1);
		put("I", 0);
	}};

	public static final Map<String, Integer> SMALL_OPL_SEATS_FIRST_ROUND = new HashMap<String, Integer>() {{
		put("R", 1);
		put("D", 2);
		put("C", 0);
		put("I", 0);
	}};

	public static final Map<String, Integer> SMALL_CPL_SEATS_SECOND_ROUND = new HashMap<String, Integer>() {{
		put("I", 1);
	}};

	public static final Map<String, Integer> SMALL_OPL_SEATS_SECOND_ROUND = new HashMap<String, Integer>() {{
		put("C", 2);
	}};

	public static final Map<String, LinkedList<String>> SMALL_OPL_PLACEMENT = new HashMap<String, LinkedList<String>>() {{
		put("R", new LinkedList() {{ add("Deutsch"); }});
		put("C", new LinkedList() {{ add("Ann"); }});
		put("D", new LinkedList() {{ add("Pike"); add("Foster"); }});
		put("I", new LinkedList() {{ }});
	}};
	
	public static final Map<String, LinkedList<String>> SMALL_CPL_PLACEMENT = new HashMap<String, LinkedList<String>>() {{
		put("R", new LinkedList() {{ add("Foster"); }});
		put("D", new LinkedList() {{ add("Pike"); }});
		put("G", new LinkedList() {{ add("Deutsch"); }});
		put("I", new LinkedList() {{ add("Jones"); }});
	}};
	
	public static final String SMALL_CPL_MEDIA_FORMAT = "Total Seat Count: 40\n" + 
														"Placement: [Jones]\n" + 
														"Seat First Round: 0\n" + 
														"Seat Second Round: 1\n" + 
														"Total Seats: 1\n";
	
	public static final String SMALL_OPL_SUMMARY_FORMAT = "Total Seat Count: 40\n" + 
															"Placement: [Ann]\n" + 
															"Seat First Round: 0\n" + 
															"Seat Second Round: 2\n" + 
															"Total Seats: 2\n";
	
	public static final String CPL_PARTIES_EQUALS_BALLOTS = "Seat 1: Pike D Votes: 1\nSeat 2: Foster R Votes: 1\nSeat 3: Deutsch G Votes: 1\n Seat 4: Borg G Votes: 1\n Seat 5: Jones I Votes: 1\n Parties \nD 1 vote\n R 1 vote\nG 1 vote\nB 1 vote\nI 1 vote\n";
}

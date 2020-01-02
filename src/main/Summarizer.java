package main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.OutputStream;

/**
 * Summarizer.java
 * Class to output summary data to summary file and media
 * @author team 7
 *
 */
public class Summarizer {
	/**
	 * summary file name and writers to write to file
	 */
	private String summary_file_name;
	private FileWriter sum_file;
	private PrintWriter sum_writer;
	private String media_file_name;
	private FileWriter media_file;
	private PrintWriter media_writer;
	/**
	 * default constructor
	 */
	public Summarizer() {}
	/**
	 * constructor
	 * @param sum_file_name name of summary file to write to
	 * @param med_file_name name of media file to write to
	 */
	public Summarizer(String sum_file_name, String med_file_name){
		this.summary_file_name = sum_file_name;
		this.media_file_name = med_file_name;
	}
	
	public void initialize() throws Exception{
		sum_file = new FileWriter(summary_file_name);
		sum_writer = new PrintWriter(sum_file);
		media_file = new FileWriter(media_file_name);
		media_writer = new PrintWriter(media_file);
	}
	public void initialize(OutputStream out1, OutputStream out2) {
		sum_writer = new PrintWriter(out1);
		media_writer = new PrintWriter(out2);
	}
	/**
	 * prints formatted strings to their respective files
	 * @param formatter object containing formatted strings of the election results
	 */
	public void summarize(LargestRemainderSeatAllocatorFormatter formatter) {
		sum_writer.print(formatter.toSummaryFormat());
		sum_writer.flush();
		media_writer.print(formatter.toMediaFormat());
		media_writer.flush();
	}
	
}

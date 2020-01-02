import javax.swing.*;

import static main.FileChooser.createAndShowGUI;

/*
 * VotingSystem.java
 * It will create and show GUI called from the FileChooser.java file
 * @author Team 7
 */
public class VotingSystem  {

	public static void main(String[] args) throws Exception {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}

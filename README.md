# Voting System
<p> This is my CSCI5801 (Software Engineering) Fall 2019 Project at the University of Minnesota - Twin Cities. </p>

<p> The purpose of this project is to develop a voting system software that allows an election official to run two different types of elections (Closed Party Listing and Open Party Listing). The overall project was being construct with Agile Scrum software development cycle within a 4-week sprint. </p>
  
## How it Works
<p> To use this voting system, simply run the VotingSystem.java file under /src. </p> 

<p> It will load a GUI for the user. </p> 

<p> There will be 3 buttons user can pick: </p> 

<li><b>Choose Election File</b> button will prompt user to pick any election file to run. To run the 500,000 ballot test files, simply pick "/testing/opl_test2.csv" for OPL Type of Election and "/testing/cpl_test2.csv" for CPL Type of Election. User can also pick any other file they want to test with under "/testing" directory. </li> <br>

<li><b>Choose Results File Location</b> button will prompt user to pick a directory, where user want to store their file. In this case, user can choose /testing as a directory or any other directory to store the audit, summary, and media file. </li> <br>  

<li><b>Submit</b> button will basically run the election file and it will show the results in the GUI as well as creating a new file called "audit.txt", "summary.txt", and "media.txt" under the directory where user pick to store the result of the election file. </li> <br>
<hr>
<p> To run the next election file, simply choose another file and click submit. </p>

<p> Enjoy :) </p>

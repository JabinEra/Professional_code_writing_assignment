import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
	public static void main(String[] args) {

		// Check arguments
		if(args == null || args.length != 1){
			System.out.println("Usage: (a | r | c | +WORD | ?WORD)");
			return; //Exit early.
		}
		//Process file contents
		String fileContents = readFileContents(Constants.FILE_NAME);
		// Process user input
		if (args[0].equals(Constants.ShowAll)) {
			showAllStudents(fileContents);
		} else if (args[0].equals(Constants.ShowRandom)) {
			showRandomStudent(fileContents);
		} else if (args[0].contains(Constants.AddEntry)) {
			addEntryToFile(Constants.FILE_NAME,args[0].substring(1));
		} else if (args[0].contains(Constants.FindEntry)) {
			findEntryInFile(fileContents,args[0].substring(1));
		} else if (args[0].contains(Constants.ShowCount)) {
			showWordCount(fileContents);
		}else{
			System.out.println(Constants.InvalidArgument + args[0]);
		}
		System.out.println(Constants.DataLoadedText);
	}
	// Method to display the word count
	public static void showWordCount(String fileContents) {
		String[] words = fileContents.split(Constants.StudentEntryDelimiter);
		System.out.println(words.length + Constants.WordsFound);
	}
	// Method to find and display an entry in the file
	public static void findEntryInFile(String fileContents, String word) {
		String words[] = fileContents.split(Constants.StudentEntryDelimiter);
		boolean found = false;
		for (String w : words) {
			if (w.equals(word)) {
				System.out.println(Constants.FoundText);
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Word not found.");
		}
	}
	// Method to add an entry to the file
	public static void addEntryToFile(String filename, String word) {
		writeToFile(filename, new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date()), word);
	}
	// Method to display a random entry from the file
	public static void showRandomStudent(String fileContents) {
		String words[] = fileContents.split(Constants.StudentEntryDelimiter);
		if (words.length > 0) {
			int randomNumber = new Random().nextInt(words.length);
			System.out.println(words[randomNumber]);
		} else {
			System.out.println("No words found.");
		}
	}
	// Method to display all entries in the file
	public static void showAllStudents(String fileContents) {
		String words[] = fileContents.split(Constants.StudentEntryDelimiter);
		for (String word : words) {
			System.out.println(word);
		}
	}
	// Method to read file contents
	public static String readFileContents(String filename) {
		System.out.println(Constants.LoadingDataText);
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String fileContents = bufferedReader.readLine();
			bufferedReader.close();
			return fileContents;
		} catch (Exception e) {
			return null;
		}
	}
	// Method to write to the file
	public static void writeToFile(String filename, String finalDate, String word) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true));
			bufferedWriter.write(", " + word + "\nList last updated on " + finalDate);
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
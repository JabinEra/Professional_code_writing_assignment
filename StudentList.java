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

		String fileContents = readFileContents(Constants.FILE_NAME);

		if (args[0].equals(Constants.ShowAll)) {
			showAll(fileContents);
		} else if (args[0].equals(Constants.ShowRandom)) {
			showRandom(fileContents);
		} else if (args[0].contains(Constants.AddEntry)) {
			addEntry(Constants.FILE_NAME,args[0].substring(1));
		} else if (args[0].contains(Constants.FindEntry)) {
			findEntry(fileContents,args[0].substring(1));
		} else if (args[0].contains(Constants.ShowCount)) {
			showWordCount(fileContents);
		}
		System.out.println(Constants.DataLoadedText);
	}
	public static void showWordCount(String fileContents) {
		char charArray[] = fileContents.toCharArray();
		boolean in_word = false;
		int count = 0;
		for (char character : charArray) {
			if (character == Constants.Space) {
				if (!in_word) {
					count++;
					in_word = true;
				} else {
					in_word = false;
				}
			}
		}
		System.out.println(count + Constants.WordsFound);
	}
	public static void findEntry(String fileContents, String word) {
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
	public static void addEntry(String filename, String word) {
		writeToFile(filename, new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date()), word);
	}

	public static void showRandom(String fileContents) {
		String words[] = fileContents.split(Constants.StudentEntryDelimiter);
		if (words.length > 0) {
			int randomNumber = new Random().nextInt(words.length);
			System.out.println(words[randomNumber]);
		} else {
			System.out.println("No words found.");
		}
	}
	public static void showAll(String fileContents) {
		String words[] = fileContents.split(Constants.StudentEntryDelimiter);
		for (String word : words) {
			System.out.println(word);
		}
	}
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
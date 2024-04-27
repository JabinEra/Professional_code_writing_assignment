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
			String words[] = fileContents.split(Constants.StudentEntryDelimiter);
			for (String word : words) {
				System.out.println(word);
			}
		} else if (args[0].equals(Constants.ShowRandom)) {
			String words[] = fileContents.split(Constants.StudentEntryDelimiter);
			Random randomGenerator = new Random();
			int randomNumber = randomGenerator.nextInt(words.length);
			System.out.println(words[randomNumber]);
		} else if (args[0].contains(Constants.AddEntry)) {
			String word = args[0].substring(1);
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
			String finalDate = dateFormat.format(date);
			writeToFile(Constants.FILE_NAME,finalDate,word);
		} else if (args[0].contains(Constants.FindEntry)) {
			String words[] = fileContents.split(Constants.StudentEntryDelimiter);
			boolean done = false;
			String word = args[0].substring(1);
			for (int idx = 0; idx < words.length && !done; idx++) {
				if (words[idx].equals(word)) {
					System.out.println(Constants.FoundText);
					done = true;
				}
			}
		} else if (args[0].contains(Constants.ShowCount)) {
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
		System.out.println(Constants.DataLoadedText);
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
			String data = ", " + word + "\nList last updated on " + finalDate;
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true));
			bufferedWriter.write(data);
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

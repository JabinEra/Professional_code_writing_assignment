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

        String fileContents = readFileContents("students.txt");

        if (args[0].equals("a")) {
            System.out.println("Loading data ...");
            String words[] = fileContents.split(",");
            for (String word : words) {
                System.out.println(word);
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("r")) {
            System.out.println("Loading data ...");
            String words[] = fileContents.split(",");
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(words.length);
            System.out.println(words[randomNumber]);
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            String word = args[0].substring(1);
            Date date = new Date();
            String dateFormatSample = "dd/mm/yyyy-hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(dateFormatSample);
            String finalDate = dateFormat.format(date);
            writeToFile("students.txt", ", " + word + "\nList last updated on " + finalDate);
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            String words[] = fileContents.split(",");
            boolean done = false;
            String word = args[0].substring(1);
            for (int idx = 0; idx < words.length && !done; idx++) {
                if (words[idx].equals(word)) {
                    System.out.println("We found it!");
                    done = true;
                }
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            char charArray[] = fileContents.toCharArray();
            boolean in_word = false;
            int count = 0;
            for (char character : charArray) {
                if (character == ' ') {
                    if (!in_word) {
                        count++;
                        in_word = true;
                    } else {
                        in_word = false;
                    }
                }
            }
            System.out.println(count + " word(s) found ");
            System.out.println("Data Loaded.");
        }
    }

    public static String readFileContents(String filename) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String fileContents = bufferedReader.readLine();
			bufferedReader.close();
			return fileContents;
		} catch (Exception e) {
			return null;
		}
	}

	public static void writeToFile(String filename, String data) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true));
			bufferedWriter.write(data);
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
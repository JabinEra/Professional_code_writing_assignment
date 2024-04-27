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
        if (args[0].equals("a")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
                String fileContents = bufferedReader.readLine();
                String words[] = fileContents.split(",");
                for (String word : words) {
                    System.out.println(word);
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("r")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
                String fileContents = bufferedReader.readLine();
                String words[] = fileContents.split(",");
                Random randomGenerator = new Random();
                int randomNumber = randomGenerator.nextInt(words.length);
                System.out.println(words[randomNumber]);
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("students.txt", true));
                String word = args[0].substring(1);
                Date date = new Date();
                String dateFormatSample = "dd/mm/yyyy-hh:mm:ss a";
                DateFormat dateFormat = new SimpleDateFormat(dateFormatSample);
                String finalDate = dateFormat.format(date);
                bufferedWriter.write(", " + word + "\nList last updated on " + finalDate);
                bufferedWriter.close();
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
                String fileContents = bufferedReader.readLine();
                String words[] = fileContents.split(",");
                boolean done = false;
                String word = args[0].substring(1);
                for (int idx = 0; idx < words.length && !done; idx++) {
                    if (words[idx].equals(word)) {
                        System.out.println("We found it!");
                        done = true;
                    }
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
                String fileContents = bufferedReader.readLine();
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
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        }
    }
}
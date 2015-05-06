import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class Reader {

public static final String [] traits = {"Female", "Male", "Transgendered", "Hermaphrodite", "Epicene", "USA", "Canada", "Central America", "South America", "Western Europe", "Eastern Europe", "Middle Eastern", "North African", "African", "South Asian", "South East Asian", "Asian", "Aussie", "Kiwi", "Polynesian", "Outer Space", "Antarctican", "Lowest levels of the deep sea", "human", "alien", "ancients", "monster", "mutants"};

	public List<String> arrayOfLinesForFileName(String fileName) throws IOException {
		DataInputStream input = new DataInputStream(new FileInputStream(fileName));
		List<String> lines = new ArrayList<String>();

		while (input.available() > 0) {
	    	String z = input.readLine();
	    	lines.add(z);
		}
		input.close();

		return lines;
	}



	public static void main (String[] args) throws IOException{
		Reader reader = new Reader();
		List<String> arrayOfLines = reader.arrayOfLinesForFileName("data.dat");
		String firstLine = arrayOfLines.get(0);
		String delims = "[ ]+";
		String[] tokens = firstLine.split(delims);

		int numberOfAgents = Integer.parseInt(tokens[0]);
		int numberOfPassengers = Integer.parseInt(tokens[1]);


		for (int i = 1;  i < numberOfAgents * 3; i+= 3) {
			String lineInfo = arrayOfLines.get(i);
			System.out.println("line1: " + lineInfo);
			String lineLikes = arrayOfLines.get(i + 1);
			System.out.println("line2: " + lineLikes);
			String lineDislikes = arrayOfLines.get(i + 2);
			System.out.println("line3: " + lineDislikes);

			tokens = lineInfo.split(delims);
			String firstName = tokens[0];
			String lastName = tokens[1];
			int age = Integer.parseInt(tokens[2]);
			int gender = Integer.parseInt(tokens[3]);
			int paranoia = Integer.parseInt(tokens[4]);

			tokens = lineLikes.split(delims);
			List<String> likes = new ArrayList<String>();
			for (int j = 0; j < tokens.length; j++) {
				String like = traits[Integer.parseInt(tokens[j])];
				System.out.println("like: " + like);
				likes.add(like);
			}

			tokens = lineDislikes.split(delims);
			List<String> dislikes = new ArrayList<String>();
			for (int k = 0; k < tokens.length; k++) {
				String dislike = traits[Integer.parseInt(tokens[k])];
				System.out.println("dislike: " + dislike);
				dislikes.add(dislike);
			}

			System.out.println("firstName: " + firstName);
			System.out.println("lastName: " + lastName);
			System.out.println("age: " + age);
			System.out.println("gender: " + gender);
			System.out.println("paranoia: " + paranoia);
			System.out.println("likes: " + likes);
			System.out.println("dislikes: " + dislikes);
		}
	}
}
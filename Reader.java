import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Reader {

	public static final String [] traits = {"Female", "Male", "Transgendered", "Hermaphrodite", "Epicene", "USA", "Canada", "Central America", "South America", "Western Europe", "Eastern Europe", "Middle Eastern", "North African", "African", "South Asian", "South East Asian", "Asian", "Aussie", "Kiwi", "Polynesian", "Outer Space", "Antarctican", "Lowest levels of the deep sea", "human", "alien", "ancients", "monster", "mutants"};
	public ArrayList<Agent> agents = new ArrayList<Agent>();
	public ArrayList<Passenger> passengers = new ArrayList<Passenger>();

	public ArrayList<String> arrayOfLinesForFileName(String fileName) throws IOException {
		DataInputStream input = new DataInputStream(new FileInputStream(fileName));
		ArrayList<String> lines = new ArrayList<String>();

		while (input.available() > 0) {
	    	String z = input.readLine();
	    	lines.add(z);
		}
		input.close();

		return lines;
	}

	public void buildAgents()  throws IOException{
		ArrayList<String> arrayOfLines = this.arrayOfLinesForFileName("data.dat");
		String firstLine = arrayOfLines.get(0);
		String delims = "[ ]+";
		String[] tokens = firstLine.split(delims);

		int numberOfAgents = Integer.parseInt(tokens[0]);
		int numberOfPassengers = Integer.parseInt(tokens[1]);


		for (int i = 1;  i < numberOfAgents * 3; i+= 3) {
			String lineInfo = arrayOfLines.get(i);
			String lineLikes = arrayOfLines.get(i + 1);
			String lineDislikes = arrayOfLines.get(i + 2);

			tokens = lineInfo.split(delims);
			String firstName = tokens[0];
			String lastName = tokens[1];
			int age = Integer.parseInt(tokens[2]);
			int gender = Integer.parseInt(tokens[3]);
			int paranoia = Integer.parseInt(tokens[4]);

			tokens = lineLikes.split(delims);
			ArrayList<String> likes = new ArrayList<String>();
			for (int j = 0; j < tokens.length; j++) {
				String like = traits[Integer.parseInt(tokens[j])];
				likes.add(like);
			}

			tokens = lineDislikes.split(delims);
			ArrayList<String> dislikes = new ArrayList<String>();
			for (int k = 0; k < tokens.length; k++) {
				String dislike = traits[Integer.parseInt(tokens[k])];
				dislikes.add(dislike);
			}

			System.out.println("firstName: " + firstName);
			System.out.println("lastName: " + lastName);
			System.out.println("age: " + age);
			System.out.println("gender: " + gender);
			System.out.println("paranoia: " + paranoia);
			System.out.println("likes: " + likes);
			System.out.println("dislikes: " + dislikes);

			Agent agent = new Agent(firstName,lastName,age,gender,paranoia,likes,dislikes);
			this.agents.add(agent);
		}
	}

	public void buildPassengers() throws IOException {
		ArrayList<String> arrayOfLines = this.arrayOfLinesForFileName("data.dat");
		String firstLine = arrayOfLines.get(0);
		String delims = "[ ]+";
		String[] tokens = firstLine.split(delims);

		int numberOfAgents = Integer.parseInt(tokens[0]);
		int numberOfPassengers = Integer.parseInt(tokens[1]);
		
		for (int i = 1 + 3 * numberOfAgents;  i < 3 * numberOfPassengers + numberOfAgents; i++) {
			String lineInfo = arrayOfLines.get(i);
			tokens = lineInfo.split(delims);

			String firstName = tokens[0];
			String lastName = tokens[1];
			int age = Integer.parseInt(tokens[2]);
			int gender = Integer.parseInt(tokens[3]);
			String origin = traits[Integer.parseInt(tokens[4])];
			String species = traits[Integer.parseInt(tokens[5])];
			int dangerLevel = Integer.parseInt(tokens[6]);
			boolean isThreat = intToBool(Integer.parseInt(tokens[7]));

			System.out.println("firstName: " + firstName);
			System.out.println("lastName: " + lastName);
			System.out.println("age: " + age);
			System.out.println("gender: " + gender);
			System.out.println("origin: " + origin);
			System.out.println("species: " + species);
			System.out.println("dangerLevel: " + dangerLevel);
			System.out.println("isThreat" + isThreat);

			Passenger passenger = new Passenger(firstName, lastName, age, gender, origin, species, dangerLevel, isThreat);
			this.passengers.add(passenger);
		}
	}

	public boolean intToBool(int intToBeConverted) {
		if (intToBeConverted == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static void main (String[] args) throws IOException{
		Reader reader = new Reader();
		reader.buildAgents();
		reader.buildPassengers();
		System.out.println(reader.agents); 
		System.out.println(reader.passengers);
	}
}
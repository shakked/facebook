import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Reader {

	public static final String [] traits = {"Female", "Male", "Transgendered", "Hermaphrodite", "Epicene", "USA", "Canada", "Central America", "South America", "Western Europe", "Eastern Europe", "Middle Eastern", "North African", "African", "South Asian", "South East Asian", "Asian", "Aussie", "Kiwi", "Polynesian", "Outer Space", "Antarctican", "Lowest levels of the deep sea", "human", "alien", "ancients", "monster", "mutants"};
	public ArrayList<Agent> agents = new ArrayList<Agent>();
	public ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	public ArrayList<TSALine> lines = new ArrayList<TSALine>();

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

		for (int i = 1;  i < 1 + numberOfAgents * 3; i+= 3) {
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

		for (int i = 1 + 3 * numberOfAgents;  i <= 3 * numberOfAgents + numberOfPassengers; i++) {
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

			Passenger passenger = new Passenger(firstName, lastName, age, gender, origin, species, dangerLevel, isThreat);
			this.passengers.add(passenger);
		}
	}

	public boolean intToBool(int intToBeConverted) {
		if (intToBeConverted == 1) {
			return true;
		} else {
			return false;
		}}

	public void sortAgents() {
		Collections.sort(this.agents);
		Collections.reverse(this.agents);
	}

	public void createLinesAndSetAgents() {
		for (int i = 0; i < this.agents.size(); i++) {
			TSALine line = new TSALine(this.agents.get(i));
			this.lines.add(line);
			System.out.println(line.agent.firstName + " " + line.agent.lastName + "(" + line.agent.age + ") is being assigned to line " + i);
		}
	}

	public void addPassengersToLines() {
		for (int i = 0; i < passengers.size(); i++) {
			// index = (age - gender + origin + species) % t  (where t is the number of lines, or TSA agents)
			Passenger passenger = this.passengers.get(i);
			int indexOfLineForPassenger = (passenger.age - passenger.gender + passenger.getOriginAsInt() + passenger.getSpeciesAsInt()) % this.agents.size();
			TSALine line = this.lines.get(indexOfLineForPassenger);
			line.passengers.add(passenger);
		}

		for (int i = 0; i < this.lines.size(); i++) {
			this.lines.get(i).buildQueue();
		}
		//you added all the passengers to their corresponding lines without testing it, you now have to build the passengerLines into a priority queue
		//
	}

	public static void main (String[] args) throws IOException{
		System.out.println("\n\n Starting TSA Simulation.... w00t");
		Reader reader = new Reader();
		reader.buildAgents();
		reader.buildPassengers();
		reader.sortAgents();
		reader.createLinesAndSetAgents();
		reader.addPassengersToLines();
		for (int i = 0; i < reader.lines.size(); i++) {
			TSALine line = reader.lines.get(i);
			for (int j = 0; j < line.passengers.size(); j++) {

				Passenger passenger = line.priorityQueue.poll();
				boolean shouldInterrogate = line.shouldInterrogate(passenger);
				if (shouldInterrogate) {
					System.out.println(passenger.firstName + " " + passenger.lastName + " is being interrogated.");
				} else {
					System.out.println(passenger.firstName + " " + passenger.lastName + " is moving on without interrogation.");
				}
			}
		}
		System.out.println("\n Done with TSA Simulation.... w00t\n\n");
	}
}
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class TSALine implements Comparator<Passenger> {
	public ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	public PriorityQueue<Passenger> priorityQueue = new PriorityQueue<Passenger>();
	public Agent agent;
	public static final String [] traits = {"Female", "Male", "Transgendered", "Hermaphrodite", "Epicene", "USA", "Canada", "Central America", "South America", "Western Europe", "Eastern Europe", "Middle Eastern", "North African", "African", "South Asian", "South East Asian", "Asian", "Aussie", "Kiwi", "Polynesian", "Outer Space", "Antarctican", "Lowest levels of the deep sea", "human", "alien", "ancients", "monster", "mutants"};


	public TSALine(Agent agent) {
		this.agent = agent;
	}

	public void buildQueue() {
		for (int i = 0; i < this.passengers.size(); i++) {
			Passenger passenger = this.passengers.get(i);
			this.priorityQueue.add(passenger);
		}
	}

	public int compare(Passenger a, Passenger b) {
		int dangerLevelA = a.dangerLevel;
		int dangerLevelB = b.dangerLevel;
 
		if (dangerLevelA < dangerLevelB) {
			return 1;
		} else if (dangerLevelA > dangerLevelB) {
			return -1;
		} else {
			return 0;
		}
	}

	public boolean shouldInterrogate(Passenger p) {
		String gender = getGenderForPassenger(p);

		int dangerLevel = p.dangerLevel;
		if (this.agent.dislikes.contains(gender)) {
			dangerLevel+= 2;
		}
		if (this.agent.dislikes.contains(p.origin)) {
			dangerLevel+= 2;
		}
		if (this.agent.dislikes.contains(p.species)) {
			dangerLevel+= 2;
		}

		if (this.agent.likes.contains(gender)) {
			dangerLevel-= 2;
		}
		if (this.agent.likes.contains(p.origin)) {
			dangerLevel-= 2;
		}
		if (this.agent.likes.contains(p.species)) {
			dangerLevel-= 2;
		}

		if (dangerLevel <  10 - this.agent.paranoiaLevel) {
			return false;
		} else {
			return true;
		}

	}

	public String getGenderForPassenger(Passenger p) {
		return this.traits[p.gender];
	}

	public String toString() {
		return "{TSALine: # of passengers: " + this.passengers.size() + " \n PQsize: " + this.priorityQueue.size() + "\n pqPeak: " + this.priorityQueue.peek() + "\npriorityQueue: " + this.priorityQueue + "}";
	}

}
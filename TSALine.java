import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class TSALine implements Comparator<Passenger> {
	public ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	public PriorityQueue<Passenger> priorityQueue;
	public Agent agent;

	public TSALine(Agent agent) {
		this.agent = agent;
	}

	public void buildQueue() {
		priorityQueue = new PriorityQueue(passengers);
	}

	public int compare(Passenger a, Passenger b) {
		int dangerLevelA = a.dangerLevel;
		int dangerLevelB = b.dangerLevel;
 
		if (dangerLevelA > dangerLevelB) {
			return 1;
		} else if (dangerLevelA < dangerLevelB) {
			return -1;
		} else {
			return 0;
		}
	}

}
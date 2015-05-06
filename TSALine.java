import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class TSALine implements Comparator<Passenger> {
	public PriorityQueue<Passenger> priorityQueue = new PriorityQueue<Passenger>();

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
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class Passenger implements Comparable<Passenger>{
	public String firstName;
	public String lastName;
	public int age;
	public int gender;
	public String origin;
	public String species;
	public int dangerLevel;
	public boolean isThreat;

	public static final String [] traits = {"Female", "Male", "Transgendered", "Hermaphrodite", "Epicene", "USA", "Canada", "Central America", "South America", "Western Europe", "Eastern Europe", "Middle Eastern", "North African", "African", "South Asian", "South East Asian", "Asian", "Aussie", "Kiwi", "Polynesian", "Outer Space", "Antarctican", "Lowest levels of the deep sea", "human", "alien", "ancients", "monster", "mutants"};


	public Passenger() {

	}

	public Passenger(String firstName, String lastName, int age, int gender, String origin, String species, int dangerLevel, boolean isThreat) {
		if (firstName == null || firstName.length() <= 0) {
			firstName = "invalid first name given";
		}
		if (lastName == null || lastName.length() <= 0) {
			lastName = "invalid last name given";
		}
		if (age <= 0) {
			age = 5;
		}
		if (gender != 1 && gender != 0) {
			gender = 1;
		}
		if (origin == null || origin.length() <= 0) {
			origin = "USA";
		}
		if (species == null || species.length() <= 0) {
			species = "human";
		}
		if (dangerLevel < 0 || dangerLevel > 10) {
			dangerLevel = 5;
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.origin = origin;
		this.species = species;
		this.dangerLevel = dangerLevel;
		this.isThreat = isThreat;
	}

	public String toString() { 
		String description = "\n{ Passenger: " + firstName + " " + lastName + "\nage: " + age + "\ngender: " + gender + "\norigin: " + origin + "\nspecies: " + species + "\ndangerLevel: " + dangerLevel + "\nisThreat: " + isThreat+ " }\n";
		return description;
	}

	public int compareTo(Passenger passenger){
		if(this.dangerLevel == passenger.dangerLevel) {
			return 0;  
		} else if(this.dangerLevel > passenger.dangerLevel) {
			return 1;
		} else {
			return -1;
		}  
   }

   public int getOriginAsInt () {
   		return Arrays.asList(this.traits).indexOf(this.origin);
   }

   public int getSpeciesAsInt () {
   		return Arrays.asList(this.traits).indexOf(this.species);
   }


















}
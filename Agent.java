import java.util.*;
public class Agent implements Comparator<Agent>, Comparable<Agent>{
// Name LastName age gender paranoia
// Like1 like2 like3 ...
// dislike1 dislike2 dislike3...
// Officer Barbrady 39 1 4
// 4 22 25
// 0 5 23

	public String firstName;
	public String lastName;
	public int age;
	public int gender;
	public int paranoiaLevel;
	public ArrayList<String> likes;
	public ArrayList<String> dislikes;

	public Agent(String firstName, String lastName, int age, int gender, int paranoiaLevel, ArrayList<String> likes, ArrayList<String> dislikes) {
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
		if (paranoiaLevel > 10 || paranoiaLevel < 0) {
			paranoiaLevel = 5;
		}
		if (likes == null) {
			likes = new ArrayList<String>();
		}
		if (dislikes == null) {
			dislikes = new ArrayList<String>();
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.paranoiaLevel = paranoiaLevel;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	public int compareTo(Agent agent){
		if(this.age == agent.age) {
			return 0;  
		} else if(this.age >agent.age) {
			return 1;
		} else {
			return -1;
		}  
   }

   public int compare(Agent a, Agent b){
      return a.age - b.age;
   }

	public String toString() {
		String string = "\n{ Agent: " + firstName + " " + lastName + "\nage: " + age + "\nparanoia level: " + paranoiaLevel + "\nlikes: " + likes + "\ndislikes: " + dislikes + "}\n";
		return string; 
	}

}
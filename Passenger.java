public class Passenger {
	public String firstName;
	public String lastName;
	public int age;
	public int gender;
	public String origin;
	public String species;
	public int dangerLevel;
	public boolean isThreat;

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

}
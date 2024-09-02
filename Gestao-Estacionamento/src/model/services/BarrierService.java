package model.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Vehicle;

//Service for the barriers
public class BarrierService {

	static Scanner sc = new Scanner(System.in);

	//service for the barrier to enter
	public static int validateEntryBarriers(Vehicle vehicle) {

		System.out.println("Enter by the barriers: ");

		String category = vehicle.getCategory().name(); 
		String options;
		String[] optionsArray;
		int choice = 0;
		boolean validOption = false;

		//According to the category it will show different options for the car to enter
		switch (category) {
		case "CAR":
		case "PUBLIC": {
			options = "1 | 2 | 3 | 4 | 5";
			optionsArray = new String[] { "1", "2", "3", "4", "5" };
			break;
		}
		case "MOTORCYCLE": {
			options = "5";
			optionsArray = new String[] { "5" };
			break;
		}
		case "TRUCK": {
			optionsArray = new String[] { "1" };
			options = "1";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + category);
		}

		System.out.println();

		//It creates a loop showing suggestion for the barrier to entry, and making the user choose
		do {
			System.out.println("A " + category + " can entry on the following barriers: " );
			System.out.println(options);
			System.out.println("Choose a barrier to enter through: ");

			try {
				choice = sc.nextInt();
				 //If the barrier chosen is valid for that vehicle, this will become true and close the loop
				validOption = false;

				//This will see if the optino the user choose is in the array, if it is then 
				//validate option becomes true and the loop ends
				for (String option : optionsArray) {
					if (option.equals(String.valueOf(choice))) {
						validOption = true;
						break;
					}
				}

				if (validOption == true) {
					System.out.println("Entered through the barrier " + choice);
				} else {
					System.out.println("Choose an adequead barrier for " + category);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry, please try a number.");
				sc.next(); //Putting here to clear the scanner 
			}

		} while (validOption == false);

		System.out.println();//Apenas para pular uma linha
		
		return choice;

	}

	//Almost the same as enter, but with the values changed
	public static int validateExitBarriers(Vehicle vehicle) {
		System.out.println("Leaving by the barrier: ");

		String category = vehicle.getCategory().name(); 
		String options;
		String[] optionsArray;
		int choice = 0;
		boolean validOption = false;

		switch (category) {
		case "CAR":
		case "PUBLIC":
		case "TRUCK": {
			options = "6 | 7 | 8 | 9 | 10";
			optionsArray = new String[] { "6", "7", "8", "9", "10" };
			break;
		}
		case "MOTORCYCLE": {
			options = "10";
			optionsArray = new String[] { "10" };
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + category);
		}

		do {
			System.out.println();
			System.out.println("A " + category + " can leave through the following barriers: " );
			System.out.println(options);
			System.out.println("Choose a barrier to leave through.");

			try {
				choice = sc.nextInt();

				validOption = false; 

				for (String option : optionsArray) {
					if (option.equals(String.valueOf(choice))) {
						validOption = true;
						break;
					}
				}

				if (validOption == true) {
					System.out.println("Left through the barrier " + choice);
				} else {
					System.out.println("Choose an adequead barrier for " + category);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry, please try a number.");
				sc.next(); 
			}

		} while (validOption == false);

		System.out.println();
		
		return choice;

	}

}

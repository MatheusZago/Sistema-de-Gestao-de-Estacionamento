package model.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Vehicle;

public class BarrierService {

	static Scanner sc = new Scanner(System.in);

	// Serviços para fazer a questão de validação de qual cancela pode ser usada por
	// qual veiculo
	public static int validateEntryBarriers(Vehicle vehicle) {

		System.out.println("Enter by the barriers: ");

		String category = vehicle.getCategory().name(); // Transformando TipoModelo em String
		String options;
		String[] optionsArray;
		int choice = 0;
		boolean validOption = false;

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

		do {
			System.out.println("A " + category + " can entry on the following barriers: " + options);
			System.out.println("CHOOSE A BARRIER TO ENTER THROUGH ");

			try {
				choice = sc.nextInt();

				// Aqui verifica se a escolha está nas opções válidas
				validOption = false; // Reinicia a validação para cada nova tentativa

				for (String option : optionsArray) {
					if (option.equals(String.valueOf(choice))) {
						validOption = true;
						break;
					}
				}

				if (validOption) {
					System.out.println("Passed through the barrier " + choice);
				} else {
					System.out.println("Choose an adequead barrier for " + category);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry, please try a number.");
				sc.next(); // Limpa o buffer do scanner
			}

		} while (validOption == false);

		System.out.println("Entered through barrier " + choice);

		return choice;

	}

	public static int validateExitBarriers(Vehicle vehicle) {
		System.out.println("Leaving by the barrier: ");

		String category = vehicle.getCategory().name(); // Transformando TipoModelo em String
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
			System.out.println("A " + category + " can leave through the following barriers: " + options);
			System.out.println("Choose a barrier to leave through.");

			try {
				choice = sc.nextInt();

				// Aqui verifica se a escolha está nas opções válidas
				validOption = false; // Reinicia a validação para cada nova tentativa

				for (String option : optionsArray) {
					if (option.equals(String.valueOf(choice))) {
						validOption = true;
						break;
					}
				}

				if (validOption) {
					System.out.println("Left through the barrier " + choice);
				} else {
					System.out.println("Choose an adequead barrier for " + category);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid entry, please try a number.");
				sc.next(); // Limpa o buffer do scanner
			}

		} while (validOption == false);

		return choice;

	}

}

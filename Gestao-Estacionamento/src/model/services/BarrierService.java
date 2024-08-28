package model.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Vehicle;

public class BarrierService {
	
	static Scanner sc = new Scanner(System.in);
	
	//Serviços para fazer a questão de validação de qual cancela pode ser usada por qual veiculo
	public static void validateEntryBarriers(Vehicle vehicle) {
		
		String category = vehicle.getCategory().name(); //Transformando TipoModelo em String 
		String options;
		String[] optionsArray;
		int choice = 0;
		boolean validOption = false;
		
		switch (category) {
		case "CAR": 
		case "PUBLIC": {
			options = "1 | 2 | 3 | 4 | 5";
			optionsArray = new String[] {"1", "2", "3", "4", "5"};
			break;
		} case "MOTORCYCLE": {
			options = "5";
			optionsArray = new String[] {"5"};
			break;
		} case "TRUCK": {
			optionsArray = new String[] {"1"};
			options = "1";
			break;
		} 
		default:
			throw new IllegalArgumentException("Unexpected value: " + category);
		}
		
		System.out.println();
		
		do {
			System.out.println("A " + category + " can entry on the following barriers: " + options );
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
		
		
		
	}
	
public static void validacaoCatracasSaida(Vehicle vehicle) {
	
	System.out.println("Called function to exit.r");
		
		String category = vehicle.getCategory().name(); //Transformando TipoModelo em String 
		String options;
		String[] optionsArray;
		int choice;
		boolean validOption = false;
		
		switch (category) {
		case "CAR": 
		case "PUBLIC":
		case "TRUCK": {
			options = "6 | 7 | 8 | 9 | 10";
			optionsArray = new String[] {"6", "7", "8", "9", "10"};
			break;
		} case "MOTORCYCLE": {
			options = "10";
			optionsArray = new String[] {"10"};
			break;
		} 
		default:
			throw new IllegalArgumentException("Unexpected value: " + category);
		}
		
		System.out.println();
		System.out.println("CHOOSE A BARRIER TO LEAVE THROUGH ");
		
		do {
			System.out.println("A " + category + " can leave through: " + options );
			
			choice = sc.nextInt();

			//Aqui ele vai percorrer o Array para ver se o numero digite tem nas opções válidas
			for(String option : optionsArray) {
				if(option.equals(String.valueOf(choice))) {
					validOption = true;
					break;
				} else if(choice > 10 || choice < 6) {
					System.out.println("Choose an existing barrier to pass through.");
				} else if(validOption == false ) {
					System.out.println("Choose an adequate barier for " + vehicle.getCategory());
				} else {
					//TODO Arrumar para não travar
					System.out.println("Invalid number, please try again.");
				}
			}
						
		} while (validOption == false);
		
		System.out.println("Left through barrier " + choice);
		
		
	}
	
	

}

package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.impl.VehicleDaoJBDC;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

//Classe principal da aplicação
public class App {

	public static void main(String[] args) {

		VehicleDaoJBDC newVehicle = DaoFactory.createVehicleDaoJBDC();
//		RegisteredDao newRegistered = DaoFactory.createRegisteredDao();
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		boolean test = true;

		try {

			// Foi usado do while para que o programa execute pelo menos uma vez.
			do {
				{
					System.out.println("=====CHOOSE AN OPTION=====");
					System.out.println("1) Register entrance");
					System.out.println("2) Register exit");
					System.out.println("3) Register Vehicle");
					System.out.println("4) Exit");

					int choice = sc.nextInt();

					switch (choice) {
					case 1: { // IMPEDIR PLACAS DUPLICADAS
						try {
							// Passo 1, construir o carro que quer entrar (FEITO)
							System.out.println();
							System.out.println("Let's register your entrance!");
							System.out.print("Enter the plate number: ");
							String plate = sc.next().toUpperCase();
							
							// 2 ) VERIFICAR SE O VEICULO ESTÁ CADASTRADO:
							// Cria-lo se estiver cadastrado
							Vehicle vehicle = Vehicle.InstantiateVehicleForEntry(plate);
							


							// Inserindo o novo veiculo na base de dados para que ele seja considerado
//							// dentro do estacionamento
							newVehicle.insert(vehicle);
							
//
//							// Ele ta fazendo isso só pra epgar o objeto completo do banco de dados,
//							// incluindo o ID que foi gerado
							Vehicle idRetrieve = newVehicle.findVehicleByPlate(vehicle.getPlate());
							System.out.println(idRetrieve);
							vehicle.setId(idRetrieve.getId());
//
//							// Está pegando a data e usando o formater pra deixar da forma certa
							System.out.println("Enter the date for your arrival (dd/MM/yyyy HH:mm)");
							sc.nextLine();
							String dateTimeInput = sc.nextLine();

							LocalDateTime arriveDateTime;
							try {
								arriveDateTime = LocalDateTime.parse(dateTimeInput, dtf);
							} catch (DateTimeParseException e) {
								System.out.println("Invalid date format. Please use the format dd/MM/yyyy HH:mm");
								return;
							}

							System.out.println(vehicle.getId());

							vehicle.enter(vehicle, arriveDateTime);

						} catch (Exception e) {
							e.printStackTrace();
						}

						break;
					}
					case 2: {

						System.out.println();
						System.out.println("Let's register your exit!");
						System.out.print("Enter the plate number: ");
						String plate = sc.next().toUpperCase();
						System.out.println();

						
						
						Vehicle vehicle = Vehicle.instantiateVehicleforExit(plate);
						
						System.out.println("Enter the date for your exit (dd/MM/yyyy HH:mm)");
						sc.nextLine();
						String dateTimeInput = sc.nextLine();

						LocalDateTime leaveDateTime;
						try {
							leaveDateTime = LocalDateTime.parse(dateTimeInput, dtf);
						} catch (DateTimeParseException e) {
							System.out.println("Invalid date format. Please use the format dd/MM/yyyy HH:mm");
							return;
						}
						
						vehicle.exit(vehicle, leaveDateTime);

						break;
					}
					case 3: {// BASICAMENTE FEITO, APENAS MELHORIAS E BUGS

						// Aqu está puxando o cridor de veiculos do BD
						System.out.println("Let's register your vehicle!");

						System.out.print("Enter vehicle plate: ");
						String plate = sc.next().toUpperCase();
						// TODO, dar um erro para evitar placas repetidas sem quebrar o programa.
						System.out.print("Category (CAR, MOTORCYCLE, TRUCK): ");
						String type = sc.next().toUpperCase();
						VehicleCategory model = VehicleCategory.valueOf(type);
 
						Vehicle registered; 

						// Vai criar um veiculo especifio de acordo com o tipo dele, sendo caminhao ou
						// avulso (carro ou moto)
						if (model == VehicleCategory.TRUCK) {
							registered = new DeliveryTruck(plate, model);
							((DeliveryTruck) registered).register(registered); // Ta especificando qual é.
						} else if (model == VehicleCategory.CAR || model == VehicleCategory.MOTORCYCLE) {
							registered = new MonthlySubscriber(plate, model);
							((MonthlySubscriber) registered).register(registered); // Ta especificando qual é
						} else {
							// TODO ajeitar para usar um erro
//							newRegistered = null;
							System.out.println("Invalid vehicle, try again.");
						}

						break;
					}
					case 4: {
						System.out.println("Thank you for using the system!");
						test = false;
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + choice);
					}

					// Apenas para pular linha
					System.out.println();

				}
			}

			while (test);

		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		finally {
			sc.close();
		}

	}

}

package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.impl.ParkedDaoJBDC;
import model.entities.DeliveryTruck;
import model.entities.IndividualVehicle;
import model.entities.MonthlySubscriber;
import model.entities.Parked;
import model.entities.PublicService;
import model.entities.Vehicle;
import model.enums.VehicleCategory;
import model.services.VerifyRegisterService;

//Classe principal da aplicação
public class App {

	public static void main(String[] args) {

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
					case 1: { // QUASE FEITO, FALTA APENAS O TICKET, IMPEDIR PLACAS DUPLICADAS E QUE AS VAGAS
								// TENHAM DE SER UMA DO LADO DA OUTRA
						try {
							// Passo 1, construir o carro que quer entrar (FEITO)
							System.out.println();
							System.out.println("Let's register your entrance!");
							System.out.print("Enter the plate number: ");
							String plate = sc.next().toUpperCase();

							// 2 ) VERIFICAR SE O VEICULO ESTÁ CADASTRADO: (FEITO)
							Vehicle vehicle = VerifyRegisterService.verifyRegister(plate);
							// Se o veiculo não estiver registrado ele constroi ele assim.
							if (vehicle == null) {
//								// TODO adicionar um erro caso tente entrar com caminhão não registrado.
								System.out.println("Vehicle not registered.");
								System.out.print("Inform the vehicle category (CAR, MOTORCYCLE, PUBLIC): ");
								String type = sc.next().toUpperCase();
								VehicleCategory category = VehicleCategory.valueOf(type);

								if (category == VehicleCategory.PUBLIC) {
									vehicle = new PublicService(plate, category);
								} else {
									vehicle = new IndividualVehicle(plate, category);
								}

								System.out.println("Vehicle with plate " + vehicle.getPlate() + " is a " + vehicle.getCategory());
							} else {
								System.out.println("Vehicle with plate " + vehicle.getPlate() + " is a " + vehicle.getCategory() + " and is registered.");
							}

							// Está pegando a data e usando o formater pra deixar da forma certa
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

							// Passo 3, ver qual o tipo do carro para saber qual catraca ele passa (FEITO)
							vehicle.enter(vehicle, arriveDateTime);

						} catch (Exception e) {
							e.printStackTrace();
						}

						// Passo 4, fazer a entrada do carro, se for avulso tem que ter ticket se não
						// não precisa
						// Passo 5, Adicionar no banco de dados "estacionamento" o carro e as vagas que
						// ele ocupa

						break;
					}
					case 2: {

						System.out.println();
						System.out.println("Let's register your exit!");
						System.out.print("Enter the plate number: ");
						String plate = sc.next().toUpperCase();
						System.out.println();

						ParkedDaoJBDC parked = DaoFactory.createParkedDaoJBDC();
						// Ele ta instanciando um veiculo estacionado de acordo com oq recebe.
						Parked parkedVehicle = parked.findByPlate(plate);
						Vehicle vehicle;

						// CRIAR TIPO DE VEICULO
						// Se ele achar um veiculo registrado.
						if (VerifyRegisterService.verifyRegister(parkedVehicle.getPlate()) != null) {

							// Se ele estiver registrado vai ser criado de acordo com o tipo
							if (parkedVehicle.getCategory() == VehicleCategory.TRUCK) {
								vehicle = new DeliveryTruck(plate, parkedVehicle.getCategory());
							} else {
								vehicle = new MonthlySubscriber(plate, parkedVehicle.getCategory());
							}

						} else {

							if (parkedVehicle.getCategory() == VehicleCategory.PUBLIC) {
								vehicle = new PublicService(plate, parkedVehicle.getCategory());
							} else {
								vehicle = new IndividualVehicle(plate, parkedVehicle.getCategory());
							}

						}

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

						// CRIOU O VEICULO, AGORA TEM QUE FAZER ELE PASSAR PELAS CATRACAS
						System.out.println("Date and time parsed successfully: " + leaveDateTime);
						vehicle.exit(vehicle, parkedVehicle, leaveDateTime);

//						System.out.println(vehicle);

//						vehicle.instantiateVehicle(parkedVehicle.getPlate(), parkedVehicle.getCategory());

						break;
					}
					case 3: {// BASICAMENTE FEITO, APENAS MELHORIAS E BUGS

						// Aqu está puxando o cridor de veiculos do BD
						DaoFactory.createVehicleDao();
						System.out.print("Enter vehicle plate: ");
						String plate = sc.next();
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
							registered = null;
							System.out.println("Invalid vehicle, try again.");
						}

						System.out.println(registered.getSize());

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

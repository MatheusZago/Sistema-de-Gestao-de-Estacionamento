package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.impl.VehicleDaoJBDC;
import model.entities.DeliveryTruck;
import model.entities.IndividualVehicle;
import model.entities.MonthlySubscriber;
import model.entities.PublicService;
import model.entities.Vehicle;
import model.enums.VehicleCategory;
import model.services.VerifyRegisterService;

//Classe principal da aplicação
public class App {

	public static void main(String[] args) {

		VehicleDaoJBDC newVehicle = DaoFactory.createVehicleDaoJBDC();
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
							Vehicle vehicle = VerifyRegisterService.verifyRegister(plate);
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

							} else {
								System.out.println("Vehicle with plate " + vehicle.getPlate() + " is a "
										+ vehicle.getCategory() + " and is registered.");

							}

							// Inserindo o novo veiculo na base de dados para que ele seja considerado
							// dentro do estacionamento
							newVehicle.insert(vehicle);

							// Ele ta fazendo isso só pra epgar o objeto completo do banco de dados,
							// incluindo o ID que foi gerado
							vehicle = newVehicle.findVehicleByPlate(vehicle.getPlate());

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

//						ParkedDaoJBDC parked = DaoFactory.createParkedDaoJBDC();
						// Ele ta instanciando um veiculo estacionado de acordo com oq recebe.
//						Parked parkedVehicle = parked.findByPlate(plate);

						Vehicle vehicle = newVehicle.findVehicleByPlate(plate);

						// CRIAR TIPO DE VEICULO
						// Se ele achar um veiculo cadastrad.
						if (VerifyRegisterService.verifyRegister(vehicle.getPlate()) != null) {

							// Se ele estiver registrado vai ser criado de acordo com o tipo
							if (vehicle.getCategory() == VehicleCategory.TRUCK) {
								vehicle = new DeliveryTruck(vehicle.getId(), plate, vehicle.getCategory());
							} else {
								vehicle = new MonthlySubscriber(vehicle.getId(), plate, vehicle.getCategory());
							}

						} else {

							if (vehicle.getCategory() == VehicleCategory.PUBLIC) {
								vehicle = new PublicService(vehicle.getId(), plate, vehicle.getCategory());
							} else {
								vehicle = new IndividualVehicle(vehicle.getId(), plate, vehicle.getCategory());
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

						vehicle.exit(vehicle, leaveDateTime);

						break;
					}
					case 3: {// BASICAMENTE FEITO, APENAS MELHORIAS E BUGS

						// Aqu está puxando o cridor de veiculos do BD
						System.out.println("Let's register your vehicle!");
						DaoFactory.createRegisteredDao();
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

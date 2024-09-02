package application;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.impl.VehicleDaoJBDC;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

//Main class of the applicatino
public class App {

	public static void main(String[] args) {

		//Creating Objects used by the menu
		VehicleDaoJBDC newVehicle = DaoFactory.createVehicleDaoJBDC();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		Scanner sc = new Scanner(System.in);
		boolean test = true;

		try {

			//The program uses a do while for it's menu, it shows the menu's options at least once
			//And until the user chooses to exit it will repea
			do {
				{
					System.out.println("=====CHOOSE AN OPTION=====");
					System.out.println("1) Register entrance");
					System.out.println("2) Register exit");
					System.out.println("3) Register Vehicle");
					System.out.println("4) Exit");

					int choice = sc.nextInt();

					switch (choice) {
					case 1: {
						try {
							
							System.out.println();
							System.out.println("Let's register your entrance!");
							System.out.print("Enter the plate number: ");
							String plate = sc.next().toUpperCase();
							//The system takes the plate of the car and calls this method with the purpose of 
							///Instantiating a vehicle, the method is able to verify if the vehicle is enrolleed 
							//If it is it creates e vehicle with the data the enrolleed table, if it isn't it constructs a new vehicle
							Vehicle vehicle = Vehicle.InstantiateVehicleForEntry(plate);
							//Inserting the vehicle in the vehicle table
							newVehicle.insert(vehicle);
							
							//This is used to add the id created by the vehicleTable on the vehicle object
							Vehicle idRetrieve = newVehicle.findVehicleByPlate(vehicle.getPlate());
							vehicle.setId(idRetrieve.getId());

							///This is taking a dateTime imput and parsing it as Timestamp for DB use
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
							
							Instant instant = arriveDateTime.atZone(ZoneId.systemDefault()).toInstant();
							Timestamp arriveTimeStamp = Timestamp.from(instant);
							
							//This is calling the enter method of the vehicle, enter is a method mostly implemented
							//But it has diferences depending on the subclass called (Public service, TRUCK etc)
							vehicle.enter(vehicle, arriveTimeStamp);
							
							System.out.println("Parked with success!");

						} catch (Exception e) {
							e.printStackTrace();
						}

						break;
					}
					case 2: {

						//This part is simply taking the plate of a vehicle that is in the vehicle table and taking the time 
						//that the person intends to leave 
						System.out.println();
						System.out.println("Let's register your exit!");
						System.out.print("Enter the plate number: ");
						String plate = sc.next().toUpperCase();
						System.out.println();						
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
						
						//Again transformingo dateTime into Timestamp for DB use.
						Instant instant = leaveDateTime.atZone(ZoneId.systemDefault()).toInstant();
						Timestamp exitTimeStamp = Timestamp.from(instant);
						
						//The vehicle is being instantiated for it to be used by the exit logic.
						Vehicle vehicle = Vehicle.instantiateVehicleforExit(plate);
						

						
						//Similarly to the enter logic it is mostly implemented on Vehicle but it has changes
						//Depending on the subclass called
						vehicle.exit(vehicle, exitTimeStamp);

						break;
					}
					case 3: {	
						//Taking infos to create a vehicle
						System.out.println("Let's register your vehicle!");
						System.out.print("Enter vehicle plate: ");
						String plate = sc.next().toUpperCase();
						System.out.print("Category (CAR, MOTORCYCLE, TRUCK): ");
						String type = sc.next().toUpperCase();
						VehicleCategory model = VehicleCategory.valueOf(type);
 
						Vehicle registered; 

						//Depending on the model of the vehicle it will call a different constructor
						//Only delivery trucks and montlhySubscrivers can be registered, so they are the only options
						if (model == VehicleCategory.TRUCK) {
							registered = new DeliveryTruck(plate, model);
							//Usando downcast para especificar a subclass
							((DeliveryTruck) registered).register(registered); 
						} else if (model == VehicleCategory.CAR || model == VehicleCategory.MOTORCYCLE) {
							registered = new MonthlySubscriber(plate, model);
							((MonthlySubscriber) registered).register(registered);
						} else {
							// TODO ajeitar para usar um erro
//							newRegistered = null;
							System.out.println("Invalid vehicle, try again.");
						}

						break;
					}
					case 4: {
						//Simply breaks the while loop
						System.out.println("Thank you for using the system!");
						test = false;
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + choice);
					}

					//Just to skip a line
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

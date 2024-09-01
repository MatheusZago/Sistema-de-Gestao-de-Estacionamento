package model.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.dao.DaoFactory;
import model.dao.EnrolleesDao;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.dao.impl.RegistersDaoJBDC;
import model.dao.impl.VehicleDaoJBDC;
import model.enums.VehicleCategory;
import model.services.BarrierService;

//Abstract class vehicle
public abstract class Vehicle {
	
	//DAO static for use by the subclasses
	static VehicleDaoJBDC accessVehicle = DaoFactory.createVehicleDaoJBDC();
	static EnrolleesDao accessEnrollees = DaoFactory.createEnrolleesDaoJBDC();
	static ParkingSlotDaoJBDC accessParkingSlot = DaoFactory.createParkingSlotDaoJBDC();
	static RegistersDaoJBDC accessRegister = DaoFactory.createRegisterDaoJBDC();
	static Scanner sc = new Scanner(System.in);

	private int id;
	private String plate;
	private VehicleCategory category;
	private int size;
	private int[] choices;
	int exitBarrier;
	int entryBarrier;
	
	//Multiple constructors for multiple cenarios
	public Vehicle() {

	}
	
	public Vehicle(String placa) {
		this.plate = placa;
	}

	public Vehicle(String plate, VehicleCategory category) {
		this.plate = plate;
		this.category = category;
	}

	public Vehicle(int id, String plate, VehicleCategory category) {
		this.id = id;
		this.plate = plate;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public VehicleCategory getCategory() {
		return category;
	}

	public void setCategory(VehicleCategory category) {
		this.category = category;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int[] getChoices() {
		return choices;
	}

	public void setChoices(int[] choices) {
		this.choices = choices;
	}
	
	public int getExitBarrier() {
		return exitBarrier;
	}

	public void setExitBarrier(int exitBarrier) {
		this.exitBarrier = exitBarrier;
	}
	
	public int getEntryBarrier() {
		return entryBarrier;
	}

	public void setEntryBarrier(int entryBarrier) {
		this.entryBarrier = entryBarrier;
	}
	
	//Instantiate method to create a vehicle
	public static Vehicle InstantiateVehicleForEntry(String plate) {
		Vehicle vehicle = accessEnrollees.FindEnrolleesByPlate(plate);
		//if the vehicle is in the enrolleed table, it is enrolled then it is instantiated like this
		if (vehicle != null) {

			if (vehicle.getCategory() == VehicleCategory.TRUCK) {
				vehicle = new DeliveryTruck(vehicle.getPlate(), vehicle.getCategory());
			} else {
				vehicle = new MonthlySubscriber(vehicle.getPlate(), vehicle.getCategory());
			}

			System.out.println("Vehicle with plate: " + vehicle.getPlate() + " is a " + vehicle.getCategory() + " and is registered.");
			
			return vehicle;

		//If it ising enrolled it is created like this
		} else {			
			System.out.println("Vehicle not registered.");
			System.out.print("Inform the vehicle category (CAR, MOTORCYCLE, PUBLIC): ");
			String type = sc.next().toUpperCase();
			VehicleCategory category = VehicleCategory.valueOf(type);

			if (category == VehicleCategory.PUBLIC) {
				return vehicle = new PublicService(plate, category);
			} else {
				return vehicle = new IndividualVehicle(plate, category);
			}
		}
		
	}
	
	//Instantiating vehicle 
	public static Vehicle instantiateVehicleforExit(String plate ) {
		Vehicle vehicle = accessVehicle.findVehicleByPlate(plate);
		boolean isRegistered = DaoFactory.createEnrolleesDaoJBDC().isEnrolleed(plate);
		
		if(isRegistered == true) {
			if (vehicle.getCategory() == VehicleCategory.TRUCK) {
			return vehicle = new DeliveryTruck(vehicle.getId(), plate, vehicle.getCategory());
		} else {
			return vehicle = new MonthlySubscriber(vehicle.getId(), plate, vehicle.getCategory());
		}

	} else {

		if (vehicle.getCategory() == VehicleCategory.PUBLIC) {
			return vehicle = new PublicService(vehicle.getId(), plate, vehicle.getCategory());
		} else {
			return vehicle = new IndividualVehicle(vehicle.getId(), plate, vehicle.getCategory());
		}

	}
		

	}

	//Default method enter, it is used as a base for the subclasses enter
//	public void enter(Vehicle vehicle, Timestamp arriveTimeStamp) {
//		
//		//List to receive all the parking slots when the method is called bellow
//		List<ParkingSlot> parkingSlotOptions;
//		List<Integer> availableSlotIds = new ArrayList<>();
//		
//		//Calling the entryBarrier service 
//		entryBarrier = BarrierService.validateEntryBarriers(vehicle);
//		this.choices = new int[vehicle.getSize()];
//
//		//If it is public it doenst occupie a slot
//		if (vehicle.getCategory() == VehicleCategory.PUBLIC) {
//			System.out.println("Entered on a special parking slot, not counted on the 500.");
//		} else {
//			//If it in't it shows the slots and calls the functions
//			System.out.println("Available slots: ");
//
//			//If the vehicle is an instance of montlhy subscriber it will show all the slots
//			//If not then it will show only the slots allowed for the general public
//			if (vehicle instanceof MonthlySubscriber) {
//				//The list receives the objects
//				parkingSlotOptions = accessParkingSlot.findByOccupied(false);
//			} else {
//				parkingSlotOptions = accessParkingSlot.findByOccupiedGeneral(false);
//			}
//			
//			parkingSlotOptions.forEach(System.out::println);
//			
//
//			//Here it makesthe person choose slots accourding to the vehicle size
////			for (int i = 0; i < vehicle.size; i++) {
////				int choice = 0;
////				System.out.println("Escolha a vaga " + (i+1));
////				choice = sc.nextInt();
////				
////				choices[i] = choice;
////				accessParkingSlot.occupieSlot(choice, vehicle.getId());
//
////			}
////			System.out.println("Slots occupied worked!");
//
//			
//			 // Here it makes the person choose slots according to the vehicle size
//	        for (int i = 0; i < vehicle.getSize(); i++) {
//	            int choice = 0;
//	            boolean validChoice = false;
//	            while (!validChoice) {
//	                System.out.println("Escolha a vaga " + (i + 1));
//	                choice = sc.nextInt();
//	                
//	                // Check if the choice is valid
//	                if (availableSlotIds.contains(choice)) {
//	                    validChoice = true;
//	                    choices[i] = choice;
//	                    accessParkingSlot.occupieSlot(choice, vehicle.getId());
//	                } else {
//	                    System.out.println("Escolha inválida. Por favor, escolha um número válido da lista.");
//	                }
//	            }
//	        }
//			
//		}
//
//	}
	
	public void enter(Vehicle vehicle, Timestamp arriveTimeStamp) {
	    
	    List<ParkingSlot> parkingSlotOptions;
	    Set<Integer> availableSlotIds = new HashSet<>(); // Para armazenar IDs de slots disponíveis

	    // Calling the entryBarrier service 
	    entryBarrier = BarrierService.validateEntryBarriers(vehicle);
	    this.choices = new int[vehicle.getSize()];

	    // If it is public it doesn’t occupy a slot
	    if (vehicle.getCategory() == VehicleCategory.PUBLIC) {
	        System.out.println("Entered on a special parking slot, not counted on the 500.");
	    } else {
	        // If it isn’t public, show the slots and call the functions
	        System.out.println("Available slots: ");

	        // If the vehicle is an instance of monthly subscriber, show all slots
	        // Otherwise, show only slots allowed for the general public
	        if (vehicle instanceof MonthlySubscriber) {
	            // The list receives the objects
	            parkingSlotOptions = accessParkingSlot.findByOccupied(false);
	        } else {
	            parkingSlotOptions = accessParkingSlot.findByOccupiedGeneral(false);
	        }
	        
	        // Print available slots and store their IDs
	        parkingSlotOptions.forEach(slot -> {
	            System.out.println(slot);
	            availableSlotIds.add(slot.getId());
	        });

	        // Here it makes the person choose slots according to the vehicle size
	        for (int i = 0; i < vehicle.getSize(); i++) {
	            int choice = 0;
	            boolean validChoice = false;
	            while (!validChoice) {
	                System.out.println("Choose the slot " + (i + 1));
	                choice = sc.nextInt();
	                
	                // Check if the choice is valid
	                if (availableSlotIds.contains(choice)) {
	                    validChoice = true;
	                    choices[i] = choice;
	                    accessParkingSlot.occupieSlot(choice, vehicle.getId());
	                } else {
	                    System.out.println("Invalid choice, please select one from the list.");
	                }
	            }
	        }

	        // Optionally, you can add a confirmation message
	        System.out.println("Slots occupied successfully!");

	    }
	}
	

	//Exit method, it is used as a base for the subclasses
	public void exit(Vehicle vehicle, Timestamp exitTimeStamp) {
		//Calling exitBarrier service
		exitBarrier = BarrierService.validateExitBarriers(vehicle);
		
		//Public does not free a slot because it did not occupie one 
		if (vehicle.getCategory() != VehicleCategory.PUBLIC) {
			accessParkingSlot.freeSlot(vehicle.getId());
		} 
		//They are all deleted to represent them leaving the parking slot
		accessVehicle.deleteVehicle(vehicle.getId());

	}
	

	@Override
	public String toString() {
		return "id: " + id + " plate: " + plate + ", Category: " + category + ", size: " + size;
	}

	//Charge methods made only to be used;
	public double  charge(int vehicleId, Timestamp exitStamp) {
		return 0;
	}

	public double charge(int vehicleId) {
		return 0;
	}


}

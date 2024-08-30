package model.entities;

import java.sql.Timestamp;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.EnrolleesDao;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.dao.impl.VehicleDaoJBDC;
import model.enums.VehicleCategory;
import model.services.BarrierService;

//Interface de Veiculos, que será implmenetada por tdos os veiculos
public abstract class Vehicle {
	
	VehicleDaoJBDC vehicleDao = DaoFactory.createVehicleDaoJBDC();
	EnrolleesDao registeredDao = DaoFactory.createEnrolleesDaoJBDC();
	ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDaoJBDC();

	static Scanner sc = new Scanner(System.in);

	private int id;
	private String plate;
	private VehicleCategory category;
	private int size;
	private int[] choices;
	int exitBarrier;
	int entryBarrier;
	

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

	public static Vehicle InstantiateVehicleForEntry(String plate) {
		Vehicle vehicle = DaoFactory.createEnrolleesDaoJBDC().FindEnrolleesByPlate(plate);

		if (vehicle != null) {

			if (vehicle.getCategory() == VehicleCategory.TRUCK) {
				vehicle = new DeliveryTruck(vehicle.getPlate(), vehicle.getCategory());
			} else {
				vehicle = new MonthlySubscriber(vehicle.getPlate(), vehicle.getCategory());
			}

			System.out.println("Vehicle with plate: " + vehicle.getPlate() + " is a " + vehicle.getCategory() + " and is registered.");
			
			return vehicle;

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
	
	public static Vehicle instantiateVehicleforExit(String plate ) {
		Vehicle vehicle = DaoFactory.createVehicleDaoJBDC().findVehicleByPlate(plate);
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

	public void enter(Vehicle vehicle, Timestamp arriveTimeStamp) {
		
		entryBarrier = BarrierService.validateEntryBarriers(vehicle);
		this.choices = new int[vehicle.getSize()];

		if (vehicle.getCategory() == VehicleCategory.PUBLIC) {
			System.out.println("Entered on a special parking slot, not counted on the 500.");
		} else {

			System.out.println("Available slots: ");

			if (vehicle instanceof MonthlySubscriber) {
				slot.findByOccupied(false);
			} else {
				slot.findByOccupiedGeneral(false);
			}

			//ESCOLHER VAGA();
			
			// Tamanho precisa de vagas para o tamanho dele.
			for (int i = 0; i < vehicle.size; i++) {
				int choice = 0;
				System.out.println("Escolha a vaga " + (i+1));
				choice = sc.nextInt();
				
				choices[i] = choice;
				slot.occupieSlot(choice, vehicle.getId());

			}
			System.out.println("Slots occupied worked!");

		}

	}

	public void exit(Vehicle vehicle, Timestamp exitTimeStamp) {
		
		System.out.println("Leaving by the barrier: ");
		exitBarrier = BarrierService.validateExitBarriers(vehicle);
		
//		if(vehicle instanceof IndividualVehicle) {
//			System.out.println("Avulso");
//		} else if(vehicle instanceof DeliveryTruck) {
//			System.out.println("Truck");
//		} else if(vehicle instanceof PublicService) {
//			System.out.println("Public");
//		} else if(vehicle instanceof MonthlySubscriber) {
//			System.out.println("Mensalista");
//		}
		
//		System.out.println();
//		BarrierService.validateExitBarriers(vehicle);

		//Se ele não for publico ele libera uma vaga, já que os públicos não ocupam
		if (vehicle.getCategory() != VehicleCategory.PUBLIC) {
			slot.freeSlot(vehicle.getId());
		} 
		
		vehicleDao.deleteVehicle(vehicle.getId());

	}
	
//	public abstract double charge(int vehicleId);

	@Override
	public String toString() {
		return "id: " + id + " plate: " + plate + ", Category: " + category + ", size: " + size;
	}
//
	public double charge(int vehicleId, Timestamp exitStamp) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double charge(int vehicleId) {
		// TODO Auto-generated method stub
		return 0;
	}


}

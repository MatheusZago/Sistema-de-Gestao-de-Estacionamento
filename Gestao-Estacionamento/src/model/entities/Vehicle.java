package model.entities;

import java.time.LocalDateTime;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.dao.impl.VehicleDaoJBDC;
import model.enums.VehicleCategory;
import model.services.BarrierService;

//Interface de Veiculos, que será implmenetada por tdos os veiculos
public abstract class Vehicle {
	
	VehicleDaoJBDC vehicleDao = DaoFactory.createVehicleDaoJBDC();

	Scanner sc = new Scanner(System.in);

	private int id;
	private String plate;
	private VehicleCategory category;
	private int size;
	ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDao();
//	ParkedDaoJBDC parked = DaoFactory.createParkedDaoJBDC();

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

	public void enter(Vehicle vehicle, LocalDateTime dateTime) {
		// Passo 3, ver qual o tipo do carro para saber qual catraca ele passa (FEITO)
//		System.out.println("Enter by the barriers: ");
//		BarrierService.validateEntryBarriers(vehicle);

		if (vehicle.getCategory() == VehicleCategory.PUBLIC) {
			System.out.println("Entered on a special parking slot, not counted on the 500.");
		} else {

			System.out.println("Available slots: ");

			if (vehicle instanceof MonthlySubscriber) {

				slot.findByOccupied(false);
			} else {
				slot.findByOccupiedGeneral(false);
			}

			int choice = 0;

			// Tamanho precisa de vagas para o tamanho dele.
			for (int i = 1; i <= vehicle.size; i++) {
				System.out.println("Escolha a vaga " + i);
				choice = sc.nextInt();
				slot.occupieSlot(choice, vehicle.getId());
//				parked.insert(vehicle, dateTime, choice);

			}
			System.out.println("Insert in Parked worked!");

		}

	}

	public void exit(Vehicle vehicle, LocalDateTime time) {
		System.out.println();
		System.out.println("Leaving by the barrier: ");
		BarrierService.validateExitBarriers(vehicle);

		if (vehicle.getCategory() != VehicleCategory.PUBLIC) {
			slot.freeSlot(vehicle.getId());
			vehicleDao.deleteVehicle(vehicle.getId());

		} else {
			System.out.println("Left.");
		}

	}

	@Override
	public String toString() {
		return "id: " + id + " plate: " + plate + ", Category: " + category + ", size: " + size;
	}

}

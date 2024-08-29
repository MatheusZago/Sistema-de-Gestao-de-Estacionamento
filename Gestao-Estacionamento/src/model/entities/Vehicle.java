package model.entities;

import java.time.LocalDateTime;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.impl.ParkedDaoJBDC;
import model.dao.impl.ParkingSlotDaoJBDC;
import model.enums.VehicleCategory;
import model.services.BarrierService;

//Interface de Veiculos, que será implmenetada por tdos os veiculos
public abstract class Vehicle {

	Scanner sc = new Scanner(System.in);

	private String plate;
	private VehicleCategory category;
	private int size;

	public Vehicle() {

	}

	public Vehicle(String placa) {
		this.plate = placa;
	}

	public Vehicle(String plate, VehicleCategory category) {
		this.plate = plate;
		this.category = category;
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

	public void enter(Vehicle vehicle, LocalDateTime time) {

		// Passo 3, ver qual o tipo do carro para saber qual catraca ele passa (FEITO)
		System.out.println("Enter by the barriers: ");
		BarrierService.validateEntryBarriers(vehicle);

		if (vehicle.getCategory() == VehicleCategory.PUBLIC) {
			// Passo 4.1) Veiculos publicos não ocupam vagas.
			System.out.println("Entered on a special parking slot, not counted on the 500.");
		} else {
			// Passo 4.2) Veiculo entrou, atualizando X vagas para ocupado e criando na
			// tabela de parking
			System.out.println("Available slots: ");
			ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDao();

			if (vehicle instanceof MonthlySubscriber) {

				slot.findByOccupied(false);
			} else {
				slot.findByOccupiedGeneral(false);
			}

			int choice = 0;
//			slot.updateSlot(true, choice);

			// Tamanho precisa de vagas para o tamanho dele.
			for (int i = 1; i <= vehicle.size; i++) {
				System.out.println("Escolha a vaga " + i);
				choice = sc.nextInt();
				// UPDATE NO PARKING SLOT
				slot.occupieSlot(choice, vehicle.getPlate());
				// AQUI VC CRIA FAZ UM INSERT NO BANCO DE DADOS PARKED
				ParkedDaoJBDC parked = DaoFactory.createParkedDaoJBDC();
				parked.insert(vehicle, time, choice);

			}
			System.out.println("Insert in Parked worked!");

		}

	}

	public void exit(Vehicle vehicle, Parked parkedVehicle, LocalDateTime time) {
		System.out.println("Chamou a logica de saida");
		ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDao();
		ParkedDaoJBDC parked = DaoFactory.createParkedDaoJBDC();
		
		System.out.println("Leaving by the barrier: ");
		BarrierService.validateExitBarriers(vehicle);
		
		if(vehicle.getCategory() != VehicleCategory.PUBLIC) {
			slot.freeSlot(parkedVehicle.getPlate());
			parked.remove(vehicle);
			
		}
		

	}

	@Override
	public String toString() {
		return "Plate: " + plate + ", Category: " + category + ", size: " + size;
	}

}

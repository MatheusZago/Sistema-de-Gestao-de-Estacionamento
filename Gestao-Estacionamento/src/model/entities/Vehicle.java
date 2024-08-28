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
	
		//Passo 3, ver qual o tipo do carro para saber qual catraca ele passa (FEITO)
		System.out.println("Enter by the barriers: ");
		BarrierService.validateEntryBarriers(vehicle);
		
		
		//Passo 4) Criar a adentrada do viculo 
		System.out.println("Chosse a slot to park: ");
		ParkingSlotDaoJBDC slot = DaoFactory.createParkingSlotDao();
		slot.findByOccupied(false);
		int choice = sc.nextInt();
		slot.updateSlot(true, choice);
		
		//AQUI VC CRIA FAZ UM INSERT NO BANCO DE DADOS PARKED
		ParkedDaoJBDC parked = DaoFactory.createParkedDaoJBDC();
		parked.insert(vehicle, time, choice);
		
	}
	
	

	public void exit(Vehicle veiculo) {
		
//		System.out.println("Sair pelas catracas: ");
//		
//		ServicoCancela.validacaoCatracasSaida(veiculo);
	}

}

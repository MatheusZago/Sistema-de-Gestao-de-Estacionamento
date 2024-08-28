package model.entities;

import java.util.Scanner;

import model.enums.VehicleCategory;
import model.services.BarrierService;

//Interface de Veiculos, que será implmenetada por tdos os veiculos
public abstract class Vehicle {

	Scanner sc = new Scanner(System.in);

	private String plate;
	private VehicleCategory category;

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

	public void enter(Vehicle vehicle) {
	
		//Passo 3, ver qual o tipo do carro para saber qual catraca ele passa (FEITO)
		System.out.println("Enter by the barriers: ");

		BarrierService.validateEntryBarriers(vehicle);

	}

	public void exit(Vehicle veiculo) {
		
//		System.out.println("Sair pelas catracas: ");
//		
//		ServicoCancela.validacaoCatracasSaida(veiculo);
	}

}

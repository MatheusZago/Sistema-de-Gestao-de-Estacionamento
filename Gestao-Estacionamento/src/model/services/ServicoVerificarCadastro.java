package model.services;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.entities.IndividualVehicle;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.PublicService;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

public class ServicoVerificarCadastro {

	static Scanner sc = new Scanner(System.in);

	public static Vehicle verifyRegister(String plate) {
		Vehicle vehicle = DaoFactory.createVehicleDao().FindRegisteredByPlate(plate);

		if (vehicle != null) {
			System.out.println("Vehicle with plate " + vehicle.getPlate() + " is a " + vehicle.getCategory()
					+ " and is registered.");

			if (vehicle.getCategory() == VehicleCategory.TRUCK) {
				vehicle = new DeliveryTruck(vehicle.getPlate(), vehicle.getCategory());
			} else {
				vehicle = new MonthlySubscriber(vehicle.getPlate(), vehicle.getCategory());
			}

			return vehicle;

		}

		
		//TODO adicionar um erro caso tente entrar com caminhão não registrado.
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
		return vehicle;

	}

}

package model.services;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

public class VerifyRegisterService {

	static Scanner sc = new Scanner(System.in);

	public static Vehicle verifyRegister(String plate) {
		Vehicle vehicle = DaoFactory.createRegisteredDao().FindRegisteredByPlate(plate);

		if (vehicle != null) {

			if (vehicle.getCategory() == VehicleCategory.TRUCK) {
				vehicle = new DeliveryTruck(vehicle.getPlate(), vehicle.getCategory());
			} else {
				vehicle = new MonthlySubscriber(vehicle.getPlate(), vehicle.getCategory());
			}

			return vehicle;

		} else {
			return null;
		}

	}

}

package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DbException;
import model.dao.VehicleDao;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.Vehicle;
import model.enums.VehicleCategory;

//Usado para Implementar o acesso de dados comJBDC para Veiculos
public class VehicleDaoJBDC implements VehicleDao {

	private Connection conn = null; 
	PreparedStatement st = null;
	ResultSet rs = null;

	// Construtor pra pegar a conexão.
	public VehicleDaoJBDC(Connection conn) {
		this.conn = conn;
	}

	// Para inserir
	@Override
	public void insert(Vehicle vehicle) {
		try {
			st = conn.prepareStatement("INSERT INTO registered (plate, category) VALUES (?, ?); ");

			st.setString(1, vehicle.getPlate());
			st.setString(2, vehicle.getCategory().name()); // Esse .name ta transformando o enum em String

			st.executeUpdate();

			System.out.println("Vehicle registered with success!");

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

	}

	// USAR ISSO PRA VER SE EXISTE NA TABELA CADASTRADA
//	@Override Ajeitar Override dps
	public Vehicle FindRegisteredByPlate(String plate) {
		try {
			st = conn.prepareStatement("SELECT * FROM registered WHERE plate = ?");

			st.setString(1, plate);
			rs = st.executeQuery();

			// Transformando o resultSet em objeto java
			if (rs.next()) {

				String returnedPlate = rs.getString("plate");
				String stringCategory = rs.getString("category");
//				//Transformando a String em Enum
				VehicleCategory returnedCategory = VehicleCategory.valueOf(stringCategory.toUpperCase());

				Vehicle registered;

				// De acordo com a categoria retornada ele devolve um objeto diferente
				if (returnedCategory == VehicleCategory.TRUCK) {
					registered = new DeliveryTruck(returnedPlate, returnedCategory);
					return registered;
				} else if (returnedCategory == VehicleCategory.CAR || returnedCategory == VehicleCategory.MOTORCYCLE) {
					registered = new MonthlySubscriber(returnedPlate, returnedCategory);
					return registered;
				} else {
					return null;
				}

			}

		} catch (SQLException e) {
			throw new DbException("Error: " + e.getMessage());
		}

		return null;
	}
	
//	@Override
//	public Vehicle instantiateParkedVehicleByPlate(String plate) {
//		ParkedDaoJBDC parked = DaoFactory.createParkedDaoJBDC();
//		Vehicle vehicle = parked.findByPlate(plate); //recebendo o veiculo achado		
//		
//		
//		//Se ele for um veiculo registrado
//		if(VerifyRegisterService.isRegistered(plate) == true) {
//			VerifyRegisterService.verifyRegister(vehicle.getPlate());
//
//			if(vehicle.getCategory() == VehicleCategory.TRUCK) {
//				//INSTANCIAR UM CAMINHÃO DE ETNREGA
//				
//				
//			} else {
//				//Instanciar um mensalista
//			}
//		
//		} else {//Se não for registrado
//			if(vehicle.getCategory() == VehicleCategory.PUBLIC) {
//				//INSTANCIAR UM SERVIÇO PUBLICO
//			} else {
//				//Instanciar um Avulso
//			}
//		}
//		
//		
//
//		
//		
//		
//
////		System.out.println(vehicle);
//		
//		
//		return null;
//		
//		
//		
//	}

}

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
	
	public static Vehicle verificarCadastro(String placa) {
		Vehicle veiculo = DaoFactory.createVehicleDao().FindRegisteredByPlate(placa);

		if (veiculo != null) {
			System.out.println(
					"Veiculo de placa " + veiculo.getPlate() + " é um(a) " + veiculo.getCategory() + " cadastrado(a)");
			
			if(veiculo.getCategory() == VehicleCategory.TRUCK) {
				veiculo = new DeliveryTruck(veiculo.getPlate(), veiculo.getCategory());
			} else {
				veiculo = new MonthlySubscriber(veiculo.getPlate(), veiculo.getCategory());
			}

			return veiculo;
			
		} 
//		else { //NÃO PRECISA DE ELSE PQ O RETURNO IRIA FINALIZAR ESSA FUNÇÃO
			// Se o if falhar entao o veiculo não é cadastrado, então ele será instanciado
			// aqui, pedindo seu tipo de veiculo
			// Não é necessário pedir o tipo de veiculo se já estiver cadastrado, pois ele
			// vai pegar o tipo do BD por si próprio.
			System.out.println("Veiculo não cadastrado.");
			System.out.print("Informe a categoria (CARRO, MOTO, CAMINHAO, PUBLICO): ");
			String tipo = sc.next().toUpperCase();
			VehicleCategory modelo = VehicleCategory.valueOf(tipo);

//			Veiculo veiculo;

			if (modelo == VehicleCategory.PUBLIC) {
				veiculo = new PublicService(placa, modelo);
			} else {
				veiculo = new IndividualVehicle(placa, modelo);
			}

			System.out.println("Seu veiculo de placa " + veiculo.getPlate() + " é um(a) " + veiculo.getCategory());
			return veiculo;
		
		//Se tudo falhar retorna nulo
//		return null;

//		System.out.println("Entrar pelas catracas: ");
//
//		ServicoCancela.validacaoCatracasEntrada(veiculo);
	}
	

}

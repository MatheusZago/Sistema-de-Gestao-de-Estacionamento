package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.entities.DeliveryTruck;
import model.entities.MonthlySubscriber;
import model.entities.Vehicle;
import model.enums.VehicleCategory;
import model.services.ServicoVerificarCadastro;

//Classe principal da aplicação
public class App {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		boolean test = true;

		try {

			//Foi usado do while para que o programa execute pelo menos uma vez.
			do {
				{
					System.out.println("=====CHOOSE AN OPTION=====");
					System.out.println("1) Register entrance");
					System.out.println("2) Register exit");
					System.out.println("3) Register Vehicle");
					System.out.println("4) Exit");

					int choice = sc.nextInt();

					switch (choice) {
					case 1: {
						try {
				            // Passo 1, construir o carro que quer entrar (FEITO)
				            System.out.println();
				            System.out.println("Let's register your entrance!");
				            System.out.print("Enter the plate number: ");
				            String plate = sc.next().toUpperCase();                        
				            
				            // 2 ) VERIFICAR SE O VEICULO ESTÁ CADASTRADO: (FEITO)
				            Vehicle vehicle = ServicoVerificarCadastro.verifyRegister(plate);
				            if (vehicle == null) {
				                System.out.println("Vehicle not found.");
				                continue; //Pra tentar continuar sem quebrar
				            }
				            
				            System.out.println(vehicle.getSize());
				            
				            // Está pegando a data e usando o formater pra deixar da forma certa
				            System.out.println("Enter the date for your arrival (dd/MM/yyyy HH:mm)");
				            sc.nextLine();
				            String dateTimeInput = sc.nextLine();
				            
				            LocalDateTime arriveDateTime;
				            try {
				                arriveDateTime = LocalDateTime.parse(dateTimeInput, dtf);
				            } catch (DateTimeParseException e) {
				                System.out.println("Invalid date format. Please use the format dd/MM/yyyy HH:mm");
				                return;
				            }

				            System.out.println("Date and time parsed successfully: " + arriveDateTime);
				            
				            // Passo 3, ver qual o tipo do carro para saber qual catraca ele passa (FEITO)
				            vehicle.enter(vehicle, arriveDateTime);
				            
				        } catch (Exception e) {
				            e.printStackTrace();
				        }

					
						
						
						//Passo 4, fazer a entrada do carro, se for avulso tem que ter ticket se não não precisa
						//Passo 5, Adicionar no banco de dados "estacionamento" o carro e as vagas que ele ocupa
						
						break;
					}
					case 2: {
						System.out.println();
						System.out.println("Let's register your exit!");
//						System.out.println("Digite a placa do veiculo: ");
//						String placa = sc.next().toUpperCase();
//						System.out.println();
//						
//						Veiculo veiculo = ServicoVerificarCadastro.verificarCadastro(placa);
//						
//						if(veiculo != null) {
//							
//							veiculo.sair(veiculo);
//						}
						
						
						
						break;
					}
					case 3: {
						// AQUI TEM QUE CHMAR A FUNÇÃO QUE CADASTRA
						DaoFactory.createVehicleDao();
						System.out.print("Enter vehicle plate: ");
						String plate = sc.next();
						//TODO, dar um erro para evitar placas repetidas sem quebrar o programa.
						System.out.print("Category (CAR, MOTORCYCLE, TRUCK): ");
						String type = sc.next().toUpperCase();
						VehicleCategory model = VehicleCategory.valueOf(type);
						
						Vehicle registered;

						//Ta criando veiculos com cash de acordo com o tipo deles
						if (model == VehicleCategory.TRUCK) {
							registered = new DeliveryTruck(plate, model);
							((DeliveryTruck) registered).register(registered); //Ta especificando qual é.
						} else if(model == VehicleCategory.CAR || model == VehicleCategory.MOTORCYCLE) {
							registered = new MonthlySubscriber(plate, model);
							((MonthlySubscriber) registered).register(registered); //Ta especificando qual é
						} else {
							//TODO ajeitar para usar um erro
							registered = null;
							System.out.println("Invalid vehicle, try again.");
						}
						
						System.out.println(registered.getSize());

						break;
					}
					case 4: {
						System.out.println("Thank you for using the system!");
						test = false;
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + choice);
					}

					// Apenas para pular linha
					System.out.println();

				}
			}

			while (test);

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		finally {
			sc.close();
		}

	}

}

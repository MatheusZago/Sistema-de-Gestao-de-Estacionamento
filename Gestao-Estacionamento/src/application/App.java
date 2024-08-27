package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.entities.CaminhaoEntrega;
import model.entities.Mensalista;
import model.entities.VeiculoCadastrado;
import model.enums.TipoVeiculo;

//Classe principal da aplicação
public class App {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean teste = true;

		try {

			//Foi usado do while para que o programa execute pelo menos uma vez.
			do {
				{
					System.out.println("=====ESCOLHA OPÇÃO=====");
					System.out.println("1) Registrar entrada");
					System.out.println("2) Registrar saida");
					System.out.println("3) Cadastrar veiculo");
					System.out.println("4) Sair");

					int escolha = sc.nextInt();

					switch (escolha) {
					case 1: {
						System.out.println("Vamos registrar sua entrada!");
						break;
					}
					case 2: {
						System.out.println("Vamos registrar sua saida!");
						break;
					}
					case 3: {
						// AQUI TEM QUE CHMAR A FUNÇÃO QUE CADASTRA
						DaoFactory.criarVeiculoDao();
						System.out.print("Digite a placa do veiculo: ");
						String placa = sc.next();
						System.out.print("Categoria (CARRO, MOTO, CAMINHAO, PUBLICO): ");
						String tipo = sc.next().toUpperCase();
						TipoVeiculo modelo = TipoVeiculo.valueOf(tipo);

						// Ta criando um objeto veiculo cadastrado
						VeiculoCadastrado cadastrado;

						if (modelo == TipoVeiculo.CAMINHAO) {
							cadastrado = new CaminhaoEntrega(placa, modelo);
						} else {
							cadastrado = new Mensalista(placa, modelo);
						}

						cadastrado.cadastrar(cadastrado);

						break;
					}
					case 4: {
						System.out.println("Obrigado por usar o sistema!");
						teste = false;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + escolha);
					}

					// Apenas para pular linha
					System.out.println();

				}
			}

			while (teste);

		} catch (RuntimeException e) {
			e.getStackTrace();
		} finally {
			sc.close();
		}

	}

}

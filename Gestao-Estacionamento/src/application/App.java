package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.entities.CaminhaoEntrega;
import model.entities.Mensalista;
import model.entities.ServicoPublico;
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
						//Passo 1, construir o carro que quer entrar
						DaoFactory.criarVeiculoDao();
						System.out.print("Digite a placa do veiculo: ");
						String placa = sc.next();
						System.out.print("Categoria (CARRO, MOTO, CAMINHAO, PUBLICO): ");
						String tipo = sc.next().toUpperCase();
						TipoVeiculo modelo = TipoVeiculo.valueOf(tipo);
						
						//1.1 ) VERIFICAR SE O VEICULO ESTÁ CADASTRADO: 
						ServicoPublico test = new ServicoPublico(placa, modelo);
//						System.out.println(test);
//						VeiculoDaoJBDC./
						
						test.entrar(test.getPlaca());
						
//						if(modelo == TipoVeiculo.PUBLICO) {
//							ServicoPublico publico = new ServicoPublico(placa, modelo);
//						} else if(modelo == TipoVeiculo.CAMINHAO) {
//							CaminhaoEntrega caminhao = new CaminhaoEntrega(placa, modelo);
//						}
						
						
						//Passo 2, ver qual o tipo do carro para saber qual catraca ele passa
						//Passo 3, ver se o carro é cadastrado 
						//Passo 4, fazer a entrada do carro, se for avulso tem que ter ticket se não não precisa
						//Passo 5, Adicionar no banco de dados "estacionamento" o carro e as vagas que ele ocupa
						
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

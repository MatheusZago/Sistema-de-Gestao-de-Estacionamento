package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.entities.CaminhaoEntrega;
import model.entities.Mensalista;
import model.entities.VeiculoCadastrado;
import model.enums.TipoVeiculo;

public class App {

	public static void main(String[] args) {

		// Teste simples para confirmar a conexão com o banco de dados.

		Connection conn = null;
		PreparedStatement st = null;
		Scanner sc = new Scanner(System.in);

		boolean teste = true;

		try {

			while (teste) {

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
				}case 2: {
					System.out.println("Vamos registrar sua saida!");
					break;
				}case 3: {
					//AQUI TEM QUE CHMAR A FUNÇÃO QUE CADASTRA
					DaoFactory.criarVeiculoDao();
					System.out.print("Digite a placa do veiculo: ");
					String placa = sc.next();
					System.out.print("Categoria (CARRO, MOTO, CAMINHAO, PUBLICO): ");
					String tipo = sc.next().toUpperCase();
					TipoVeiculo modelo = TipoVeiculo.valueOf(tipo);
					
					//Ta criando um objeto veiculo cadastrado 
					VeiculoCadastrado cadastrado;
					
					if(modelo == TipoVeiculo.CAMINHAO) {
						 cadastrado = new CaminhaoEntrega(placa, modelo);
					} else {
						cadastrado = new Mensalista(placa, modelo);
					}
					
					cadastrado.cadastrar(cadastrado);
					
					break;
				}case 4: {
					System.out.println("Obrigado por usar o sistema!");
					teste = false;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + escolha);
				}
				
				
				
				
			}

		} catch (RuntimeException e) {
			e.getStackTrace();
		}

//		try {
//			conn = DB.getConnection();
//			//Colocando autoCommit falso como boa prática.
//			conn.setAutoCommit(false);
//			
//			
//			//AJEITAR, ISSO TUDO É SÓ PARA TESTE
//			System.out.print("Entre com a placa do carro: ");
//			String placa = sc.nextLine();
//			System.out.println("Entre com o tipo: (CARRO/MOTO/CAMINHAO/PUBLICO) ");
//			String tipo = TipoVeiculo.valueOf(sc.next()).name();
//			
//			st = conn.prepareStatement("INSERT INTO cadastrados (placa, tipo) VALUES (?, ?)");
//			st.setString(1, placa);
//			st.setString(2, tipo); //passando o Enum pra string
//			
//			int rowsAffected = st.executeUpdate();
//			conn.commit();
//			System.out.println("Done! Rows affected: " + rowsAffected);
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DB.closeStatement(st);
//			DB.closeConnection();
//			sc.close();
//		}

	}

}

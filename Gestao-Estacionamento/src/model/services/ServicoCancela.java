package model.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Veiculo;

public class ServicoCancela {
	
	static Scanner sc = new Scanner(System.in);
	
	//Serviços para fazer a questão de validação de qual cancela pode ser usada por qual veiculo
	public static void validacaoCatracasEntrada(Veiculo veiculo) {
		
		String modelo = veiculo.getModelo().name(); //Transformando TipoModelo em String 
		String opcoes;
		String[] arrayOpcoes;
		int escolha = 0;
		boolean opcaoValida = false;
		
		switch (modelo) {
		case "CARRO": 
		case "PUBLICO": {
			opcoes = "1 | 2 | 3 | 4 | 5";
			arrayOpcoes = new String[] {"1", "2", "3", "4", "5"};
			break;
		} case "MOTO": {
			opcoes = "5";
			arrayOpcoes = new String[] {"5"};
			break;
		} case "CAMINHAO": {
			arrayOpcoes = new String[] {"1"};
			opcoes = "1";
			break;
		} 
		default:
			throw new IllegalArgumentException("Unexpected value: " + modelo);
		}
		
		System.out.println();
		System.out.println("ESCOLHA A CANCELA PARA ENTRAR ");
		
		do {
			System.out.println("Um(a) " + modelo + " pode entrar pela(s) cancela(s): " + opcoes );
			
			try {
				escolha = sc.nextInt();
				
				// Aqui verifica se a escolha está nas opções válidas
				opcaoValida = false; // Reinicia a validação para cada nova tentativa
				
				for (String opcao : arrayOpcoes) {
					if (opcao.equals(String.valueOf(escolha))) {
						opcaoValida = true;
						break;
					}
				}
				
				if (opcaoValida) {
					System.out.println("Passou pela catraca " + escolha);
				} else {
					System.out.println("Escolha entre uma catraca própria para " + modelo);
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, digite um número.");
				sc.next(); // Limpa o buffer do scanner
			}
						
		} while (opcaoValida == false);
		
		System.out.println("Passou pela cancela " + escolha);
		
		
	}
	
public static void validacaoCatracasSaida(Veiculo veiculo) {
		
		String modelo = veiculo.getModelo().name(); //Transformando TipoModelo em String 
		String opcoes;
		String[] arrayOpcoes;
		int escolha;
		boolean opcaoValida = false;
		
		switch (modelo) {
		case "CARRO": 
		case "PUBLICO":
		case "CAMINHAO": {
			opcoes = "6 | 7 | 8 | 9 | 10";
			arrayOpcoes = new String[] {"6", "7", "8", "9", "10"};
			break;
		} case "MOTO": {
			opcoes = "10";
			arrayOpcoes = new String[] {"5"};
			break;
		} 
		default:
			throw new IllegalArgumentException("Unexpected value: " + modelo);
		}
		
		System.out.println();
		System.out.println("ESCOLHA A CANCELA PARA SAIR ");
		
		do {
			System.out.println("Um(a) " + modelo + " pode sair pela(s) cancela(s): " + opcoes );
			
			escolha = sc.nextInt();

			//Aqui ele vai percorrer o Array para ver se o numero digite tem nas opções válidas
			for(String opcao : arrayOpcoes) {
				if(opcao.equals(String.valueOf(escolha))) {
					opcaoValida = true;
					break;
				} else if(escolha > 5 || escolha < 1) {
					System.out.println("Escolha entre uma cancela existente.");
				} else if(opcaoValida == false ) {
					System.out.println("Escolha entre uma cancela própria para " + veiculo.getModelo());
				} else {
					//TODO arrumar um jeito de não travar
					System.out.println("Valor invalido, tente novamente por favor.");
				}
			}
						
		} while (opcaoValida == false);
		
		System.out.println("Saiu pela cancela " + escolha);
		
		
	}
	
	

}

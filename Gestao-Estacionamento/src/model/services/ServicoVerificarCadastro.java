package model.services;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.entities.Avulso;
import model.entities.CaminhaoEntrega;
import model.entities.Mensalista;
import model.entities.ServicoPublico;
import model.entities.Veiculo;
import model.enums.TipoVeiculo;

public class ServicoVerificarCadastro {
	
	static Scanner sc = new Scanner(System.in);
	
	public static Veiculo verificarCadastro(String placa) {
		Veiculo veiculo = DaoFactory.criarVeiculoDao().FindCadastroByPlaca(placa);

		if (veiculo != null) {
			System.out.println(
					"Veiculo de placa " + veiculo.getPlaca() + " é um(a) " + veiculo.getModelo() + " cadastrado(a)");
			
			if(veiculo.getModelo() == TipoVeiculo.CAMINHAO) {
				veiculo = new CaminhaoEntrega(veiculo.getPlaca(), veiculo.getModelo());
			} else {
				veiculo = new Mensalista(veiculo.getPlaca(), veiculo.getModelo());
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
			TipoVeiculo modelo = TipoVeiculo.valueOf(tipo);

//			Veiculo veiculo;

			if (modelo == TipoVeiculo.PUBLICO) {
				veiculo = new ServicoPublico(placa, modelo);
			} else {
				veiculo = new Avulso(placa, modelo);
			}

			System.out.println("Seu veiculo de placa " + veiculo.getPlaca() + " é um(a) " + veiculo.getModelo());
			return veiculo;
		
		//Se tudo falhar retorna nulo
//		return null;

//		System.out.println("Entrar pelas catracas: ");
//
//		ServicoCancela.validacaoCatracasEntrada(veiculo);
	}
	

}

package model.entities;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.enums.TipoVeiculo;
import model.services.ServicoCancela;

//Interface de Veiculos, que será implmenetada por tdos os veiculos
public abstract class Veiculo {

	Scanner sc = new Scanner(System.in);

	private String placa;
	private TipoVeiculo modelo;

	public Veiculo(String placa) {
		this.placa = placa;
	}

	public Veiculo(String placa, TipoVeiculo modelo) {
		this.placa = placa;
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVeiculo getModelo() {
		return modelo;
	}

	public void setModelo(TipoVeiculo modelo) {
		this.modelo = modelo;
	}

	public void entrar(String placa) {

		Veiculo veiculo = DaoFactory.criarVeiculoDao().FindCadastroByPlaca(placa);

		if (veiculo != null) {
			System.out.println(
					"Veiculo de placa " + veiculo.getPlaca() + " é um(a) " + veiculo.getModelo() + " cadastrado(a)");

		} else {
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
		}

		System.out.println("Entrar pelas catracas: ");

		ServicoCancela.validacaoCatracasEntrada(veiculo);

	}

	public abstract void sair();

}

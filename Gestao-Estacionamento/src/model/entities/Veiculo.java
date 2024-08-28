package model.entities;

import java.util.Scanner;

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

	public void entrar(Veiculo veiculo) {
	
		//Passo 3, ver qual o tipo do carro para saber qual catraca ele passa (FEITO)
		System.out.println("Entrar pelas catracas: ");

		ServicoCancela.validacaoCatracasEntrada(veiculo);

	}

	public void sair(Veiculo veiculo) {
		
//		System.out.println("Sair pelas catracas: ");
//		
//		ServicoCancela.validacaoCatracasSaida(veiculo);
	}

}

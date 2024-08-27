package model.entities;

import model.enums.TipoVeiculo;

//Interface de Veiculos, que será implmenetada por tdos os veiculos
public abstract class Veiculo {


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
	
	
	
	
	public abstract void entrar(String placa);
	public abstract void sair();
	

}

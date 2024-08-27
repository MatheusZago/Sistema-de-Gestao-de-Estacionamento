package model.entities;

import model.enums.TipoVeiculo;

//Classe para representar os veiculos avulsos, não cadastrados.
public class Avulso implements Veiculo{
	
	private String placa;
	private TipoVeiculo modelo;
	
	
	

	public Avulso(String placa, TipoVeiculo modelo) {
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


	@Override
	public void entrar() {
		
		
	}

	@Override
	public void sair() {
		// TODO Auto-generated method stub
		
	}

}

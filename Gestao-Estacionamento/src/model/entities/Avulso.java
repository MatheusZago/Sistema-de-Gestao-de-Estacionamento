package model.entities;

import model.enums.TipoVeiculo;

//Classe para representar os veiculos avulsos, não cadastrados.
public class Avulso extends VeiculosNaoCadastrado{
	
	//Ppegando construtor da classe abstrata
	public Avulso(String placa, TipoVeiculo modelo) {
		super(placa, modelo);
	}


	



	@Override
	public void entrar(String placa) {
		
		
	}

	@Override
	public void sair() {
		// TODO Auto-generated method stub
		
	}

}

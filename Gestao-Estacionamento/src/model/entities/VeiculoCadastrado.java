package model.entities;

import model.dao.DaoFactory;

//Classe abstrata para os Veiculos Cadastrados, implementa Veiculo e adiciona metos que todos terão.
public interface VeiculoCadastrado {
	
	//Dps vê se é possível
	public default void cadastrar(Veiculo veiculo) {
		DaoFactory.criarVeiculoDao().insert(veiculo);
	}
	
	
}

package model.dao;

import model.entities.Veiculo;

//Interface para a implementação de possíveis modosDAO, no caso do projeto só tem JBDC mas ta aqui por boa prática
public interface VeiculoDao {
	
	void insert(Veiculo veiculo);
	Veiculo FindCadastroByPlaca(String placa);
//	void update();
//	void deleteByPlaca();
	//DPS BOAAR OS OUTROS
	
}

package model.dao;

import model.entities.VeiculoCadastrado;

//Interface para a implementação de possíveis modosDAO, no caso do projeto só tem JBDC mas ta aqui por boa prática
public interface VeiculoDao {
	
	void insert(VeiculoCadastrado veiculo);
	VeiculoCadastrado searchCadastroByPlaca(String placa);
//	void update();
//	void deleteByPlaca();
	//DPS BOAAR OS OUTROS
	
}

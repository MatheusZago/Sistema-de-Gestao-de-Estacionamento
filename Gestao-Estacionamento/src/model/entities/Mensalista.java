package model.entities;

import model.dao.DaoFactory;
import model.enums.TipoVeiculo;

//Classe para representar os mensalistas, que implmeneta veiculos e herda de Veiculos Cadastrados
public class Mensalista extends VeiculoCadastrado{



	public Mensalista(String placa, TipoVeiculo modelo) {
		super(placa, modelo);
	}



	@Override
	public void entrar(String placa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sair() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cadastrar(VeiculoCadastrado veiculo) {
		DaoFactory.criarVeiculoDao().insert(veiculo);;
		
	}

}

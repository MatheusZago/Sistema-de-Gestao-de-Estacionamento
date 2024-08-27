package model.entities;

import model.dao.DaoFactory;
import model.enums.TipoVeiculo;

//Classe para representar o Caminhão que entrega, que implementa veiculos e herda de veiculos cadastrados
public class CaminhaoEntrega extends Veiculo implements VeiculoCadastrado{


	public CaminhaoEntrega(String placa, TipoVeiculo modelo) {
		super(placa, modelo);
	}

//	@Override
	public void cadastrar(Veiculo veiculo) {
		
		DaoFactory.criarVeiculoDao().insert(veiculo);;
		
	}	

	@Override
	public void entrar(String placa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sair() {
		// TODO Auto-generated method stub
		
	}

}

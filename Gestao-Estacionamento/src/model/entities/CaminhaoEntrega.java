package model.entities;

import model.dao.DaoFactory;
import model.enums.TipoVeiculo;

public class CaminhaoEntrega extends VeiculoCadastrado{



	public CaminhaoEntrega(String placa, TipoVeiculo modelo) {
		super(placa, modelo);
		// TODO Auto-generated constructor stub
	}

//	@Override
	public void cadastrar(VeiculoCadastrado veiculo) {
		
		DaoFactory.criarVeiculoDao().insert(veiculo);;
		
	}

	@Override
	public void entrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sair() {
		// TODO Auto-generated method stub
		
	}

}

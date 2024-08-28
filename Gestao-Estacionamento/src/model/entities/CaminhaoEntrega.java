package model.entities;

import model.enums.TipoVeiculo;

//Classe para representar o Caminhão que entrega, que implementa veiculos e herda de veiculos cadastrados
public class CaminhaoEntrega extends Veiculo implements VeiculoCadastrado{


	public CaminhaoEntrega(String placa, TipoVeiculo modelo) {
		super(placa, modelo);
	}

	//ESSA CLASSE ESTÁ COMENTADA PQ O VEICULO CADASTRADO TEM O METODO DEFAULT
////	@Override
//	public void cadastrar(Veiculo veiculo) {
//		
//		DaoFactory.criarVeiculoDao().insert(veiculo);;
//		
//	}	

//	@Override Está comentado pq o método entrar se tornou concreto de Veiculo
//	public void entrar(String placa) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void sair(Veiculo veiculo) {
//		// TODO Auto-generated method stub
//		
//	}

}

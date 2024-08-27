package model.entities;

import model.dao.DaoFactory;
import model.enums.TipoVeiculo;

//Classe para implementar Veiculos de Serviço Publico, que não são cadastrados.
public class ServicoPublico extends Veiculo{

	public ServicoPublico(String placa, TipoVeiculo modelo) {
		super(placa, modelo);
	}

	@Override
	public void entrar(String placa) {
		DaoFactory.criarVeiculoDao();
		
		
		
	}

	@Override
	public void sair() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "ServicoPublico [getPlaca()=" + getPlaca() + ", getModelo()=" + getModelo() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}

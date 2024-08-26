package model.entities;

import model.enums.TipoVeiculo;

public abstract class VeiculoCadastrado implements Veiculo{
	

	private String placa;
	private TipoVeiculo modelo;
	
	//Construtor base
    public VeiculoCadastrado(String placa, TipoVeiculo modelo) {
    	//usando programação defensiva
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("A placa não pode ser nula ou vazia.");
        }
        if (modelo == null) {
            throw new IllegalArgumentException("O modelo não pode ser nulo.");
        }
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

	//Dps vê se é possível
	public abstract void cadastrar(VeiculoCadastrado veiculo);
	
	
}

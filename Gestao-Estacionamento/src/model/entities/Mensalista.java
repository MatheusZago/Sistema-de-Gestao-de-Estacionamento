package model.entities;

import java.util.Scanner;

import model.enums.TipoVeiculo;

//Classe para representar os mensalistas, que implmeneta veiculos e herda de Veiculos Cadastrados
public class Mensalista extends Veiculo implements VeiculoCadastrado{
	
	Scanner sc = new Scanner(System.in);

	public Mensalista(String placa) {
		super(placa);
	}

	public Mensalista(String placa, TipoVeiculo modelo) {
		super(placa, modelo);
	}



//	@Override  Está comentado pq o método entrar se tornou concreto de Veiculo
//	public void entrar(String placa) {
//		Veiculo veiculo = DaoFactory.criarVeiculoDao().FindCadastroByPlaca(placa);
//		
//		if(veiculo != null) {
//			System.out.println("Veiculo de placa " + veiculo.getPlaca() +" é um(a) " + veiculo.getModelo() + " cadastrado(a)");
//			
//			
//			
//			
//			
//		} else {			
//			//Se o if falhar entao o veiculo não é cadastrado, então ele será instanciado aqui, pedindo seu tipo de veiculo
//			//Não é necessário pedir o tipo de veiculo se já estiver cadastrado, pois ele vai pegar o tipo do BD por si próprio.
//			System.out.println("Veiculo não cadastrado.");
//			System.out.print("Informe a categoria (CARRO, MOTO, CAMINHAO, PUBLICO): ");
//			String tipo = sc.next().toUpperCase();
//			TipoVeiculo modelo = TipoVeiculo.valueOf(tipo);
//			
////			Veiculo veiculo;
//			
//			if(modelo == TipoVeiculo.PUBLICO) {
//				veiculo = new ServicoPublico(placa, modelo); 
//			} else {
//				veiculo = new Avulso(placa, modelo);
//			}
//			
//			System.out.println("Seu veiculo de placa " + veiculo.getPlaca() + " é um(a) " + veiculo.getModelo());
//		}
//		
//		System.out.println("Entrar pelas catracas: ");
//		
//		ServicoCancela.validacaoCatracasEntrada(veiculo);
//		

//	}

	
	
	
	
	@Override
	public void sair(Veiculo veiculo) {
		// TODO Auto-generated method stub
		
	}


	//ESSA CLASSE ESTÁ COMENTADA PQ O VEICULO CADASTRADO TEM O METODO DEFAULT
//	@Override
//	public void cadastrar(Veiculo veiculo) {
//		DaoFactory.criarVeiculoDao().insert(veiculo);
//		
//	}







}

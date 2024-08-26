package model.dao;

import db.DB;
import model.dao.impl.VeiculoDaoJBDC;

public class DaoFactory {
	
	//Aqui está criando a conexão com o JDBC
	public static VeiculoDaoJBDC criarVeiculoDao() {
		return new VeiculoDaoJBDC(DB.getConnection());
	}

}

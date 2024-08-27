package model.dao;

import db.DB;
import model.dao.impl.VeiculoDaoJBDC;

public class DaoFactory {
	
	//A Fabrica Dao serve par criar conexões especificas, caso quisesse outra conexão fora JBDC seria aqui
	public static VeiculoDaoJBDC criarVeiculoDao() {
		return new VeiculoDaoJBDC(DB.getConnection());
	}

}

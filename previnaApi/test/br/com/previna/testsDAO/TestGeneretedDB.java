 package br.com.previna.testsDAO;

import br.com.previna.db.GeneratedDB;

/**
 * 
 * @author cassio.trindade
 * Classe para teste do DAO Hibernate
 */
public class TestGeneretedDB {
	public static void main(String[] args) {
		try {
			GeneratedDB.generatedDB();
			System.out.println("Gerado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

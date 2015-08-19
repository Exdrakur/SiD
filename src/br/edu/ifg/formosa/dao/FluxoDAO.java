package br.edu.ifg.formosa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import br.edu.ifg.formosa.bd.ConnectionFactory;

public class FluxoDAO {

	public void registraEntrada(int ID){
		String SQL = "INSERT INTO fluxo (idPessoa, data, hrEntrada) VALUES (?, CURRENT_DATE, CURRENT_TIME);"
					+"UPDATE pessoa SET dentro=TRUE WHERE id=?";
		try{
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);
				pS.setLong(1, ID);//Passagem do ID para a Query de fluxo
				pS.setLong(2, ID);//Passagem do ID para a Query de pessoa
			pS.execute();
			pS.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void registraSaida(int ID){
		String SQL = "UPDATE fluxo SET hrSaida=CURRENT_TIME WHERE idPessoa=? AND data=CURRENT_DATE AND hrSaida IS NULL;"
					+"UPDATE pessoa SET dentro=FALSE WHERE id=?";
		try{
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);
				pS.setLong(1, ID);//Passagem do ID para a Query de fluxo
				pS.setLong(2, ID);//Passagem do ID para a Query de pessoa
			pS.execute();
			pS.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public int contaSaidasFaltantes(){
		String SQL = "SELECT COUNT(idPessoa) AS Contagem FROM fluxo WHERE hrSaida IS NULL";
		int qnt=0;
		try{
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);
			ResultSet rS = pS.executeQuery();
			while (rS.next()) {
				qnt=rS.getInt("Contagem");
			}
			pS.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return qnt;
	}
	
	public Vector<Vector<String>> buscaRegistrosIndividuais(int ID){
		String SQL = "SELECT * FROM fluxo WHERE idPessoa=?";
		Vector<Vector<String>> resultado=new Vector<Vector<String>>();
		try{
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);
				pS.setInt(1, ID);
			ResultSet rS = pS.executeQuery();
			while (rS.next()) {
				Vector<String> linha=new Vector<String>();
					linha.add(rS.getString("data").substring(8,10)+"/"+rS.getString("data").substring(5,7)+"/"+rS.getString("data").substring(0,4));
					linha.add(rS.getString("hrEntrada").substring(0,8));
					linha.add(rS.getString("hrSaida").substring(0,8));
				resultado.add(linha);
			}
			pS.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
}

package br.edu.ifg.formosa.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifg.formosa.bd.ConnectionFactory;
import br.edu.ifg.formosa.modelo.FluxoModelo;
import br.edu.ifg.formosa.modelo.RelatorioModelo;

public class RelatorioDAO {

	public List<RelatorioModelo> porData(Date dataInicio, Date dataFim){
		String SQL = "Select * from relatorioData WHERE data >= ? AND data <= ? ORDER BY data, hrEntrada, hrSaida";//Query de busca na view
		List<RelatorioModelo> lista = new ArrayList<RelatorioModelo>();
		try{
			PreparedStatement pS=new ConnectionFactory().getConnection().prepareStatement(SQL);//Preparaçã da conexão com o BD
				pS.setDate(1, dataInicio);
				pS.setDate(2, dataFim);
			ResultSet rS = pS.executeQuery();
			while(rS.next()){
				RelatorioModelo rMBd = new RelatorioModelo(
						rS.getString("nome"),//Nome
						rS.getString("rg"),//RG
						rS.getString("orgaoExpeditor"),//Orgao
						rS.getString("fone"),//Telefone
						rS.getString("hrEntrada"),//Hora de entrada
						rS.getString("hrSaida"),//Hora de saída
						rS.getString("data")//Data
				);
				lista.add(rMBd);//Passagem para a lista que será enviada para o iReport
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return lista;	
	}
	
	public ArrayList<FluxoModelo> porPessoa(int ID){
		String SQL = "SELECT * FROM fluxo WHERE idPessoa=?";
		ArrayList<FluxoModelo> resultado = new ArrayList<FluxoModelo>();
		try{
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);
				pS.setInt(1, ID);
			ResultSet rS = pS.executeQuery();
			while (rS.next()) {
				FluxoModelo linha = new FluxoModelo(rS.getString("data"), rS.getString("hrEntrada"), rS.getString("hrSaida"));
				resultado.add(linha);
			}
			pS.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
}

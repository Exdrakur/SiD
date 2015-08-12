package br.edu.ifg.formosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifg.formosa.bd.ConnectionFactory;
import br.edu.ifg.formosa.modelo.UsuarioModelo;

public class UsuarioDAO {
	
	public UsuarioModelo logar(UsuarioModelo uM){
		String SQL = "SELECT * FROM usuario WHERE cpf=?;";
		
		ConnectionFactory cF = new ConnectionFactory();//Cria a conexão com o banco de dados
		Connection c = cF.getConnection();//Retorna conexão
	
		UsuarioModelo uM2=null;//Cria o modelo de usuário que será retornado
		
		try {
			PreparedStatement pS = c.prepareStatement(SQL);//Preparando cláusula
				pS.setString(1, uM.getCpf());//Passagem de cpf para verificar se o login existe
			
			ResultSet rS = pS.executeQuery();//Somente para consultas, para INSERT, DELET e UPDATE usa-se o execute
			
			if(rS.next()){//Testa para ver se tem registros
				uM2=new UsuarioModelo(rS.getString("cpf"), rS.getString("senha"),rS.getString("nome"));//Inicializa o usuário modelo que será enviado
					//Passa o CPF do BD para a variável de modelo
					//Passa o nome do BD para a variável de modelo
					//Passa a senha do BD para a variável de modelo
			}
			
			rS.close();
			pS.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			//Informar que não foi possível conectar
		}
		return uM2;
	}
	
	public UsuarioModelo logarAdm(UsuarioModelo uM){
		String SQL = "SELECT * FROM selectADM WHERE cpf=?;";
		
		ConnectionFactory cF = new ConnectionFactory();//Cria a conexão com o banco de dados
		Connection c = cF.getConnection();//Retorna conexão
	
		UsuarioModelo uM2=new UsuarioModelo();//Cria o modelo de usuário que será retornado
		
		try {
			PreparedStatement pS = c.prepareStatement(SQL);//Preparando cláusula
				System.out.println(uM.getCpf());
				pS.setString(1, uM.getCpf());//Passagem de cpf para verificar se o login existe
			
			ResultSet rS = pS.executeQuery();//Somente para consultas, para INSERT, DELET e UPDATE usa-se o execute
			
			if(rS.next()){//Testa para ver se tem registros
				uM2=new UsuarioModelo(rS.getString("cpf"), rS.getString("senha"),rS.getString("nome"));//Inicializa o usuário modelo que será enviado
					//Passa o CPF do BD para a variável de modelo
					//Passa o nome do BD para a variável de modelo
					//Passa a senha do BD para a variável de modelo
			}
			
			rS.close();
			pS.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			//Informar que não foi possível conectar
		}
		return uM2;
	}
	
}

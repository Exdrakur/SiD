package br.edu.ifg.formosa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import br.edu.ifg.formosa.bd.ConnectionFactory;
import br.edu.ifg.formosa.modelo.PessoaModelo;

public class PessoaDAO {
	
	
//Adicionar um novo visitante ao BD
	public void adiciona(PessoaModelo pM){
		String SQL= "INSERT INTO pessoa (nome, RG, orgaoExpeditor, fone, email, dentro) VALUES (?, ?, ?, ?, ?, false)";
		try {
			PreparedStatement pS=new ConnectionFactory().getConnection().prepareStatement(SQL);//Prepara�� da conex�o com o BD
				pS.setString(1, pM.getNome());//Passagem do Nome
				pS.setString(2, pM.getRg());//Passagem do RG
				pS.setString(3, pM.getOrgaoExped());//Passagem do Oreg�o expeditor do RG
				pS.setString(4, pM.getFone());//Passagem do Telefone
				pS.setString(5, pM.getEmail());//Passagem do e-mail
			
			pS.execute();//Execu��o da Query
			pS.close();//Fechamento da conex�o
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
//Deletar o registro de um visitante
	public void deleta(int ID){//Manter ou deletar os dados ap�s a pessoa ser exclu�da?
		String SQL = "DELETE FROM fluxo WHERE idPessoa=?;"//Query de exclus�o dos logs da pessoa selecionada, caso haja
					+"DELETE FROM pessoa WHERE id=?";//Query de exclus�o da pessoa selecionada
		try {
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);
				pS.setLong(1,ID);//Passagem do ID para fluxo
				pS.setLong(2,ID);//Passagem do ID para pessoa
			pS.execute();
			pS.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

//Fun��o para alterar os dados de um visitante no BD
	public void altera(PessoaModelo pM){//Realiza a altera��o
		String SQL="UPDATE pessoa SET nome=?, rg=?, orgaoExpeditor=?, fone=?, email=? WHERE id=?";//Query para altera��o dos dados de uma pessoa
		
		try {
			PreparedStatement pS=new ConnectionFactory().getConnection().prepareStatement(SQL);//Prepara�� da conex�o com o BD
				pS.setString(1, pM.getNome());//Passagem do nome
				pS.setString(2, pM.getRg());//Passagem do RG
				pS.setString(3, pM.getOrgaoExped());//Passagem do Org�o expeditor do RG
				pS.setString(4, pM.getFone());//Passagem do telefone
				pS.setString(5, pM.getEmail());//Passagem do E-mail
				pS.setLong(6, pM.getID());//Passagem do ID
			
			pS.executeUpdate();
			pS.close();//Fechamento da conex�o
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public PessoaModelo buscaPorID(int ID){
		String SQL = "SELECT * FROM pessoa WHERE id = "+ID;//Query a ser utilizada na consulta
				
		try{
			PessoaModelo pM = new PessoaModelo();//Vari�vel para receber a lista de visitantes registrados
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);//Conex�o com o BD > Prepara��o da query
			
			ResultSet rS = pS.executeQuery();//Execu��o da Query
			
			while(rS.next()){
				//Cri um objeto do tipo PessoaModelo
					pM.setID(rS.getInt("id"));
					pM.setNome(rS.getString("nome"));
					pM.setRg(rS.getString("rg"));
					pM.setOrgaoExped(rS.getString("orgaoExpeditor"));
					pM.setFone(rS.getString("fone"));
					pM.setEmail(rS.getString("email"));
				//Vetor para armazenamento tempor�rio dos dados recebidos
			}
			rS.close();
			pS.close();
			return pM;
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
		
	}
	
//Lista todos os visitantes cadastrados
	public Vector<PessoaModelo> listar(){
		String SQL = "SELECT * FROM pessoa ORDER BY dentro DESC, nome";//Query a ser utilizada na consulta
		try{
			Vector<PessoaModelo> pessoaModelo=new Vector<PessoaModelo>();//Usar com o novo table model
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);//Conex�o com o BD > Prepara��o da query
			
			ResultSet rS = pS.executeQuery();//Execu��o da Query
			
			while(rS.next()){
				//Cri um objeto do tipo PessoaModelo
				PessoaModelo pMBD=new PessoaModelo();
					pMBD.setID(rS.getInt("id"));
					pMBD.setNome(rS.getString("nome"));
					pMBD.setFone(rS.getString("fone"));
					pMBD.setEmail(rS.getString("email"));
					pMBD.setcFluxo(rS.getBoolean("dentro")==true ? "saida.png":"entrada.png");//Modifica a imagem de fluxo de acordo com a situa��o da pessoa(dentro/fora)
					pMBD.setSituFluxo(rS.getBoolean("dentro"));
				//Vetor para armazenamento tempor�rio dos dados recebidos
				Vector<String> colunas = new Vector<String>();
					colunas.add(""+rS.getInt("id"));
					colunas.add(pMBD.getNome());
					colunas.add(pMBD.getFone());
					
				//Adiciona � lista que ser� passada para a tabela
				pessoaModelo.add(pMBD);
			}
			rS.close();
			pS.close();
			return pessoaModelo;
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	} 

//Fun��o para realizar a busca de visitantes pelo nome
	public Vector<PessoaModelo> busca(String nome){
		nome=nome.toLowerCase();
		String SQL = "SELECT * FROM pessoa WHERE LOWER(nome) LIKE '%"+nome+"%' ORDER BY nome";//Query a ser utilizada na consulta
		try{
			Vector<PessoaModelo> pessoaModelo=new Vector<PessoaModelo>();//Usar com o novo table model
			PreparedStatement pS = new ConnectionFactory().getConnection().prepareStatement(SQL);//Conex�o com o BD > Prepara��o da query
//				pS.setString(1, nome);
			ResultSet rS = pS.executeQuery();//Execu��o da Query
			
			while(rS.next()){
				//Cri um objeto do tipo PessoaModelo
				PessoaModelo pMBD=new PessoaModelo();
					pMBD.setID(rS.getInt("id"));
					pMBD.setNome(rS.getString("nome"));
					pMBD.setFone(rS.getString("fone"));
					pMBD.setEmail(rS.getString("email"));
					pMBD.setcFluxo(rS.getBoolean("dentro")==true ? "saida.png":"entrada.png");//Modifica a imagem de fluxo de acordo com a situa��o da pessoa(dentro/fora)
					pMBD.setSituFluxo(rS.getBoolean("dentro"));
				//Adiciona � lista que ser� passada para a tabela
				pessoaModelo.add(pMBD);
			}
			rS.close();
			pS.close();
			return pessoaModelo;
		}catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
		
	}
	
}





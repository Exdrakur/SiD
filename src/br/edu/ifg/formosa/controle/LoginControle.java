package br.edu.ifg.formosa.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.formosa.dao.UsuarioDAO;
import br.edu.ifg.formosa.main.build;
import br.edu.ifg.formosa.modelo.UsuarioModelo;
import br.edu.ifg.formosa.view.LoginRecepcionistaView;

public class LoginControle {
	
	private static LoginRecepcionistaView lRV;
	private UsuarioDAO userDAO;
	
	public LoginControle(LoginRecepcionistaView lRV) {
		LoginControle.lRV=lRV;
		userDAO=new UsuarioDAO();
		acaoLogar();
		acaoCancelar();
	}
	//Adiciona ação ao botão btConfirmar
	private void acaoLogar(){
		lRV.getBtnConfirmar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Torna a JLabel informativa invisível
				lRV.getLblTextoInfomativo().setVisible(true);
				//Testar campos nulos
				String cpf  =lRV.getFTxtCPF().getText().replace(".", "").replace("-", "");//Pega valor do campo CPF/Login
				
				@SuppressWarnings("deprecation")
				String senha=lRV.getTxtSenha().getText();//Pega valor do campo senha
				
				//Testa se existe algum campo vazio
				if(cpf.equalsIgnoreCase("") || cpf.equalsIgnoreCase(null) || senha.equalsIgnoreCase("") || senha.equalsIgnoreCase(null)){
					LoginControle.mensagemErro("Preencha todos os campos!");
				}else{
					UsuarioModelo uM=new UsuarioModelo(cpf, senha);//Pesquisar criptografia MD5 para senha
							
						//Manda buscar
						UsuarioModelo userBD = userDAO.logar(uM);
					
					if(userBD!=null){
						//Encontrou o CPF
						if(userBD.getSenha().equals(uM.getSenha())){//Loga no sistema
							lRV.dispose();
							new build();//Classe responsável por montar as telas e os controles
						}else{//Informa que a senha está errada
							LoginControle.mensagemErro("Loguin ou senha incorretos!");
						}
					}else {
						LoginControle.mensagemErro("Usuário não encontrado!");
					}//Fim do else para testar se a busca retornou algum resultado
				}//Fim do else de teste nulo
			}
		});	
	}
	
	//Troca os textos e exibe as informações na JLabel infromativa
	//--> Utilizado pela funcao acaoLogar()
	private static void mensagemErro(String msg){
		lRV.getLblTextoInfomativo().setText(msg);//Altera texto informativo da JLabel informativa5
	}
	
	//Adiciona ação ao botão btCancelar
	private void acaoCancelar(){
		lRV.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lRV.setDefaultCloseOperation(LoginRecepcionistaView.EXIT_ON_CLOSE);
				lRV.dispose();
			}
		});
	}

}

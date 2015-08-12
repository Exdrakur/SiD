package br.edu.ifg.formosa.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.formosa.dao.PessoaDAO;
import br.edu.ifg.formosa.modelo.PessoaModelo;
import br.edu.ifg.formosa.util.Dispatcher;
import br.edu.ifg.formosa.util.Event;
import br.edu.ifg.formosa.view.CadastrarVisitanteView;

public class AlterarControle {
		
	private CadastrarVisitanteView aVV;
	private PessoaDAO pDAO;
	
	public AlterarControle(CadastrarVisitanteView aVV, int ID) {
		this.aVV=aVV;
		this.pDAO=new PessoaDAO();
		addAcaoCancelar();
		addAcaoConfirmar(ID);
		preencheCampos(ID);
		aVV.repaint();
	}
	
//__Preenche os campos da tela de alteração
	private void preencheCampos(int ID){
		PessoaModelo pM = pDAO.buscaPorID(ID);
		aVV.getTxtNome().setText(pM.getNome());
		aVV.getfTxtRg().setText(pM.getRg());
		aVV.getcBOrgaoExpeditor().setText(pM.getOrgaoExped());
		aVV.getTxtEmail().setText(pM.getEmail());
		aVV.getFTxtFone().setText(pM.getFone().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
	}	

//__Adiciona ação ao botão cancelar
	private void addAcaoCancelar(){
		aVV.getBtCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	aVV.dispose();	}
		});
	}
//__Adiciona ação ao botão confirmar
	private void addAcaoConfirmar(final int ID){
		aVV.getBtConfirmar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PessoaModelo pM = new PessoaModelo();
					pM.setID(ID);
					pM.setNome(aVV.getTxtNome().getText());
					pM.setRg(aVV.getfTxtRg().getText());
					pM.setOrgaoExped(aVV.getcBOrgaoExpeditor().getText());
					pM.setEmail(aVV.getTxtEmail().getText());
					pM.setFone(aVV.getFTxtFone().getText().replace(" ", ""));
						pM.setFone(	pM.getFone().length() <= 8 ? "" :  pM.getFone()	);//Verificação para que o valor do telefone seja válido ou nenhum
					
				if(!(//Verificação se os campos essenciais não estão vazios
					pM.getNome().equals(null) || pM.getNome().equalsIgnoreCase("") ||
					pM.getRg().equals(null)   || pM.getRg().equalsIgnoreCase("")   ||
					pM.getOrgaoExped().equals(null) || pM.getOrgaoExped().equalsIgnoreCase("")
				)){
					pDAO.altera(pM);//passagem para o PessoaDAO
					Dispatcher.getInstance().dispatchEvent(new Event("cadastrar"));
					aVV.dispose();
				}else{
					aVV.getLbAviso().setVisible(true);
					aVV.getLbAviso().setText("Preencha os campos com * para continuar");
					aVV.repaint();
				}
			}
		});
	}
	
}

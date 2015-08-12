package br.edu.ifg.formosa.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.formosa.dao.PessoaDAO;
import br.edu.ifg.formosa.modelo.PessoaModelo;
import br.edu.ifg.formosa.util.Dispatcher;
import br.edu.ifg.formosa.util.Event;
import br.edu.ifg.formosa.view.CadastrarVisitanteView;

public class CadastrarControle {
	
	private CadastrarVisitanteView cVV;
	private PessoaDAO pDAO;
	
	public CadastrarControle(CadastrarVisitanteView cVV) {
		this.cVV=cVV;
		this.pDAO=new PessoaDAO();
		addAcaoCancelar();
		addAcaoConfirmar();
	}
	
//__Adiciona ação ao botão cancelar
	private void addAcaoCancelar(){
		cVV.getBtCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cVV.dispose();
			}
		});
	}
//__Adiciona ação ao botão confirmar
	private void addAcaoConfirmar(){
		cVV.getBtConfirmar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PessoaModelo pM = new PessoaModelo();
					pM.setNome(cVV.getTxtNome().getText());
					pM.setRg(cVV.getfTxtRg().getText());
					pM.setOrgaoExped(cVV.getcBOrgaoExpeditor().getText());
					pM.setEmail(cVV.getTxtEmail().getText());
					pM.setFone(cVV.getFTxtFone().getText().replace(" ", ""));
						pM.setFone(	pM.getFone().length() <= 8 ? "-" :  pM.getFone()	);//Verificação para que o valor do telefone seja válido ou nenhum
				if(!(//Verificação se os campos essenciais não estão vazios
					pM.getNome().equals(null) || pM.getNome().equalsIgnoreCase("") ||
					pM.getRg().equals(null)   || pM.getRg().equalsIgnoreCase("")   ||
					pM.getOrgaoExped().equals(null) || pM.getOrgaoExped().equalsIgnoreCase("")
				)){
					pDAO.adiciona(pM);//passagem para o PessoaDAO
					Dispatcher.getInstance().dispatchEvent(new Event("cadastrar"));
					cVV.dispose();
				}else{
					cVV.getLbAviso().setVisible(true);
					cVV.getLbAviso().setText("Preencha os campos com * para continuar");
					cVV.repaint();
				}
			}
		});
	}
	
}

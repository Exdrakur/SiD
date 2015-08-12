package br.edu.ifg.formosa.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.formosa.dao.PessoaDAO;
import br.edu.ifg.formosa.util.Dispatcher;
import br.edu.ifg.formosa.util.Event;
import br.edu.ifg.formosa.view.ExcluiVisitantesView;

public class ExcluirControle {

	private ExcluiVisitantesView eVV;
	
	public ExcluirControle(ExcluiVisitantesView eVV, int ID) {
		this.eVV=eVV;
		addAcaoBotaoNao();
		addAcaoBotaoSim(ID);
	}
	
	//Adiciona ação ao botão NÃO
	private void addAcaoBotaoNao(){
		eVV.getBtNao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eVV.dispose();
			}
		});
	}
	//Adiciona ação ao botão SIM
	private void addAcaoBotaoSim(final int ID){
		eVV.getBtSim().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PessoaDAO pDAO = new PessoaDAO();
				pDAO.deleta(ID);
				Dispatcher.getInstance().dispatchEvent(new Event("fechar"));
				eVV.dispose();
			}
		});
	}
}

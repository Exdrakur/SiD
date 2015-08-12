package br.edu.ifg.formosa.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.formosa.dao.FluxoDAO;
import br.edu.ifg.formosa.util.Dispatcher;
import br.edu.ifg.formosa.util.Event;
import br.edu.ifg.formosa.view.FluxoVisitantesView;

public class FluxoControle {
	
	private FluxoVisitantesView fVV;
	
	public FluxoControle(FluxoVisitantesView fVV, int ID, boolean situacao) {
		this.fVV=fVV;
		addAcaoN�o();
		addAcaoSim(ID, situacao);
	}
	
	private void addAcaoSim(final int ID, final boolean situacao){
		fVV.getBtSim().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FluxoDAO fDAO = new FluxoDAO();
				if(situacao){//Verdadeiro, indica que est� sa�ndo
					fDAO.registraSaida(ID);
				}else{//False, indica que est� entrando
					fDAO.registraEntrada(ID);
				}
				Dispatcher.getInstance().dispatchEvent(new Event("fluxo"));
				fVV.dispose();
			}
		});
	}
	
	private void addAcaoN�o(){
		fVV.getBtNao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fVV.dispose();
			}
		});
	}

}

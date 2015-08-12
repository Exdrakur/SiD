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
		addAcaoNão();
		addAcaoSim(ID, situacao);
	}
	
	private void addAcaoSim(final int ID, final boolean situacao){
		fVV.getBtSim().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FluxoDAO fDAO = new FluxoDAO();
				if(situacao){//Verdadeiro, indica que está saíndo
					fDAO.registraSaida(ID);
				}else{//False, indica que está entrando
					fDAO.registraEntrada(ID);
				}
				Dispatcher.getInstance().dispatchEvent(new Event("fluxo"));
				fVV.dispose();
			}
		});
	}
	
	private void addAcaoNão(){
		fVV.getBtNao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fVV.dispose();
			}
		});
	}

}

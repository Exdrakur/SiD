package br.edu.ifg.formosa.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;

import br.edu.ifg.formosa.view.GerarRelatorioNomeView;
import br.edu.ifg.formosa.view.VisualizaDadosView;

public class VisualizarControle {

	private final VisualizaDadosView vDV;
	private final JDesktopPane deskP;
	
	public VisualizarControle(VisualizaDadosView vDV,JDesktopPane deskP,int ID) {
		this.vDV=vDV;
		this.deskP=deskP;
		addAcaoCancelar();//Adiciona a ação de fechar o JIF no botão cancelar
		addAcaoImprimir(ID);//Adiciona a ação de imprimir relatório
	}
	
	private void addAcaoCancelar(){
		vDV.getBtnFechar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vDV.dispose();
			}
		});
	}
	
	private void addAcaoImprimir(final int ID){
		vDV.getBtnImprimir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GerarRelatorioNomeView gRNV = new GerarRelatorioNomeView();
					deskP.add(gRNV);//Adição do JIF ao JFrame principal
					deskP.moveToFront(gRNV);//Move o JIF para frente
				new RelatorioNomeControle(gRNV,deskP,ID);//Adiciona as ações
				vDV.dispose();
			}
		});
	}
}

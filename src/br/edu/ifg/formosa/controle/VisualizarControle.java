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
		addAcaoCancelar();//Adiciona a a��o de fechar o JIF no bot�o cancelar
		addAcaoImprimir(ID);//Adiciona a a��o de imprimir relat�rio
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
					deskP.add(gRNV);//Adi��o do JIF ao JFrame principal
					deskP.moveToFront(gRNV);//Move o JIF para frente
				new RelatorioNomeControle(gRNV,deskP,ID);//Adiciona as a��es
				vDV.dispose();
			}
		});
	}
}

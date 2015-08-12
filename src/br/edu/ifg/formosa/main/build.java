package br.edu.ifg.formosa.main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JDesktopPane;

import br.edu.ifg.formosa.controle.VisitantesControle;
import br.edu.ifg.formosa.view.VisitantesView;

public class build {

	public build() {
		//Painel desktop 
		JDesktopPane deskP = new JDesktopPane() {//Painel Desktop que é utilizado no programa
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.setColor(Color.WHITE);
		        g.fillRect(0, 0, getWidth(), getHeight());
		    }
		};							
		VisitantesView visV = new VisitantesView(deskP);//Passagem do JPanelDesktop para o frame principal
		new VisitantesControle(visV, deskP);//Atri]buição dos controles para o frame principal
		
		//Janela interna de Cadastro
		
		//Janela interna de Alteração
		
		
	}
	
}
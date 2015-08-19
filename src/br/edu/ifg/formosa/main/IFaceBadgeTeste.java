package br.edu.ifg.formosa.main;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import br.edu.ifg.formosa.controle.LoginControle;
import br.edu.ifg.formosa.view.LoginRecepcionistaView;

public class IFaceBadgeTeste {

	public static void main(String[] args) {
		try {	
			UIManager.setLookAndFeel(new NimbusLookAndFeel());	
			}
		catch(Exception e){	e.printStackTrace();	}		
//		 System.out.println(System.getProperty("user.home")+System.getProperty("file.separator"));
		LoginRecepcionistaView lRV = new LoginRecepcionistaView();
		new LoginControle(lRV);
	}

}

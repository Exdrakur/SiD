package br.edu.ifg.formosa.view;


public class AlterarVisitantesView extends CadastrarVisitanteView{
	
	private static final long serialVersionUID = 1L;

	public AlterarVisitantesView() {
		getLbTitulo().setText("Alterar Visitante\r\n");
		getBtConfirmar().setText("Alterar");
		this.setTitle("Alterar Visitante");
		
		this.setVisible(true);
	}

}

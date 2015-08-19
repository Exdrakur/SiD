package br.edu.ifg.formosa.view;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.formosa.dao.FluxoDAO;
import br.edu.ifg.formosa.modelo.PessoaModelo;

public class VisualizaDadosView extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private final JButton btnFechar, btnImprimir;
	private final JTable table;
	
	public VisualizaDadosView(PessoaModelo pM) {
		super("Visualizar dados", false, true, false, false);
		getContentPane().setBackground(Color.WHITE);
		this.setBounds(240,50,400,430);
		getContentPane().setLayout(null);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbNome.setBounds(10, 50, 70, 25);
		getContentPane().add(lbNome);
		
		JLabel lbTitulo = new JLabel("Visualisar Dados");
		lbTitulo.setToolTipText("");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbTitulo.setBounds(10, 10, 365, 30);
		getContentPane().add(lbTitulo);
		
		JLabel lbEmail = new JLabel("E-mail:");
		lbEmail.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbEmail.setBounds(10, 80, 70, 25);
		getContentPane().add(lbEmail);
		
		JLabel lbTelefone = new JLabel("Telefone:");
		lbTelefone.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbTelefone.setBounds(10, 110, 70, 25);
		getContentPane().add(lbTelefone);
		
		Vector<String> colunas = new Vector<String>();
			colunas.add("Data");
			colunas.add("Entrada");
			colunas.add("Saída");
		table = new JTable(){
			private static final long serialVersionUID = 1L;
			@Override//Retira a edição dos campos da tabela
			public boolean isCellEditable(int row, int column){	return false;	}
		};
		table.setModel(new DefaultTableModel(
			new FluxoDAO().buscaRegistrosIndividuais(pM.getID()), colunas
		));
		table.getTableHeader().setFont(new Font("Segoe UI Semilight", Font.BOLD, 13));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 150, 365, 200);
			getContentPane().add(scrollPane);
		
		
		btnFechar = new JButton("Fechar");
		btnFechar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnFechar.setBounds(80, 360, 100, 25);
		getContentPane().add(btnFechar);
		
		JLabel lbInfoFone = new JLabel(pM.getFone());
		lbInfoFone.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbInfoFone.setBounds(80, 110, 295, 25);
		getContentPane().add(lbInfoFone);
		
		JLabel lbInfoEmail = new JLabel(pM.getEmail());
		lbInfoEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbInfoEmail.setBounds(80, 80, 295, 25);
		getContentPane().add(lbInfoEmail);
		
		JLabel lbInfoNome = new JLabel(pM.getNome());
		lbInfoNome.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbInfoNome.setBounds(80, 50, 295, 25);
		getContentPane().add(lbInfoNome);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnImprimir.setBounds(200, 360, 100, 25);
		btnImprimir.setEnabled(true);
		getContentPane().add(btnImprimir);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		setVisible(true);
	}

	public JButton getBtnFechar() {	return btnFechar;	}
	public JButton getBtnImprimir() {	return btnImprimir;	}
	public JTable getTable() {	return table;	}
}

package br.edu.ifg.formosa.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


public class VisitantesView extends JFrame {
	private static final long serialVersionUID = 1L;
	//MenuBar
	private final JMenuBar menuBar;
	private final JMenu mSistema;
	private final JMenuItem miAjuda, miSair, miRelatorioPorData;
	//Componentes do pTitulo
	private final JPanel panel;
	private final JTextField txtBuscaNome;
	private final JTable table;
	private final JButton btnAdicionar, btnBuscar;
		
	@SuppressWarnings("serial")
	public VisitantesView(JDesktopPane deskP) {
		ImageIcon imgLogo = new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/img/Sem t\u00EDtulo-1.png"));
		
		setIconImage(imgLogo.getImage());
		setTitle("SiD - IF (Sistema de Identifica\u00E7\u00E3o do IF)");
		setBackground(Color.WHITE);
		this.setContentPane(deskP);
		deskP.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		
		JLabel lblLogoesq = new JLabel("LogoEsq");
		lblLogoesq.setIcon(imgLogo);
		lblLogoesq.setBounds(30, 6, 50, 65);
		getContentPane().add(lblLogoesq);
		
		JLabel lblLogodir = new JLabel("LogoDir");
		lblLogodir.setIcon(imgLogo);
		lblLogodir.setBounds(800, 6, 50, 65);
		getContentPane().add(lblLogodir);
		
		JLabel lblCentro = new JLabel("SiD-IF (Sistema de Identifica\u00E7\u00E3o do IF)");
		lblCentro.setVerticalAlignment(SwingConstants.TOP);
		lblCentro.setFont(new Font("Segoe UI Semilight", Font.BOLD, 35));
		lblCentro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentro.setBounds(90, 14, 696, 50);
		getContentPane().add(lblCentro);
		
		panel = new JPanel();
		panel.setBackground(new Color(50, 205, 50));
		panel.setBounds(30, 73, 820, 50);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnAdicionar.setBounds(20, 10, 100, 30);
		panel.add(btnAdicionar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBuscar.setBounds(730, 10, 75, 30);
		panel.add(btnBuscar);
		
		txtBuscaNome = new JTextField();
		txtBuscaNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtBuscaNome.setBounds(180, 10, 545, 30);
		panel.add(txtBuscaNome);
		txtBuscaNome.setColumns(10);
		
		
		//TABELA
		table = new JTable(){@Override
		public boolean isCellEditable(int row, int column) {//Retira a edição dos campos da tabela
			return false;
		}};
		table.setModel(new DefaultTableModel(	null, new String[] { "ID", "Nome", "Telefone", "Fluxo" })	);
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.setRowHeight(30);
		table.setFont(new Font("Segoe UI Semilight", 0, 14));
		table.setAutoCreateRowSorter(true);
		table.setBounds(16, 139, 778, 350);
		table.setBackground(UIManager.getColor("Table.light"));
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.getTableHeader().setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
		JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(30, 121, 820, 397);
		getContentPane().add(scrollPane);
		//Menu
			this.menuBar = new JMenuBar();
			this.setJMenuBar(menuBar);
			
			this.mSistema = new JMenu("Sistema");
			menuBar.add(mSistema);
			
			this.miRelatorioPorData=new JMenuItem("Gerar relatório por data");
			this.mSistema.add(miRelatorioPorData);
			
			this.miAjuda = new JMenuItem("Ajuda");
			this.mSistema.add(miAjuda);
			
			this.miSair = new JMenuItem("Sair");
			this.mSistema.add(miSair);
		
		repaint();
		setVisible(true);
	}
	
	//Getters
	public JButton getBtnAdicionar(){	return btnAdicionar;}
	public JButton getBtnBuscar() {	return btnBuscar;	}
	public JMenuItem getMiAjuda() {	return miAjuda;	}
	public JMenuItem getMiSair()  {	return miSair;	}
	public JTable getTable() {	return table;	}
	public JTextField getTxtBuscaNome() {	return txtBuscaNome;	}
	public JMenuItem getMiRelatorioPorData() {	return miRelatorioPorData;	}
	
	
}

package br.edu.ifg.formosa.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ExcluiVisitantesView  extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private final JButton btNao, btSim;
	
	public ExcluiVisitantesView(String nome) {
		super("Deletar Visitante", false, true, false, false);
		getContentPane().setBackground(Color.WHITE);
		this.setBounds(240,125,420,150);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Deseja realmente excluir:");
		lblNewLabel.setBounds(5, 10, 400, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		getContentPane().add(lblNewLabel);
		
		btNao = new JButton("N\u00E3o");
		btNao.setBounds(100, 80, 90, 30);
		btNao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		getContentPane().add(btNao);
		
		btSim = new JButton("Sim");
		btSim.setBounds(230, 80, 90, 30);
		btSim.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		getContentPane().add(btSim);
		
		JLabel label = new JLabel(nome);
		label.setBounds(5, 40, 390, 30);
		label.setToolTipText("");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Segoe UI", Font.BOLD, 20));
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("?");
		label_1.setToolTipText("");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		label_1.setBounds(385, 40, 10, 30);
		getContentPane().add(label_1);
		
		this.setVisible(true);
		this.repaint();
	}

	public JButton getBtNao() {	return btNao;	}
	public JButton getBtSim() {	return btSim;	}
}
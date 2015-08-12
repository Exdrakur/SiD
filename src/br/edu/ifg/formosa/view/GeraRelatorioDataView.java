package br.edu.ifg.formosa.view;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import br.edu.ifg.formosa.util.JDatePanel;

public class GeraRelatorioDataView extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtCPF;
	private JPasswordField pfSenha;
	private JButton btnGerar,btnCancelar;
	private JLabel lbAviso;
	private JDatePanel datePInicio, datePTermino;

	public GeraRelatorioDataView() {
		super("Gerar Relatório", false, true, false, false);
		//Titulo, Redimensionável, Fechar, Maximizável,  Minimizável
		this.setBounds(275, 120, 350, 250);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblGerarRelatrio = new JLabel("Gerar Relat\u00F3rio");
		lblGerarRelatrio.setBounds(0, 0, 338, 27);
		lblGerarRelatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerarRelatrio.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		getContentPane().add(lblGerarRelatrio);
		
		JLabel lblDataIncio = new JLabel("Data In\u00EDcio");
		lblDataIncio.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDataIncio.setBounds(10, 55, 100, 30);
		getContentPane().add(lblDataIncio);
		
		JLabel lblDataTrmino = new JLabel("Data T\u00E9rmino");
		lblDataTrmino.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDataTrmino.setBounds(10, 85, 100, 30);
		getContentPane().add(lblDataTrmino);
		
		JLabel lbSenha = new JLabel("Senha");
		lbSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbSenha.setBounds(10, 145, 100, 30);
		getContentPane().add(lbSenha);
		
		datePInicio=new JDatePanel(110, 55);
			getContentPane().add(datePInicio);
			
		datePTermino=new JDatePanel(110,85);
			getContentPane().add(datePTermino);
		
		try{			
			JLabel lbCPF = new JLabel("CPF");
			lbCPF.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lbCPF.setBounds(10, 115, 100, 30);
			getContentPane().add(lbCPF);
			
			txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
			txtCPF.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			txtCPF.setBounds(110, 115, 165, 30);
			getContentPane().add(txtCPF);
			txtCPF.setColumns(10);
		} catch (ParseException e) {	e.printStackTrace();	}
			
		lbAviso = new JLabel();
		lbAviso.setForeground(Color.RED);
		lbAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lbAviso.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbAviso.setBounds(10, 30, 270, 16);
		getContentPane().add(lbAviso);
		
		pfSenha = new JPasswordField();
		pfSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pfSenha.setBounds(110, 145, 165, 30);
		getContentPane().add(pfSenha);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnCancelar.setBounds(85, 182, 90, 30);
		getContentPane().add(btnCancelar);
		
		btnGerar = new JButton("Gerar");
		btnGerar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnGerar.setBounds(185, 182, 90, 30);
		getContentPane().add(btnGerar);
		
		this.setVisible(true);
	}

	
	public JDatePanel getDatePInicio() {return datePInicio;}
	public JDatePanel getDatePTermino() {return datePTermino;}
	public JFormattedTextField getTxtCPF() {	return txtCPF;	}
	public JPasswordField getPfSenha() {	return pfSenha;	}
	public JButton getBtnGerar() {	return btnGerar;	}
	public JButton getBtnCancelar() {	return btnCancelar;	}
	public JLabel getLbAviso(){return lbAviso;}
}

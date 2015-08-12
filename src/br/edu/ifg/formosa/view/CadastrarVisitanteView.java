package br.edu.ifg.formosa.view;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class CadastrarVisitanteView extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private final JLabel lbTitulo;
	private final JTextField txtNome;
	private JFormattedTextField fTxtRg, fTxtFone;
	private final JTextField txtEmail;
	private final JButton btConfirmar, btCancelar;
	private final JLabel lbAviso;
	private final JTextField txtOrgaoExpeditor;

	public CadastrarVisitanteView() {
		super("Cadastro de Visitantes", false, true, false, false);
		//Titulo, Redimensionável, Fechar, Maximizável,  Minimizável
		this.setBounds(200, 120, 500, 280);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		
		lbTitulo = new JLabel("Cadastrar Visitantes");
		lbTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(10, 12, 464, 27);
		getContentPane().add(lbTitulo);
		
		JLabel lblNome = new JLabel("Nome*:");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNome.setBounds(15, 65, 60, 25);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtNome.setBounds(85, 65, 390, 29);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lbOrExp = new JLabel("Org\u00E3o Expeditor*:");
		lbOrExp.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbOrExp.setBounds(245, 100, 120, 25);
		getContentPane().add(lbOrExp);
		
		txtOrgaoExpeditor = new JTextField();
		txtOrgaoExpeditor.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtOrgaoExpeditor.setBounds(365, 100, 110, 29);
		getContentPane().add(txtOrgaoExpeditor);
		
		MaskFormatter maskRG, maskFone;
		String validaCaracteres = "1234567890 ";
		try {
			maskRG = new MaskFormatter("#######*******");
				maskRG.setValidCharacters(validaCaracteres);
			fTxtRg = new JFormattedTextField(maskRG);
			fTxtRg.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			fTxtRg.setBounds(85, 100, 150, 29);
			getContentPane().add(fTxtRg);
			
			maskFone=new MaskFormatter("(##)####-####*");
				maskFone.setValidCharacters(validaCaracteres);
			fTxtFone = new JFormattedTextField(maskFone);
			fTxtFone.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			fTxtFone.setColumns(10);
			fTxtFone.setBounds(85, 166, 390, 29);
			getContentPane().add(fTxtFone);
		} catch (ParseException e) {	e.printStackTrace();		}
		
			JLabel lbRG = new JLabel("RG*:");
			lbRG.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lbRG.setBounds(15, 100, 60, 25);
			getContentPane().add(lbRG);
			
			JLabel lbEmail = new JLabel("E-mail:");
			lbEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lbEmail.setBounds(14, 135, 60, 25);
			getContentPane().add(lbEmail);
			
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			txtEmail.setColumns(10);
			txtEmail.setBounds(85, 135, 390, 29);
			getContentPane().add(txtEmail);
			
			JLabel lbFone = new JLabel("Telefone:");
			lbFone.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lbFone.setBounds(15, 166, 70, 25);
			getContentPane().add(lbFone);
			
			btConfirmar = new JButton("Salvar");
			btConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			btConfirmar.setBounds(260, 205, 90, 30);
			getContentPane().add(btConfirmar);
			
			btCancelar = new JButton("Cancelar\r\n");
			btCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			btCancelar.setBounds(150, 205, 90, 30);
			getContentPane().add(btCancelar);
			
			lbAviso = new JLabel("Aviso");
			lbAviso.setHorizontalAlignment(SwingConstants.CENTER);
			lbAviso.setForeground(Color.RED);
			lbAviso.setFont(new Font("Segoe UI", Font.BOLD, 14));
			lbAviso.setBounds(9, 45, 470, 20);
			lbAviso.setVisible(false);
			getContentPane().add(lbAviso);
		
		this.setVisible(true);
	}
	
	public JLabel getLbTitulo()		{	return lbTitulo;	}
	public JLabel getLbAviso()		{	return lbAviso;	}
	public JTextField getTxtNome()	{	return txtNome;		}
	public JFormattedTextField getfTxtRg() { return fTxtRg;	}
	public JTextField getcBOrgaoExpeditor() {	return txtOrgaoExpeditor;	}
	public JTextField getTxtEmail()	{	return txtEmail;	}
	public JFormattedTextField getFTxtFone()	{ 	return fTxtFone;		}
	public JButton getBtConfirmar() { return btConfirmar;	}
	public JButton getBtCancelar()	{	return btCancelar;	}
}

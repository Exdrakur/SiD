package br.edu.ifg.formosa.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import br.edu.ifg.formosa.dao.FluxoDAO;
import br.edu.ifg.formosa.dao.PessoaDAO;
import br.edu.ifg.formosa.modelo.PessoaModelo;
import br.edu.ifg.formosa.util.Dispatcher;
import br.edu.ifg.formosa.util.Event;
import br.edu.ifg.formosa.util.Listener;
import br.edu.ifg.formosa.util.VisitanteTableModel;
import br.edu.ifg.formosa.view.AlterarVisitantesView;
import br.edu.ifg.formosa.view.CadastrarVisitanteView;
import br.edu.ifg.formosa.view.ExcluiVisitantesView;
import br.edu.ifg.formosa.view.FluxoVisitantesView;
import br.edu.ifg.formosa.view.GeraRelatorioDataView;
import br.edu.ifg.formosa.view.VisitantesView;
import br.edu.ifg.formosa.view.VisualizaDadosView;

public class VisitantesControle {

	private VisitantesView vV;
	private JDesktopPane deskP;
	
	public VisitantesControle(VisitantesView vV, JDesktopPane deskP) {
		this.vV=vV;
		this.deskP=deskP;
		addAcaoBtAdicionar();//Adiciona ação ao botão adicionar visitante
		addAcaoMenuRelatorior();//Adiciona ação ao menu de impressão de relatório
		addAcaoBuscar();//Adiciona a função de busca
		preencheTabelaDAO();//Executa o preenchimento da tabela com os dados do BD
		acaoRetorno();//Executa a atualização da tabela quando os JInternalFrames são fechados
		acaoTabela();//Adiciona as ações à tabela simulando botões (Fluxo, Visualizar, Alterar, Excluir)
		acaoFecharJanela();//Ação disparada ao fechar o sofrware para evitar que o programa seja fechado sem registrar as saídas faltantes
		acaoMenuSair();
		acaoMenuAjuda();
	}
	//Método responsável por criar a tela de cadastro
	private void addAcaoBtAdicionar(){
		vV.getBtnAdicionar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				CadastrarVisitanteView cVV = new CadastrarVisitanteView();//Instancia do JInternalFrame
				vV.getContentPane().add(cVV);//Adição do JIF ao JFrame principal
				deskP.moveToFront(cVV);//Move o JIF para frente
				new CadastrarControle(cVV);
			}
		});
	}
	
	//Método responsável por criar a tela de Alteração
	private void addAcaoMenuRelatorior(){
		vV.getMiRelatorioPorData().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				GeraRelatorioDataView gRDV = new GeraRelatorioDataView();//Instancia do JInternalFrame
				vV.getContentPane().add(gRDV);//Adição do JIF ao JFrame principal
				deskP.moveToFront(gRDV);//Move o JIF para frente
				new RelatorioDataControle(gRDV);
			}
		});
	}
	
	//Adicona a função de busca ao butão Buscar
	private void addAcaoBuscar(){
		vV.getBtnBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<PessoaModelo> lista=new PessoaDAO().busca(vV.getTxtBuscaNome().getText());
				preencheTabela(lista);
			}
		});
	}
	
	//Evento disparado quando um cadastro ou alteração é efetuado(a)
	private void acaoRetorno(){
		Dispatcher.getInstance().addListener(new Listener() {
			@Override
			public void receivedEvent(Event event) {
				if(event.getName().equals("alteracao") || event.getName().equals("cadastrar") || event.getName().equals("fechar") || event.getName().equals("fluxo")){
					preencheTabelaDAO();
				}
			}
		});
	}
	
//Preenchimento da tabela
	private void preencheTabelaDAO(){//Realiza a busca no banco de dados
		preencheTabela(new PessoaDAO().listar());
	}
	private void preencheTabela(Vector<PessoaModelo> listaPessoas){//Insere os dados na tabela
		//Apaga os dados da tabela
		vV.getTable().clearSelection();
		vV.getTable().removeAll();
		//Recria as colunas da tabela
		Vector<String> colunas = new Vector<String>();
			colunas.add("ID");
			colunas.add("Nome");
			colunas.add("Telefone");
			colunas.add("Fluxo");
			colunas.add("Visualizar");
			colunas.add("Editar");
			colunas.add("Remover");
		//Cria um novo modelo para a tabela
		VisitanteTableModel vTM =new VisitanteTableModel(listaPessoas);
			vV.getTable().setModel(vTM);
		//Altera a largura das colunas da tabela
	        //Determina um tamanho Máximo para as colunas
		        vV.getTable().getColumnModel().getColumn(0).setMaxWidth(0);
		        vV.getTable().getColumnModel().getColumn(1).setMaxWidth(490);
		        vV.getTable().getColumnModel().getColumn(2).setMaxWidth(120);
		        vV.getTable().getColumnModel().getColumn(3).setMaxWidth(50);
		        vV.getTable().getColumnModel().getColumn(4).setMaxWidth(50);
		        vV.getTable().getColumnModel().getColumn(5).setMaxWidth(50);
		        vV.getTable().getColumnModel().getColumn(6).setMaxWidth(55);
		        vV.getTable().getColumnModel().getColumn(7).setMaxWidth(0);
	        //Determina um tamanho Mínimo para as colunas
		        vV.getTable().getColumnModel().getColumn(0).setMinWidth(0);
		        vV.getTable().getColumnModel().getColumn(1).setMinWidth(490);
		        vV.getTable().getColumnModel().getColumn(2).setMinWidth(120);
		        vV.getTable().getColumnModel().getColumn(3).setMinWidth(50);
		        vV.getTable().getColumnModel().getColumn(4).setMinWidth(50);
		        vV.getTable().getColumnModel().getColumn(5).setMinWidth(50);
		        vV.getTable().getColumnModel().getColumn(6).setMinWidth(55);
		        vV.getTable().getColumnModel().getColumn(7).setMinWidth(0);
	}
	
//Ações eplicadas à tabela
	public void acaoTabela(){
		vV.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int c = vV.getTable().getSelectedColumn();//Coluna selecionada
				int l = vV.getTable().getSelectedRow();//Linha selecionada
				int ID = (int) vV.getTable().getValueAt(l, 0);//Obtenção do ID que está oculto na tabela
				JInternalFrame jIF=new JInternalFrame();//Pseudo inicialização para não indicar erro
				//JIF é sigla para JInternalFrame
				switch (c) {
					case 3://Fluxo
						boolean situacao = (boolean)vV.getTable().getValueAt(l, 7);
						jIF = new FluxoVisitantesView((String)vV.getTable().getValueAt(l, 1), situacao);
						new FluxoControle((FluxoVisitantesView)jIF, ID, situacao);//Janela - ID - Situação
					break;
					case 4://Visualizar
						jIF = new VisualizaDadosView(new PessoaDAO().buscaPorID(ID));
						new VisualizarControle((VisualizaDadosView)jIF, deskP, ID);
					break;
					case 5://Alterar
						jIF = new AlterarVisitantesView();//Instancia do JInternalFrame
						new AlterarControle((AlterarVisitantesView)jIF, ID);
					break;
					case 6://Excluir
						jIF = new ExcluiVisitantesView((String) vV.getTable().getValueAt(l, 1));
						new ExcluirControle((ExcluiVisitantesView)jIF, ID);
					break;
				}
				vV.getContentPane().add(jIF);//Adição do JIF ao JFrame principal
				deskP.moveToFront(jIF);//Move o JIF para frente
				
			}
		});
	}
	
	private void acaoFecharJanela(){//Ação disparada ao fecha a janela, para evitar que 
		vV.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(new FluxoDAO().contaSaidasFaltantes()<=0){
					vV.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Registre as saídas que ainda estão em aberto antes de fechar!");
					vV.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});		
	}
	private void acaoMenuSair(){//Ação disparada ao fecha a janela, para evitar que
		vV.getMiSair().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(new FluxoDAO().contaSaidasFaltantes()<=0){
					vV.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Registre as saídas que ainda estão em aberto antes de fechar!");
					vV.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});		
	}
	private void acaoMenuAjuda(){//Ação disparada ao fecha a janela, para evitar que
		vV.getMiAjuda().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "AJUDA");
			}
		});		
	}
	
}

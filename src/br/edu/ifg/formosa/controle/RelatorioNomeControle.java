package br.edu.ifg.formosa.controle;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JDesktopPane;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.edu.ifg.formosa.dao.PessoaDAO;
import br.edu.ifg.formosa.dao.RelatorioDAO;
import br.edu.ifg.formosa.dao.UsuarioDAO;
import br.edu.ifg.formosa.modelo.FluxoModelo;
import br.edu.ifg.formosa.modelo.PessoaModelo;
import br.edu.ifg.formosa.modelo.PessoaRelatorioModelo;
import br.edu.ifg.formosa.modelo.UsuarioModelo;
import br.edu.ifg.formosa.view.GerarRelatorioNomeView;
import br.edu.ifg.formosa.view.VisualizaDadosView;

public class RelatorioNomeControle {

	private GerarRelatorioNomeView gRNV;
	private final JDesktopPane deskP;
	
	public RelatorioNomeControle(GerarRelatorioNomeView gRNV,JDesktopPane deskP, int ID){
		this.gRNV=gRNV;
		this.deskP=deskP;
		addAcaoCancelar(ID);
		addAcaoImprimir(ID);
	}
	
	private void addAcaoCancelar(final int ID){
		gRNV.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Reabertura de VisualizaDadosView
					VisualizaDadosView vDV = new VisualizaDadosView(new PessoaDAO().buscaPorID(ID));
					new VisualizarControle(vDV, deskP, ID);
					deskP.add(vDV);//Adição do vDV ao JFrame principal
					deskP.moveToFront(vDV);//Move o vDV para frente
				//Fechamento de GerarRelatorioNomeView
					gRNV.dispose();
			}
		});
	}
	
	private void addAcaoImprimir(final int ID){
		gRNV.getBtnImprimir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Testar campos nulos
				String cpf  =gRNV.getfTxtCPF().getText().replace(".", "").replace("-", "");//Pega valor do campo CPF/Login
				
				@SuppressWarnings("deprecation")
				String senha=gRNV.getPwSenha().getText();//Pega valor do campo senha
				
				//Testa se existe algum campo vazio
				if(cpf.equalsIgnoreCase("") || cpf.equalsIgnoreCase(null) || senha.equalsIgnoreCase("") || senha.equalsIgnoreCase(null)){
					gRNV.setLblTextoInfomativo("Preencha todos os campos!");
				}else{
					UsuarioModelo uM = new UsuarioModelo(cpf, senha);
					//Manda buscar no BD
					UsuarioModelo userBD = new UsuarioDAO().logarAdm(uM);
					if(uM.getSenha().equals(userBD.getSenha()) && uM.getCpf().equals(userBD.getCpf())){
						//Busca de resultados no BD
						//__Dados pessoais
							PessoaModelo pM = new PessoaDAO().buscaPorID(ID);
						//__Registro de fluxo
							List<FluxoModelo> fluxo = new RelatorioDAO().porPessoa(ID);
						//__Modelo do relatório
							List<PessoaRelatorioModelo> lista = new ArrayList<PessoaRelatorioModelo>();
								lista.add(new PessoaRelatorioModelo(pM, fluxo));
						//Adicionar funções do Report
								try{
									// Compilação do JRXML
									JasperReport report = JasperCompileManager.compileReport("relatorios/relatorioNome.jrxml");
									//JasperReport reportSub = JasperCompileManager.compileReport("relatorios/relatorioNome_Subreport.jrxml");
									
									URL web = getClass().getResource("../../../../../relatorios/");
									System.out.println(web.toString());
																			
									Map<String, Object> parametro = new HashMap<String, Object>();
										parametro.put("SUBREPORT_DIR", web.toString());
									
									JasperPrint print = JasperFillManager.fillReport(report, parametro, new JRBeanCollectionDataSource(lista));
																		
									// Exportação do relatório para outro formato, no caso PDF
									Calendar dataAtual = Calendar.getInstance(new Locale("pt","br"));//Pega a data atual do sistema
									DateFormat dF = DateFormat.getDateInstance();//Utilizado para a conversão de Date para String
									String separadorSO =System.getProperty("file.separator");//Separador de pastas do SO \ para Windows e / para Linux
									String caminhoArquivo=System.getProperty("user.home")+separadorSO+"Desktop"+separadorSO+"Relatório de fluxo por nome - SiD "+pM.getNome()+" "+dF.format((dataAtual.getTime())).replace("/", ".")+".pdf";
									
									JasperExportManager.exportReportToPdfFile(print,caminhoArquivo);
									
									//Abre o arquivo
									Desktop.getDesktop().open(new File(caminhoArquivo));
								} catch (Exception ex){
									System.err.println(ex.getMessage());
								}
						
						//Fecha a janela
						gRNV.dispose();
						
						//Abre o arquivo
						//...
					}else {
						gRNV.setLblTextoInfomativo("Usuário não encontrado!");
					}//Fim do else para testar se a busca retornou algum resultado
				}
			}
		});
	}
	
}

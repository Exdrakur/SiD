package br.edu.ifg.formosa.controle;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.edu.ifg.formosa.dao.RelatorioDAO;
import br.edu.ifg.formosa.dao.UsuarioDAO;
import br.edu.ifg.formosa.modelo.RelatorioModelo;
import br.edu.ifg.formosa.modelo.UsuarioModelo;
import br.edu.ifg.formosa.view.GeraRelatorioDataView;

public class RelatorioDataControle {

	private static GeraRelatorioDataView gRDV;
	
	public RelatorioDataControle(GeraRelatorioDataView gRDV){
		RelatorioDataControle.gRDV=gRDV;
		addAcaoCancelar();
		addAcaoGerar();
	}
	
	private void addAcaoGerar(){
		gRDV.getBtnGerar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Testar campos nulos
				String cpf  =gRDV.getTxtCPF().getText().replace(".", "").replace("-", "");//Pega valor do campo CPF/Login
				
				@SuppressWarnings("deprecation")
				String senha=gRDV.getPfSenha().getText();//Pega valor do campo senha
				
				//Testa se existe algum campo vazio
				if(cpf.equalsIgnoreCase("") || cpf.equalsIgnoreCase(null) || senha.equalsIgnoreCase("") || senha.equalsIgnoreCase(null)){
					RelatorioDataControle.mensagemErro("Preencha todos os campos!");
				}else{
					UsuarioModelo uM = new UsuarioModelo(cpf, senha);
					//Manda buscar no BD
					UsuarioModelo userBD = new UsuarioDAO().logarAdm(uM);
					System.out.println("SENHA: "+uM.getSenha());
					System.out.println("SENHA BD:"+userBD.getSenha());
					System.out.println("CPF BD:"+userBD.getCpf());
					System.out.println("CPF:"+uM.getCpf());
					if(uM.getSenha().equals(userBD.getSenha()) && uM.getCpf().equals(userBD.getCpf())){
						List<RelatorioModelo> lista = new RelatorioDAO().porData(//Chamado do mpetodo que realiza a busca no BD
								gRDV.getDatePInicio().getDateEUA(),
								gRDV.getDatePTermino().getDateEUA()
//								mudaStringData(gRDV.getfTextDataInicio().getText()),//Passagem da data inicial na forma necessária
//								mudaStringData(gRDV.getfTextDataTermino().getText())
							);//Passagem da data final na forma necessária
						//Adicionar funções do Report
							try{
								// compilacao do JRXML
								JasperReport report = JasperCompileManager.compileReport("relatorios/relatorioData.jrxml");
								// preenchimento do relatorio, note que o metodo recebe 3 parametros:
								// 1 - o relatorio
								//
								// 2 - um Map, com parametros que sao passados ao relatorio
								// no momento do preenchimento. No nosso caso eh null, pois nao
								// estamos usando nenhum parametro
								//
								// 3 - o data source. Note que nao devemos passar a lista diretamente,
								// e sim "transformar" em um data source utilizando a classe
								// JRBeanCollectionDataSource
								JasperPrint print = JasperFillManager.fillReport(report, null,new JRBeanCollectionDataSource(lista));
								// exportacao do relatorio para outro formato, no caso PDF
								String caminhoArquivo=gerarNomeRel();
								JasperExportManager.exportReportToPdfFile(print,caminhoArquivo);
								
								//Abre o arquivo
									Desktop.getDesktop().open(new File(caminhoArquivo));
							} catch (Exception ex){
								System.err.println(ex);
							}
						
						//Fecha a janela
						gRDV.dispose();
						
					}else {
						RelatorioDataControle.mensagemErro("Usuário não encontrado!");
					}//Fim do else para testar se a busca retornou algum resultado
				}
			}
		});
	}
	
	private static void mensagemErro(String msg){
		gRDV.getLbAviso().setText(msg);//Altera texto informativo da JLabel informativa
	}
	
	private void addAcaoCancelar(){
		gRDV.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gRDV.dispose();
			}
		});
	}
	private String gerarNomeRel(){
		Calendar dataAtual = Calendar.getInstance(new Locale("pt","br"));//Pega a data atual do sistema
		DateFormat dF = DateFormat.getDateTimeInstance();//Utilizado para a conversão de Date para String
		String separadorSO =System.getProperty("file.separator");//Separador de pastas do SO \ para Windows e / para Linux
		return (
				System.getProperty("user.home")+separadorSO//Diretório do usuário
				+"Desktop"+separadorSO//Pasta desktop
				+"Relatório de fluxo por data - SiD "//Nome do arquivo
					+dF.format(dataAtual.getTime()).replace(":", ".").replace("/", ".")+"hrs"
				+".pdf"//Extensão
		);
	}
}

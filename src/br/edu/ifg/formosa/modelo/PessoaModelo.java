package br.edu.ifg.formosa.modelo;

import javax.swing.ImageIcon;



public class PessoaModelo {

	private int id;
	private String nome;
	private String email;
	private String fone;
	private String rg;
	private String orgaoExped;

	//Imagens dos ícones utilizados como botões na tabela
	private ImageIcon cFluxo = new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/img/entrada.png"));
	private ImageIcon cVisualizar = new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/img/vizualisar.png"));
	private ImageIcon cEditar = new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/img/alterar.png"));
	private ImageIcon cExcluir = new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/img/remover.png"));
	
	//Situação de fluxo(True=Dentro,False=Fora)
	private boolean situFluxo;
	
	public int getID(){	return id;	}
	public void setID(int id){	this.id=id;	}
	public String getNome() {		return nome;	}
	public void setNome(String nome) {		this.nome = nome;	}
	public String getEmail() {		return email;	}
	public void setEmail(String email) {		this.email = email;	}
	public String getFone() {		return fone;	}
	public void setFone(String fone) {		this.fone = fone;	}
	public String getRg() {		return rg;	}
	public void setRg(String rg) {		this.rg = rg;	}
	public String getOrgaoExped() {		return orgaoExped;	}
	public void setOrgaoExped(String orgaoExped) {		this.orgaoExped = orgaoExped;	}
	public ImageIcon getcExcluir() {	return cExcluir;	}
	public void setcExcluir(ImageIcon cExcluir) {	this.cExcluir = cExcluir;}
	public ImageIcon getcEditar() {	return cEditar;	}
	public void setcEditar(ImageIcon cEditar) {	this.cEditar = cEditar;	}
	public ImageIcon getcVisualizar() {	return cVisualizar;}
	public void setcVisualizar(ImageIcon cVisualizar) {this.cVisualizar = cVisualizar;	}
	public ImageIcon getcFluxo() {	return cFluxo;}
	public void setcFluxo(String nomeFluxo) {this.cFluxo = new ImageIcon(this.getClass().getClassLoader().getResource("br/edu/ifg/formosa/img/"+nomeFluxo));}
	public void setcFluxo(ImageIcon cFluxo) {this.cFluxo = cFluxo;	}
	public boolean isSituFluxo() {return situFluxo;}
	public void setSituFluxo(boolean situFluxo) {this.situFluxo = situFluxo;}
	
	
	
}

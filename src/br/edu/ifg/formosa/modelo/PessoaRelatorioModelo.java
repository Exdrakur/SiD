package br.edu.ifg.formosa.modelo;

import java.util.ArrayList;
import java.util.List;

public class PessoaRelatorioModelo {

	private String nome;
	private String fone;
	private String rg;
	private String orgaoExp;
	private List<FluxoModelo> listFluxo = new ArrayList<FluxoModelo>();
	
	public PessoaRelatorioModelo(PessoaModelo p, List<FluxoModelo> listFluxo){
		this.nome=p.getNome();
		this.fone=p.getFone();
		this.rg=p.getRg();
		this.orgaoExp=p.getOrgaoExped();
		this.listFluxo=listFluxo;
	}

	public String getNome() {
		return nome;
	}

	public String getFone() {
		return fone;
	}

	public String getRg() {
		return rg;
	}

	public String getOrgaoExp() {
		return orgaoExp;
	}

	public List<FluxoModelo> getListFluxo() {
		return listFluxo;
	}
	
}
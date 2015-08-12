package br.edu.ifg.formosa.util;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import br.edu.ifg.formosa.modelo.PessoaModelo;

public class VisitanteTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	Class<?>[] classes = { Integer.class, String.class, String.class, ImageIcon.class, ImageIcon.class, ImageIcon.class, ImageIcon.class, Boolean.class};
	//ID - Nomes - Fone - Fluxo - Ver - Editar - Excluir - Situação de Fluxo(True=Dentro,False=Fora)
     private Vector<PessoaModelo> linhas;
     private String[] colunas = {"ID","Nome","Telefone","Fluxo","Ver","Editar","Excluir","Situacao"};//Nome das colunas
     private final int ID=0, NOME=1,FONE=2,FLUXO=3,VER=4,EDITAR=5,EXCLUIR=6,SITUACAO_FLUXO=7;
     
     //Construtor
    public VisitanteTableModel(Vector<PessoaModelo> linhas) {	this.linhas=linhas;	}
     
    @Override//Retira a edição dos campos da tabela
	public boolean isCellEditable(int row, int column){	return false;	}
     
    @Override//Pegar o valor de uma célula indicando sua posição em Linha e Coluna
    public Object getValueAt(int linha, int coluna) {
    	switch (coluna) {
			case ID:
				return this.linhas.get(linha).getID();
			case NOME:
				return this.linhas.get(linha).getNome();
			case FONE:
				return this.linhas.get(linha).getFone();
			case FLUXO:
				return this.linhas.get(linha).getcFluxo();
			case VER:
				return this.linhas.get(linha).getcVisualizar();
			case EDITAR:
				return (ImageIcon)this.linhas.get(linha).getcEditar();
			case EXCLUIR:
				return this.linhas.get(linha).getcExcluir();
			case SITUACAO_FLUXO:
				return this.linhas.get(linha).isSituFluxo();
			default://Exeção: Array não encontrado
				throw new IndexOutOfBoundsException("Index da coluna não encontrado. Verifique se o índice está entre 0 e "+getColumnCount());
    	}
	}
     
     @Override//Passa um valor para uma célula indicando sua posição em Linha e Coluna
     public void setValueAt(Object object, int linha, int coluna) {
    	 switch (coluna) {
			case ID:
				this.linhas.get(linha).setID((Integer)object);
			break;
			case NOME:
				this.linhas.get(linha).setNome((String)object);
				break;
			case FONE:
				this.linhas.get(linha).setFone((String)object);
				break;
			case FLUXO:
				this.linhas.get(linha).setcFluxo((String)object);
				break;
			case VER:
				this.linhas.get(linha).setcVisualizar((ImageIcon)object);
				break;
			case EDITAR:
				this.linhas.get(linha).setcEditar((ImageIcon)object);
				break;
			case EXCLUIR:
				this.linhas.get(linha).setcExcluir((ImageIcon)object);
				break;
			case SITUACAO_FLUXO:
				this.linhas.get(linha).setSituFluxo((boolean)object);
				break;
			default://Exeção: Array não encontrado
				throw new IndexOutOfBoundsException("Index da coluna não encontrado. Verifique se o índice está entre 0 e "+getColumnCount());
		}
    	 
     }
       
     @Override//Contagem da quantidade de linhas
     public int getRowCount() {	return this.linhas.size();	}  
       
     @Override//Contagem da quantidade de colunas
     public int getColumnCount(){	return this.colunas.length;	}
       
     @Override//Pega a classe do objeto que está na coluna selecionada coluna
     public Class<?> getColumnClass(int col) {	return this.classes[col];	}
     
     @Override
     public String getColumnName(int columnIndex){	return colunas[columnIndex];	}
}
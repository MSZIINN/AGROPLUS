package br.ufpb.dcx.agroplus.model;
import java.time.LocalDate;
public class VacinaMedicamento {
 private int id; private String nome,tipo,lote; private LocalDate dataValidade; private int quantidadeEstoque;
 public VacinaMedicamento(int id,String nome,String tipo,String lote,LocalDate dataValidade,int quantidadeEstoque){this.id=id;this.nome=nome;this.tipo=tipo;this.lote=lote;this.dataValidade=dataValidade;this.quantidadeEstoque=quantidadeEstoque;}
 public void atualizarEstoque(int quantidade){quantidadeEstoque+=quantidade;}
 public int getId(){return id;} public void setId(int v){id=v;} public String getNome(){return nome;} public void setNome(String v){nome=v;} public String getTipo(){return tipo;} public void setTipo(String v){tipo=v;} public String getLote(){return lote;} public void setLote(String v){lote=v;} public LocalDate getDataValidade(){return dataValidade;} public void setDataValidade(LocalDate v){dataValidade=v;} public int getQuantidadeEstoque(){return quantidadeEstoque;} public void setQuantidadeEstoque(int v){quantidadeEstoque=v;}
}

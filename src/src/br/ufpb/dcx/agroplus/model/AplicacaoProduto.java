package br.ufpb.dcx.agroplus.model;
import java.time.LocalDate;
public class AplicacaoProduto {
 private int id,animalId,produtoId; private String dose; private LocalDate dataAplicacao; private String funcionarioResponsavel,observacoes;
 public AplicacaoProduto(int id,int animalId,int produtoId,String dose,LocalDate dataAplicacao,String funcionarioResponsavel,String observacoes){this.id=id;this.animalId=animalId;this.produtoId=produtoId;this.dose=dose;this.dataAplicacao=dataAplicacao;this.funcionarioResponsavel=funcionarioResponsavel;this.observacoes=observacoes;}
 public int getId(){return id;} public void setId(int v){id=v;} public int getAnimalId(){return animalId;} public void setAnimalId(int v){animalId=v;} public int getProdutoId(){return produtoId;} public void setProdutoId(int v){produtoId=v;} public String getDose(){return dose;} public void setDose(String v){dose=v;} public LocalDate getDataAplicacao(){return dataAplicacao;} public void setDataAplicacao(LocalDate v){dataAplicacao=v;} public String getFuncionarioResponsavel(){return funcionarioResponsavel;} public void setFuncionarioResponsavel(String v){funcionarioResponsavel=v;} public String getObservacoes(){return observacoes;} public void setObservacoes(String v){observacoes=v;}
}

package br.ufpb.dcx.agroplus.model;

import java.time.LocalDate;

public class Animal {
    private int id; private String identificacaoUnica; private String especie; private int idade; private float pesoAtual; private String situacaoSaude; private LocalDate dataNascimento; private String lote; private boolean ativo;
    public Animal(int id,String identificacaoUnica,String especie,int idade,float pesoAtual,String situacaoSaude,LocalDate dataNascimento,String lote){this.id=id;this.identificacaoUnica=identificacaoUnica;this.especie=especie;this.idade=idade;this.pesoAtual=pesoAtual;this.situacaoSaude=situacaoSaude;this.dataNascimento=dataNascimento;this.lote=lote;this.ativo=true;}
    public void cadastrar(){this.ativo=true;} public void atualizarPeso(float novoPeso){this.pesoAtual=novoPeso;} public void atualizarSaude(String novaSituacao){this.situacaoSaude=novaSituacao;} public void excluir(){this.ativo=false;}
    public int getId(){return id;} public void setId(int id){this.id=id;} public String getIdentificacaoUnica(){return identificacaoUnica;} public void setIdentificacaoUnica(String v){identificacaoUnica=v;} public String getEspecie(){return especie;} public void setEspecie(String v){especie=v;} public int getIdade(){return idade;} public void setIdade(int v){idade=v;} public float getPesoAtual(){return pesoAtual;} public void setPesoAtual(float v){pesoAtual=v;} public String getSituacaoSaude(){return situacaoSaude;} public void setSituacaoSaude(String v){situacaoSaude=v;} public LocalDate getDataNascimento(){return dataNascimento;} public void setDataNascimento(LocalDate v){dataNascimento=v;} public String getLote(){return lote;} public void setLote(String v){lote=v;} public boolean isAtivo(){return ativo;} public void setAtivo(boolean v){ativo=v;}
}

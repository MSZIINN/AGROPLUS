package br.ufpb.dcx.agroplus.model;
import java.time.LocalDate; import java.util.List;
public class VisitaVeterinaria {
 private int id; private LocalDate dataVisita; private int funcionarioId; private String objetivo,observacoes; private List<Integer> animaisAtendidos;
 public VisitaVeterinaria(int id,LocalDate dataVisita,int funcionarioId,String objetivo,String observacoes,List<Integer> animaisAtendidos){this.id=id;this.dataVisita=dataVisita;this.funcionarioId=funcionarioId;this.objetivo=objetivo;this.observacoes=observacoes;this.animaisAtendidos=animaisAtendidos;}
 public int getId(){return id;} public void setId(int v){id=v;} public LocalDate getDataVisita(){return dataVisita;} public void setDataVisita(LocalDate v){dataVisita=v;} public int getFuncionarioId(){return funcionarioId;} public void setFuncionarioId(int v){funcionarioId=v;} public String getObjetivo(){return objetivo;} public void setObjetivo(String v){objetivo=v;} public String getObservacoes(){return observacoes;} public void setObservacoes(String v){observacoes=v;} public List<Integer> getAnimaisAtendidos(){return animaisAtendidos;} public void setAnimaisAtendidos(List<Integer> v){animaisAtendidos=v;}
}

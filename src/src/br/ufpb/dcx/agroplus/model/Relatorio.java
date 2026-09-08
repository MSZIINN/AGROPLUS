package br.ufpb.dcx.agroplus.model;
import java.io.File; import java.io.FileWriter; import java.io.IOException; import java.time.LocalDate; import java.util.Map;
public class Relatorio {
 private int id; private String tipo; private LocalDate dataGeracao; private Map<String,Object> parametros; private byte[] conteudo;
 public Relatorio(int id,String tipo,LocalDate dataGeracao,Map<String,Object> parametros,byte[] conteudo){this.id=id;this.tipo=tipo;this.dataGeracao=dataGeracao;this.parametros=parametros;this.conteudo=conteudo;}
 public static File gerar(String tipo,Map<String,Object> parametros,byte[] conteudo){File temp=new File("relatorio_"+tipo+"_"+LocalDate.now()+".txt");try(FileWriter fw=new FileWriter(temp)){fw.write("=== RELATÓRIO AGROPLUS: "+tipo.toUpperCase()+" ===\n");fw.write("Data de Geração: "+LocalDate.now()+"\n");fw.write("Parâmetros: "+parametros.toString()+"\n\n");fw.write(new String(conteudo));}catch(IOException e){e.printStackTrace();}return temp;}
 public void exportarPDF(){System.out.println("[INFO] Relatório exportado com sucesso para PDF (Simulado: relatorio_"+tipo+"_"+id+".pdf)");} public void exportarExcel(){System.out.println("[INFO] Relatório exportado com sucesso para Excel (Simulado: relatorio_"+tipo+"_"+id+".xlsx)");}
 public int getId(){return id;} public void setId(int v){id=v;} public String getTipo(){return tipo;} public void setTipo(String v){tipo=v;} public LocalDate getDataGeracao(){return dataGeracao;} public void setDataGeracao(LocalDate v){dataGeracao=v;} public Map<String,Object> getParametros(){return parametros;} public void setParametros(Map<String,Object> v){parametros=v;} public byte[] getConteudo(){return conteudo;} public void setConteudo(byte[] v){conteudo=v;}
}

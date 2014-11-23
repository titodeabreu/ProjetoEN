import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeradorDAT {
	 
	public static List<File> lista = new ArrayList<File>();

	public String pathDAT;
	public String pathPRN;

	public List<File> getListFile(String dir) {
		File file = new File(dir);
		File[] files = file.listFiles();
		try {
			for (File f : files) {
				lista.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public File getFile(String dir) {
		File file = new File(dir);
		return file;
	}
	
	public static String lerArquivo(File file) throws IOException {
		String conteudo = "";
		FileReader reader = new FileReader(file);
		@SuppressWarnings("resource")
		BufferedReader buffReader = new BufferedReader(reader);
		while (buffReader.readLine() != null) {
			conteudo += buffReader.readLine()+"\n";
		}
		return conteudo;
	}
	
	public void gerarArquivoPRN(File filePRN, File fileDAT, String path, int index) {
		try {
			String arquivo = formataArquivoPRN(filePRN, fileDAT, path, index);
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(pathPRN));
			buffWrite.append(arquivo);
			buffWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	@SuppressWarnings("resource")
	private String formataArquivoPRN(File filePRN, File fileDAT, String path, int index) throws IOException {
		
		List<String> prn = new ArrayList<String>();
		List<String> dat = new ArrayList<String>();
		String stringAux = "";
		String cabecalhoPRN = "";
			
		FileReader readerPRN = new FileReader(filePRN);
		BufferedReader buffReaderPRN = new BufferedReader(readerPRN);
		
		//cabecalhoPRN = buffReaderPRN.readLine(); //LÊ SOMENTE A PRIMEIRA LINHA
		for (int i = 0; i < 29; i++) {
			prn.add(buffReaderPRN.readLine());
		}
		
		cabecalhoPRN = prn.get(0);
		String tmpMes = PRNReaderUtil.verificaDadosCabecalho(cabecalhoPRN.substring(17,26));
		String mes = tmpMes.replace(" ", "").trim();
		String tmpAno = PRNReaderUtil.verificaDadosCabecalho(cabecalhoPRN.substring(30,32));
		String ano = tmpAno.replace(" ", "").trim();
		
		stringAux = stringAux.concat("n periodos, mes inicial, ano inicial, =1(desp. hidrotermico), =2(valor da agua)"+"\n");
		stringAux = stringAux.concat(" xxxx xxxx xxxx xxxx"+"\n");
		stringAux = stringAux.concat(PRNReaderUtil.getCabecalho(prn));
		
		stringAux = stringAux.concat("energia armazenada inicial"+"\n");
		stringAux = stringAux.concat(" xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");
		stringAux = stringAux.concat(PRNReaderUtil.getEnergiaArmazenadaInicial(prn));
		
		stringAux = stringAux.concat("energias afluentes passadas      (REFERENTES A 65% DE VOLUME ARMAZENADO)"+"\n");
		stringAux = stringAux.concat("mes xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");	
		stringAux = stringAux.concat(PRNReaderUtil.getEnergiaAfluentePassada(prn));
		
		stringAux = stringAux.concat("energias afluentes previstas     (REFERENTES A 65% DE VOLUME ARMAZENADO)"+"\n");
		stringAux = stringAux.concat("mes xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");
		
		stringAux = stringAux.concat(PRNReaderUtil.getEnergiaAfluentePrevista(prn));
		
		stringAux = stringAux.concat("iute  nome GNL     lag  (INFORMACAO SOBRE ANTECIPACAO DE DESPACHO GNL"+"\n");
		
		FileReader readerDAT = new FileReader(fileDAT);
		BufferedReader buffReaderDAT = new BufferedReader(readerDAT);
		for (int i = 0; i < 9; i++) {
			dat.add(buffReaderDAT.readLine());
		}
		
		stringAux = stringAux.concat(DATReaderUtil.getDadosDAT(dat));
		stringAux = stringAux.concat("nada");
		
		this.pathPRN = path+"/nw-"+EnumMesArquivo.getSigla(mes)+ano+".dat";
		
		return stringAux;
	}
	
	
	public void gerarArquivoMAP(File fileArquivo, String path, int index) {
		try {
			String arquivo = formataArquivoMAP(fileArquivo,path);
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(pathDAT));
			buffWrite.append(arquivo);
			buffWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public String formataArquivoMAP(File filePRN, String path) throws IOException {
		
		String cabecalhoPRN = "";
		String stringAux = "";
		
		FileReader readerPRN = new FileReader(filePRN);
		BufferedReader buffReaderPRN = new BufferedReader(readerPRN);

		cabecalhoPRN = buffReaderPRN.readLine(); //LÊ SOMENTE A PRIMEIRA LINHA
		String tmpMes = PRNReaderUtil.verificaDadosCabecalho(cabecalhoPRN.substring(17,26));
		String mes = tmpMes.replace(" ", "").trim();
		String tmpAno = PRNReaderUtil.verificaDadosCabecalho(cabecalhoPRN.substring(30,32));
		String ano = tmpAno.replace(" ", "").trim();
		

		stringAux = stringAux.concat("ARQUIVO DE DADOS GERAIS     : nw-"+EnumMesArquivo.getSigla(mes)+ano+".dat"+"\n");
		stringAux = stringAux.concat("ARQUIVO COM CORTES BENDERS  : cortes.dat"+"\n");
		stringAux = stringAux.concat("ARQUIVO CABECALHO DE CORTES : cortesh.dat"+"\n");
		stringAux = stringAux.concat("RELATORIO DE SAIDA          : saida-"+EnumMesArquivo.getSigla(mes)+ano+".rel"+"\n");
		stringAux = stringAux.concat("ARQUIVO P/DESPACHO HIDROTERM: newdesp.dat"+"\n");
		stringAux = stringAux.concat("ARQUIVO DE RESTRIOCOES SAR  : RSAR.DAT"+"\n");
		stringAux = stringAux.concat("ARQUIVO DE CABECALHO SAR    : RSARH.DAT"+"\n");
		stringAux = stringAux.concat("ARQUIVO DE INDICE SAR       : RSARI.DAT"+"\n");
		
		this.pathDAT = path+"/arquivos.dat";

		return stringAux;
		
	}
	
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class GeradorDAT {
	
	public static List<File> lista = new ArrayList<File>();

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
	
	public static void gerarArquivosEntrada(JFrame frame,File filePRN, File fileDAT, String path) {
//		try {
//			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
//			buffWrite.append(formataArquivoEntrada(filePRN, fileDAT));
//			buffWrite.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			System.out.println("#######################################");
			System.out.println(formataArquivosEntrada(filePRN, fileDAT));
			System.out.println("#######################################\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String formataArquivosEntrada(File filePRN, File fileDAT) throws IOException {
		List<String> prn = new ArrayList<String>();
		String stringAux = "";
		stringAux = stringAux.concat("n periodos, mes inicial, ano inicial, =1(desp. hidrotermico), =2(valor da agua)"+"\n");
		stringAux = stringAux.concat(" xxxx xxxx xxxx xxxx"+"\n");
			
			FileReader reader = new FileReader(filePRN);
			BufferedReader buffReader = new BufferedReader(reader);
			for (int i = 0; i < 20; i++) {
				prn.add(buffReader.readLine());
			}
			//System.out.println(prn);
			
		stringAux = stringAux.concat(" "+getCabecalho(prn));
		stringAux = stringAux.concat("energia armazenada inicial"+"\n");
		stringAux = stringAux.concat(" xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");
		stringAux = stringAux.concat(prn.get(2)+"\n");
		stringAux = stringAux.concat("energias afluentes passadas      (REFERENTES A 65% DE VOLUME ARMAZENADO)"+"\n");
		stringAux = stringAux.concat("mes xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");
		
		stringAux = stringAux.concat(getEnergiaAfluentePassada(prn));
		
		stringAux = stringAux.concat("energias afluentes previstas     (REFERENTES A 65% DE VOLUME ARMAZENADO)"+"\n");
		stringAux = stringAux.concat("mes xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");
		
		stringAux = stringAux.concat(getEnergiaAfluentePrevista(prn));
		
		stringAux = stringAux.concat("iute  nome GNL     lag  (INFORMACAO SOBRE ANTECIPACAO DE DESPACHO GNL"+"\n");
		
		stringAux = stringAux.concat(lerArquivo(fileDAT));
		stringAux = stringAux.concat("nada");
		
		return stringAux;
	}
	


	public static String verificaMes(String mes){
		mes = mes.trim();
		while(mes.length() < 2){
			mes = mes.concat(" ");
		}
		return mes;
	}
	
	public static String verificaDadosCabecalho(String dado){
		dado = dado.trim();
		while(dado.length() < 4){
			dado = " ".concat(dado);
		}
		return dado;
	}
	
	public static String verificaParcela(String parcela){
		parcela = parcela.trim();
		while(parcela.length() < 10){
			parcela = " ".concat(parcela);
		}
		return parcela;
	}
	
	public static String verificaPrimeiraParcela(String parcela){
		parcela = parcela.trim();
		while(parcela.length() < 8){
			parcela = " ".concat(parcela);
		}
		return parcela;
	}
	
	
	public static String getEnergiaAfluentePassada(List<String> prn){
		int aux1=4;
		String mesPRN = "", linha = "";
		do {
			linha = linha.concat(prn.get(aux1)+"\n");
			mesPRN = mesPRN.concat(verificaMes(linha.substring(0, 8)));						//MES
			mesPRN = mesPRN.concat(verificaPrimeiraParcela(linha.substring(9, 16)));		//PRIMEIRA PARCELA
			mesPRN = mesPRN.concat(".");
			mesPRN = mesPRN.concat(verificaParcela(linha.substring(17, 24)));		 		//SEGUNDA PARCELA
			mesPRN = mesPRN.concat(".");
			mesPRN = mesPRN.concat(verificaParcela(linha.substring(25, 32)));				//TERCEIRA PARCELA
			mesPRN = mesPRN.concat(".");
			mesPRN = mesPRN.concat(verificaParcela(linha.substring(33, 40)));				//QUARTA PARCELA
			mesPRN = mesPRN.concat(".");
			//mesPRN = mesPRN.concat(linha.substring(41, 48));								//ZERO
			mesPRN = mesPRN.concat("\n");
			linha = "";
			aux1++;
		} while (aux1 < 15);
		
		return mesPRN;
	}
	
	public static String getEnergiaAfluentePrevista(List<String> prn){
		int aux2=16;
		String mesPRN2 = "", linha2 = "";
		do {
			linha2 = linha2.concat(prn.get(aux2)+"\n");
			mesPRN2 = mesPRN2.concat(verificaMes(linha2.substring(0, 8)));					//MES
			mesPRN2 = mesPRN2.concat(verificaPrimeiraParcela(linha2.substring(9, 16)));		//PRIMEIRA PARCELA
			mesPRN2 = mesPRN2.concat(".");
			mesPRN2 = mesPRN2.concat(verificaParcela(linha2.substring(17, 24)));			//SEGUNDA PARCELA
			mesPRN2 = mesPRN2.concat(".");
			mesPRN2 = mesPRN2.concat(verificaParcela(linha2.substring(25, 32)));			//TERCEIRA PARCELA
			mesPRN2 = mesPRN2.concat(".");
			mesPRN2 = mesPRN2.concat(verificaParcela(linha2.substring(33, 40)));			//QUARTA PARCELA
			mesPRN2 = mesPRN2.concat(".");
			//mesPRN2 = mesPRN2.concat(linha.substring(41, 48));							//ZERO
			mesPRN2 = mesPRN2.concat("\n");
			linha2 = "";
			aux2++;
		} while (aux2 < 18);
		
		return mesPRN2;
	}
	
	private static String getCabecalho(List<String> prn) {
		String linha = "" ,dadoCabecalho = "";
		linha = linha.concat(prn.get(0)+"\n");
		dadoCabecalho = dadoCabecalho.concat(verificaDadosCabecalho(linha.substring(0,16)));	//NUMERO DE PERIODOS
		dadoCabecalho = dadoCabecalho.concat(" ");
		dadoCabecalho = dadoCabecalho.concat(verificaDadosCabecalho(linha.substring(17,26)));	//MES INICIAL
		dadoCabecalho = dadoCabecalho.concat(" ");
		dadoCabecalho = dadoCabecalho.concat(verificaDadosCabecalho(linha.substring(27,32)));	//ANO INICIAL
		dadoCabecalho = dadoCabecalho.concat(" ");
		dadoCabecalho = dadoCabecalho.concat(verificaDadosCabecalho(linha.substring(33,40)));	//1 PARA (DESP. HIDROTERMICO), e 2 PARA (VALOR DA AGUA)
		dadoCabecalho = dadoCabecalho.concat("\n");
		return dadoCabecalho;
	}
}

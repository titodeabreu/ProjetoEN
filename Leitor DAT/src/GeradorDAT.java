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
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
			buffWrite.append(formataArquivosEntrada(filePRN, fileDAT));
			buffWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			System.out.println("#######################################");
//			System.out.println(formataArquivosEntrada(filePRN, fileDAT));
//			System.out.println("#######################################\n");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	


	@SuppressWarnings("resource")
	private static String formataArquivosEntrada(File filePRN, File fileDAT) throws IOException {
		
		List<String> prn = new ArrayList<String>();
		List<String> dat = new ArrayList<String>();
		String stringAux = "";
			
		FileReader readerPRN = new FileReader(filePRN);
		BufferedReader buffReaderPRN = new BufferedReader(readerPRN);
		for (int i = 0; i < 20; i++) {
			prn.add(buffReaderPRN.readLine());
		}
		
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
		
		return stringAux;
	}
	
	
	public static void gerarListaArquivos(JFrame frame, String path, String Arquivostxt) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
			buffWrite.append(Arquivostxt);
			buffWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("null")
	public static String formataArquivosLista(List<File> listFilePRN, JFrame frame, String path) throws IOException {
		
		List<String> prn = new ArrayList<String>();
		String stringAux = "";
		String receberMes = "";
		String receberAno = "";
		String nomeMes;
		List<String> listarDadosLinha1 = new ArrayList<String>();;
		String linha1;
		List<String> listarDadosLinha2 = new ArrayList<String>();;
		String linha2;
		for (File filePRN : listFilePRN) {
				
			FileReader readerPRN = new FileReader(filePRN);
			BufferedReader buffReaderPRN = new BufferedReader(readerPRN);
			for (int i = 0; i < 20; i++) {
				prn.add(buffReaderPRN.readLine());
				receberMes = PRNReaderUtil.verificaDadosCabecalho(prn.get(0).substring(17,26));
				System.out.println(prn);
				//receberAno = PRNReaderUtil.getCabecalho(prn).substring(13,15);
			}
	
			
			nomeMes = NomeArquivo.validarMes(Integer.parseInt(receberMes.trim()));
			//if(listFilePRN.size() > 1)
			linha1 = "ARQUIVO DE DADOS GERAIS     : NW-"+nomeMes+receberAno+".DAT"+"\n";
			listarDadosLinha1.add(linha1);
			
			linha2 = "RELATORIO DE SAIDA          : saida-"+nomeMes+receberAno+".REL"+"\n";
			listarDadosLinha2.add(linha2);
		}

		for (String arquivoDadosLinha1 : listarDadosLinha1) {
		stringAux = stringAux.concat(arquivoDadosLinha1);
		}
		
		stringAux = stringAux.concat("ARQUIVO COM CORTES BENDERS  : CORTES.DAT"+"\n");
		stringAux = stringAux.concat("ARQUIVO CABECALHO DE CORTES : CORTESH.DAT"+"\n");
		for (String arquivoDadosLinha1 : listarDadosLinha2) {
			stringAux = stringAux.concat(arquivoDadosLinha1);
			}
		stringAux = stringAux.concat("ARQUIVO P/DESPACHO HIDROTERM: NEWDESP.DAT"+"\n");
		stringAux = stringAux.concat("ARQUIVO DE RESTRIOCOES SAR  : RSAR.DAT"+"\n");
		stringAux = stringAux.concat("ARQUIVO DE CABECALHO SAR    : RSARH.DAT"+"\n");
		stringAux = stringAux.concat("ARQUIVO DE INDICE SAR       : RSARI.DAT"+"\n");

		return stringAux;
	}
	
	
}

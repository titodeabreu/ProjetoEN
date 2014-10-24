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
			buffWrite.append(formataArquivoDAT(filePRN, fileDAT));
			buffWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String formataArquivoDAT(File filePRN, File fileDAT) throws IOException {
		List<String> prn = new ArrayList<String>();
		String stringAux = "";
		stringAux = stringAux.concat("n periodos, mes inicial, ano inicial, =1(desp. hidrotermico), =2(valor da agua)"+"\n");
		stringAux = stringAux.concat(" xxxx xxxx xxxx xxxx"+"\n");
			
			FileReader reader = new FileReader(filePRN);
			BufferedReader buffReader = new BufferedReader(reader);
			for (int i = 0; i < 20; i++) {
				prn.add(buffReader.readLine());
			}
			System.out.println(prn);
			
		stringAux = stringAux.concat(prn.get(0)+"\n");
		stringAux = stringAux.concat("energia armazenada inicial"+"\n");
		stringAux = stringAux.concat(" xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");
		stringAux = stringAux.concat(prn.get(2)+"\n");
		stringAux = stringAux.concat("energias afluentes passadas      (REFERENTES A 65% DE VOLUME ARMAZENADO)"+"\n");
		stringAux = stringAux.concat("mes xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");
		
		int aux1=4;
		do {
			stringAux = stringAux.concat(prn.get(aux1)+"\n");
			aux1++;
		} while (prn.get(aux1) == "");
		
		stringAux = stringAux.concat("energias afluentes passadas      (REFERENTES A 65% DE VOLUME ARMAZENADO)"+"\n");
		stringAux = stringAux.concat("mes xxsis1.xxx xxsis2.xxx xxsis3.xxx xxsis4.xxx xxsis5.xxx"+"\n");
		
		int aux2=16;
		do {
			stringAux = stringAux.concat(prn.get(aux2)+"\n");
			aux2++;
		} while (prn.get(aux2) == "");
		
		stringAux = stringAux.concat(lerArquivo(fileDAT));
		stringAux = stringAux.concat("nada");
		
		return stringAux;
	}
}

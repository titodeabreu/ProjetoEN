import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GeradorDeDiretorio {
	
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
		
		//TESTE TESTE
		String conteudo = "";
		FileReader reader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(reader);
		while (buffReader.readLine() != null) {
			conteudo += buffReader.readLine()+"\n";
		}
		return conteudo;
	}
	
	public static void gerarArquivosEntrada(JFrame frame,File filePRN, File fileDAT, String path) throws IOException {
		path = path.concat("/arquivo.dat");
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		buffWrite.append(lerArquivo(filePRN));
		buffWrite.append(lerArquivo(fileDAT));
		buffWrite.close();
		
		JOptionPane.showMessageDialog(frame,
			    "Arquivos gerados com sucesso na pasta: "+path);
		
	}
}

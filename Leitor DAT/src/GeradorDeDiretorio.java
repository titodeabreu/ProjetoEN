import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeDiretorio {

	public static int interacao;
	public static List<File> lista = new ArrayList<File>();
	private static int numerodearquivos;
	private static int numerodediretorios;

	public List<File> getLista(String dir) {
		File file = new File(dir);
		File[] files = file.listFiles();
		try {
			for (File f : files) {
				if (f.isDirectory()) {
					numerodediretorios += 1;
					interacao += 1;
					System.out.println(interacao);

					getLista(f.toString());
				} else {
					lista.add(f);
					numerodearquivos += 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static String lerArquivo(File file) throws IOException {
		String conteudo = "";
		FileReader reader = new FileReader(file);
		BufferedReader leitor = new BufferedReader(reader);
		String linha = null;
		while ((linha = leitor.readLine()) != null) {
			conteudo += "\n"+linha;
			System.out.printf("\n", conteudo);
		}
		return conteudo;
	}

	public static int getInteracao() {
		return interacao;
	}

	public static int getNumerodearquivos() {
		return numerodearquivos;
	}

	public static int getNumerodediretorios() {
		return numerodediretorios;
	}

	public static void zerarNumerosDeDirEArq() {
		numerodearquivos = 0;
		numerodediretorios = 0;
	}
}

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ValidaFormUtil {
	
	JFrame frame;
	
	public ValidaFormUtil(JFrame frame) {
		this.frame = frame;
	}

	public boolean validaCampos(String... paths){
		for (int i = 0; i < paths.length; i++) {
			if(!this.validaPath(paths[i])){
				return false;
			}
		}
		return true;
	}
	
	private boolean validaPath(String path){
		if(path == null || path.equals("")){
			JOptionPane.showMessageDialog(frame,
					"Favor informar todos os campos",
					"Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**	Método que verifica se o arquivo está no formato PRN **/
	public boolean validaFilePRN(File filePRN) {
		if (filePRN.getName().toLowerCase().endsWith(".prn")){
			return true;
		}
		return false;
	}
	
}

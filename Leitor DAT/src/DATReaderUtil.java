import java.util.List;


public class DATReaderUtil {
	
	public static String getDadosDAT(List<String> dat){
		String dadoDAT = "";
		for (int i = 0; i < dat.size(); i++) {
			if(!dat.get(i).trim().equals("XXXX  XXXXXXXXXXXX  X  XXXXXXX.XX  XXXXXXX.XX  XXXXXXX.XX"))
				dadoDAT = dadoDAT.concat(dat.get(i))+"\n";	
		}
		return dadoDAT;
	}
}


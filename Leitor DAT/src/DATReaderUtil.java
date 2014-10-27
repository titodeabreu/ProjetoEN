import java.util.List;


public class DATReaderUtil {
	
	public static String getDadosDAT(List<String> dat){
		String dadoDAT = "";
		for (int i = 0; i < dat.size(); i++) {
			dadoDAT = dadoDAT.concat(dat.get(i))+"\n";
		}
		return dadoDAT;
	}
	
}

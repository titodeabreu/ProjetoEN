import java.util.List;


public class PRNReaderUtil {
	
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
	
	private static String verificaDadosEnergia(String dado) {
		dado = dado.trim();
		while(dado.length() < 6){
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
			mesPRN = mesPRN.concat(verificaPrimeiraParcela(linha.substring(9, 16)));		//PRIMEIRA DADO
			mesPRN = mesPRN.concat(".");
			mesPRN = mesPRN.concat(verificaParcela(linha.substring(17, 24)));		 		//SEGUNDA DADO
			mesPRN = mesPRN.concat(".");
			mesPRN = mesPRN.concat(verificaParcela(linha.substring(25, 32)));				//TERCEIRA DADO
			mesPRN = mesPRN.concat(".");
			mesPRN = mesPRN.concat(verificaParcela(linha.substring(33, 40)));				//QUARTA DADO
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
			mesPRN2 = mesPRN2.concat(verificaPrimeiraParcela(linha2.substring(9, 16)));		//PRIMEIRA DADO
			mesPRN2 = mesPRN2.concat(".");
			mesPRN2 = mesPRN2.concat(verificaParcela(linha2.substring(17, 24)));			//SEGUNDA DADO
			mesPRN2 = mesPRN2.concat(".");
			mesPRN2 = mesPRN2.concat(verificaParcela(linha2.substring(25, 32)));			//TERCEIRA DADO
			mesPRN2 = mesPRN2.concat(".");
			mesPRN2 = mesPRN2.concat(verificaParcela(linha2.substring(33, 40)));			//QUARTA DADO
			mesPRN2 = mesPRN2.concat(".");
			//mesPRN2 = mesPRN2.concat(linha.substring(41, 48));							//ZERO
			mesPRN2 = mesPRN2.concat("\n");
			linha2 = "";
			aux2++;
		} while (aux2 < 18);
		
		return mesPRN2;
	}
	
	public static String getCabecalho(List<String> prn) {
		String linha = "" ,dadoCabecalho = "";
		linha = linha.concat(prn.get(0)+"\n");
		dadoCabecalho = dadoCabecalho.concat(" ");
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
	
	public static String getEnergiaArmazenadaInicial(List<String> prn){
		String linha = "" ,energiaInicial = "";
		linha = linha.concat(prn.get(2)+"\n");
		energiaInicial = energiaInicial.concat(" ");
		energiaInicial = energiaInicial.concat(verificaDadosEnergia(linha.substring(0,16)));	//PRIMEIRO DADO
		energiaInicial = energiaInicial.concat(".0  ");
		energiaInicial = energiaInicial.concat(" ");
		energiaInicial = energiaInicial.concat(verificaDadosEnergia(linha.substring(17,24)));	//SEGUNDO DADO
		energiaInicial = energiaInicial.concat(".0  ");
		energiaInicial = energiaInicial.concat(" ");
		energiaInicial = energiaInicial.concat(verificaDadosEnergia(linha.substring(25,32)));	//TERCEIRO DADO
		energiaInicial = energiaInicial.concat(".0  ");
		energiaInicial = energiaInicial.concat(" ");
		energiaInicial = energiaInicial.concat(verificaDadosEnergia(linha.substring(33,40)));	//QUARTO DADO
		energiaInicial = energiaInicial.concat(".0  ");
		energiaInicial = energiaInicial.concat(" ");
		energiaInicial = energiaInicial.concat("\n");
		return energiaInicial;
	}


}

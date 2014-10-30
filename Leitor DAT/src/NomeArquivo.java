

public enum NomeArquivo{
	1("jan"),
	2("fev"),
	3("mar"),
	4("abr"),
	5("mai"),
	6("jun"),
	7("jul"),
	8("ago"),
	9("set"),
	10("out"),
	11("nov"),
	12("dez");
	
	String mes;
	String numeroMes;
	
	NomeArquivo(String numeroMes){
          this.mes = mes;
          this.numeroMes = numeroMes;
      }
	
	 
	 public String getnumeroMes(){
         return this.numeroMes;
     }
     
     public String getNome(){
         return this.mes;
     }
	
	
//	public static String validarMes(int valor)
//	{
//		String[] meses = new String[14];
//		meses[1] = "jan";
//		meses[2] = "fev";
//		meses[3] = "mar";
//		meses[4] = "abr";
//		meses[5] = "mai";
//		meses[6] = "jun";
//		meses[7] = "jul";
//		meses[8] = "ago";
//		meses[9] = "set";
//		meses[10] = "out";
//		meses[11] = "nov";
//		meses[12] = "dez";
//		for(int i=0; i<=12; i++)
//			if(valor == i)
//			{
//				
//				return meses[valor];
//			}
//		
//		
//		return meses[2];
//		
//	}

}





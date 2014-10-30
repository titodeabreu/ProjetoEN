

public enum EnumMesArquivo{

	JANEIRO(1, "Janeiro","jan"),
	FEVEREIRO(2, "Fevereiro","fev"),
	MARCO(3, "Março","mar"),
	ABRIL(4, "Abril","abr"),
	MAIOR(5, "Maio","mai"),
	JUNHO(6, "Junho","jun"),
	JULHO(7, "Julho","jul"),
	AGOSTO(8, "Agosto","ago"),
	SETEMBRO(9, "Setembro","set"),
	OUTUBRO(10, "Outubro","out"),
	NOVEMBRO(11, "Novembro","nov"),
	DEZEMBRO(12, "Dezembro","dez");

	private EnumMesArquivo(int valor, String descricao, String sigla){
		this.valor = valor;
		this.descricao = descricao;
		this.sigla = sigla;
	}

	private int valor;
	private String descricao;
	private String sigla;
	
	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * @return the valor
	 */
	public String getSigla() {
		return sigla;
	}

}





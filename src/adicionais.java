import java.io.Serializable;

public abstract class adicionais implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4311840781765868688L;
	protected double Valor;
	protected String descricao;


	public adicionais(double valor, String desc) {//tudo sendo preenchida pelas filhas
		this.Valor = valor;
		this.descricao = desc;
	}

	public double getValor() {
		return this.Valor;
	}

	public void setValor(double valor) {
		this.Valor = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}

import java.io.Serializable;

public class Vaga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3163029981652102685L;

	static int MAX_VAGAS=50;
	
	private boolean vagas[];
	
	public Vaga(){
		
		this.vagas = new boolean[MAX_VAGAS];
	}
	
	public int conferir() {//conferindo qual vaga está disponivel
		
		for(int cont = 0; cont < this.vagas.length - 10;cont++) {
			if(this.vagas[cont] == false) {
				return cont;
			}
			
		}
		return -1;
	}
	
	public int conferirFixa() {//conferindo se alguma vaga fixa disponivel 
		
		for(int cont = (this.vagas.length-10); cont < this.vagas.length;cont++) {
			if(this.vagas[cont] == false) {
				return cont;
			}
			
		}
		return -1;
	}
	
	public void preecherVaga(int vaga) {
		
		this.vagas[vaga] = true;
		
	}
	
	public void liberarVaga(int vaga) {
		
		this.vagas[vaga] = false;
		
	}
	
}

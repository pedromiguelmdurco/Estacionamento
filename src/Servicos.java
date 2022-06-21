
import java.util.LinkedList;
import java.io.Serializable;
import java.time.*;


public class Servicos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3744262012480385487L;

	static int PRECO = 3;
	
	private int id;
	private String placa;
	private int vagaid = -1;
	private LinkedList<adicionais> adicionais ;
	private LocalDateTime datainicio;
	private LocalDateTime datasaida;
	private LocalDateTime dataminima;
	private int tempParado;
	private double valorTotal = 3;
	private double valAdici = 0;
	
	

	public Servicos(String placaCliente, int id) {
		this.id = id;
		this.placa = placaCliente;
		this.adicionais = new LinkedList<>();
		this.dataminima = LocalDateTime.of(1900,01,01,00,00);
	}
	
	
	public void solicitarVaga(Vaga vagas) {//colancando data de inicio quando chamar esta funcao e vendo se vaga foi setada e preenchendo
		this.datainicio = LocalDateTime.now();
		
		if(this.vagaid == -1) {
			this.vagaid = vagas.conferir();
			
		}
		vagas.preecherVaga(this.vagaid);
		
	}
	
	public boolean solicitarRetirada(Vaga vagas,double descont) {//calculando tempo parado setando hr de saida e liberando vaga e retornando se pode retirar
		int aux;
		LocalDateTime aux2;
		
		this.datasaida = LocalDateTime.now(); 
		
		if(this.dataminima.getHour() <= this.datasaida.getHour()) {
			
			aux = this.datainicio.getMinute();
			
			aux2 = this.datasaida.minusMinutes(aux);
			
			this.tempParado = aux2.getMinute();
			
			aux = this.datainicio.getHour();
			
			aux2 = this.datasaida.minusHours(aux);
			
			aux = aux2.getHour();
			
			while(aux > 0) {
				this.tempParado += 60;
				aux--;
			}
			
			calcPreco(descont);
			
			vagas.liberarVaga(this.vagaid);
		
			return true;
		}else {
			return false;
		}
		
	    
	}
	
	
	public void addAdicionais(adicionais qual) {
		this.adicionais.add(qual);
		
	}
	
	
	private void calcPreco(double desc) {//calculando valor de vaga e adicionais
		
		double aux;
		aux = this.tempParado/15;
		
		this.valorTotal += (aux*3);
		
		
		if(this.valorTotal >50) {
			this.valorTotal = 50;
		}
		
		
		for(adicionais adici : adicionais) {// para cada adicional na lista adicionais repita
			this.valAdici += adici.getValor();
		}
		
		aux = this.valAdici * desc;
		
		this.valAdici -= aux;
		
		this.valorTotal += this.valAdici;
	}
	
	

	//-------------------------GET/SETS-----------------------------
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	
	public int getVagaid() {
		return vagaid;
	}


	public void setVagaid(int vagaid) {
		this.vagaid = vagaid;
	}


	
	public double getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	public LocalDateTime getDatainicio() {
		return datainicio;
	}


	public void setDatainicio(LocalDateTime datainicio) {
		this.datainicio = datainicio;
	}


	public LocalDateTime getDataminima() {
		return dataminima;
	}


	public void setDataminima(LocalDateTime dataminima) {
		this.dataminima = dataminima;
	}
	
	public LocalDateTime getDatasaida() {
		return datasaida;
	}


	public void setDatasaida(LocalDateTime datasaida) {
		this.datasaida = datasaida;
	}


	public LinkedList<adicionais> getAdicionais() {
		return adicionais;
	}


	public void setAdicionais(LinkedList<adicionais> adicionais) {
		this.adicionais = adicionais;
	}


	public int getTempParado() {
		return tempParado;
	}


	public void setTempParado(int tempParado) {
		this.tempParado = tempParado;
	}


	public double getValAdici() {
		return valAdici;
	}


	public void setValAdici(double valAdici) {
		this.valAdici = valAdici;
	}

	public double getValvaga() {
		return this.valorTotal - this.valAdici;
	}


}

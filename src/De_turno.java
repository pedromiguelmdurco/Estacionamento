import java.io.Serializable;

public class De_turno extends Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2825915761465806355L;
	private turno turno;
	
	public De_turno(String nome, String placa_carro, turno turno) {//linha 302 codigo app restaurante final tirar duvida de como fazer com o enum
		super(nome, "De turno", placa_carro,0);
		this.turno = turno;
		
	}

	@Override
	public boolean FinalizarServico(Vaga vaga) {//conferindo se ele sai no turno correspondente
		int cont = this.historico.size() ;
		
		boolean aux = this.servAberto.solicitarRetirada(vaga, this.desconto);
		if(aux == true) {
			
			if(this.servAberto.getDatasaida().getHour() >= turno.getIni() && this.servAberto.getDatasaida().getHour() <= turno.getFim()) {
				this.servAberto.setValorTotal(this.servAberto.getValAdici());
				
			}
			this.valGasto += this.servAberto.getValorTotal();
		
			this.historico.add(cont, this.servAberto);
			
			this.servAberto = null;
		}
		
		
		return aux;
	}
	
}

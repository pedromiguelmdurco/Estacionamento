import java.time.LocalDateTime;

public class Lavagem_Completa extends adicionais implements icalcData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3202731555690617868L;
	static int PRECO = 30;
	static int temp_min = 2;
	
	public Lavagem_Completa(Servicos serv) {//setando valor de lavagem e colocando tempo minimo para poder sair
		super(PRECO,"Lavagem Completa");
		serv.setDataminima(calcDataMin(serv.getDatainicio(), temp_min));
	}

	
	@Override
	 public LocalDateTime calcDataMin(LocalDateTime inicio, int hmin) {
			
			return inicio.plusHours(hmin);
			
		}
}

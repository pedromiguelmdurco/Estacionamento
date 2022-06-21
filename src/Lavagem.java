import java.time.LocalDateTime;

public class Lavagem extends adicionais implements icalcData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -74349741334100088L;
	static int PRECO = 10;
	static int temp_min = 1;
	
	public Lavagem(Servicos serv) {//setando valor de lavagem e colocando tempo minimo para poder sair
		super(PRECO,"Lavagem");
		serv.setDataminima(calcDataMin(serv.getDatainicio(), temp_min));
	}

	 @Override
	 public LocalDateTime calcDataMin(LocalDateTime inicio, int hmin) {
			
			inicio.plusHours(hmin);
			
			return inicio;
		}
}

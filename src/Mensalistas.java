
public class Mensalistas extends Cliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3172203618183472185L;
	private int vagaFixa;
	
	public Mensalistas(String nome, String placa_carro, int vaga) {
		super(nome, "Mensalista", placa_carro, 0.1);
		this.vagaFixa = vaga;
		this.valGasto = 500;
		
	}
	
	@Override
	public void CriarServicos(int id, Vaga vag){//setando sua vaga no servico que esta sendo criado
		
		Servicos servico = new Servicos(this.placa_carro,id);
		
		servico.setVagaid(vagaFixa);
		servico.solicitarVaga(vag);
		
		this.servAberto = servico;
		
	}
	
	@Override
	public boolean FinalizarServico(Vaga vaga) {// deixando sua vaga sempre preenchida
		int cont = this.historico.size();
		
		boolean aux =this.servAberto.solicitarRetirada(vaga, this.desconto);
		
		vaga.preecherVaga(vagaFixa);
		
		if(aux == true) {
			this.servAberto.setValorTotal(this.servAberto.getValAdici());
			
			this.valGasto += this.servAberto.getValorTotal();
			
			this.historico.add(cont, this.servAberto);
			
			this.servAberto = null;			
		}

		return aux;
	}
	

}

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6490219177894242934L;
	protected String nome;
	protected String descricao;
	protected String placa_carro;
	protected List<Servicos> historico;
	protected Servicos servAberto;
	protected double valGasto;
	protected double desconto;
	static LocalDateTime hoje = LocalDateTime.now() ;
	
		public Cliente(String nome, String descricao, String placa_carro, double desconto) {
		this.nome = nome;
		this.descricao = descricao;
		this.placa_carro = placa_carro;
		this.historico = new ArrayList<Servicos>();
		this.valGasto = 0;
		this.desconto = desconto;
	}
		
	public void CriarServicos(int id, Vaga vag) {//criando servico e armazenando em servicos aberto
		
		Servicos servico = new Servicos(this.placa_carro, id);
		servico.solicitarVaga(vag);
		this.servAberto = servico;

	}
	
	public boolean FinalizarServico(Vaga vaga) {//finalizando servico em aberto e retornando se pode fechar
		int cont = this.historico.size();
		boolean aux;
		
		aux = this.servAberto.solicitarRetirada(vaga, this.desconto);
		
		if(aux == true) {
			this.valGasto += this.servAberto.getValorTotal();
		
			this.historico.add(cont, this.servAberto);
		
			this.servAberto = null;
		}
		
		return aux;
	}
	
	public void valGasto30() {//filtrando valor gasto nos ultimos 30 dias
		this.valGasto = this.historico.stream().filter(v -> v.getDatasaida().minusDays(30).isBefore(hoje))
				.mapToDouble(Servicos::getValorTotal).sum();
	}
	
	
	public List<Servicos> FiltroHitorico(LocalDate dt1, LocalDate dt2){//filtrando servicos por datas
		LocalTime agora = LocalTime.now();
		
		if(dt1 != null || dt2 != null) {
			LocalDateTime auxd = LocalDateTime.of(dt1, agora);
			LocalDateTime auxd2 = LocalDateTime.of(dt2, agora);
			List<Servicos> aux =this.historico.stream().filter(c -> c.getDatasaida().isAfter(auxd))
														.filter(d -> d.getDatasaida().isBefore(auxd2)).collect(Collectors.toList());
		return aux;
		}
		List<Servicos> test = this.historico;
		return test;
		
	}
	
	
	public String relatorio(LocalDate dt1, LocalDate dt2) {
		StringBuilder relat = new StringBuilder();
		relat.append("\n=================\n");
		relat.append("Historico\n \n");
		
		for(Servicos serv: FiltroHitorico(dt1, dt2)) {
			relat.append("id:"+ serv.getId()+"\n");
			relat.append("Tempo Parado:"+ serv.getTempParado()+ " min"+"\n");
			relat.append("Valor: R$"+ serv.getValorTotal()+"\n");
			for(adicionais ad : serv.getAdicionais()) {
				relat.append("Adicionais:"+ ad.getDescricao()+"\n");
			}
			relat.append("Entrada:"+ serv.getDatainicio().getHour()+ ":" +serv.getDatainicio().getMinute()+ "\n");
			relat.append("Saida:"+ serv.getDatasaida().getHour()+ ":" +serv.getDatasaida().getMinute()+ "\n");
			relat.append("================================== \n");
		}
		
		return relat.toString();
	
	}

	
	public String toString(LocalDate dt1, LocalDate dt2) {
		return this.relatorio(dt1, dt2);
	}
	
	//----------------------- get set----
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPlaca_carro() {
		return placa_carro;
	}

	public void setPlaca_carro(String placa_carro) {
		this.placa_carro = placa_carro;
	}

	public List<Servicos> getHistorico() {
		return historico;
	}

	public void setHistorico(ArrayList<Servicos> historico) {
		this.historico = historico;
	}

	public double getValGasto() {
		valGasto30();
		return valGasto;
	}

	public void setValGasto(double valGasto) {
		this.valGasto = valGasto;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Servicos getServAberto() {
		return servAberto;
	}
	public void setServAberto(Servicos servAberto) {
		this.servAberto = servAberto;
	}

}

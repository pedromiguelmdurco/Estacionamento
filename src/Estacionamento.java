import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


public class Estacionamento implements Serializable {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 4771401900918610406L;
		Vaga Vagas;
		double valorRecadado[];//Valor Mensal arrecadado 
		Cliente clienteMaisRecadado;// cliente que mais recadou nos ultimos 30 dias
	    ArrayList<Cliente> clientes;
	    double valorMedio;
	    int ultservi;
		
	    
	    public Estacionamento() {
			Vagas = new Vaga();
			this.clientes = new ArrayList<Cliente>();
			this.valorRecadado = new double[11];
			this.valorMedio = 0;
			this.ultservi = 1;
			this.clienteMaisRecadado = new Regulares(null, null);
	    }

	    
	    public Cliente procurarCliente(String placa) {//metodo de percorrer lista de clientes e retornar um
	    	
	    	for(int cont = 1; this.clientes.size() > cont;cont++) {
	 			  if(this.clientes.get(cont).getPlaca_carro().equals(placa)) {
	 				   return clientes.get(cont);
	 			  }
	    	}
	    	
	    	return null;
	    	
	    }
	    
	   public void addCliente(Cliente cliente) {
		   
		   this.clientes.add(cliente);
		   
	   }
	   
	   public void fecharEstacionamento(Estacionamento estacio) throws IOException {//salvando no arquivo dados
		   
		   ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("tudo.txt"));
	       
	        obj.writeObject(estacio);
	        
	        obj.close();
		   
	   }
	   
	   public boolean NovoServicos(Cliente clien) {//converindo se cliente tem algum servico em aberto e criando se n
			if(clien.servAberto == null) {
				clien.CriarServicos(ultservi, Vagas);
				ultservi ++;
				System.out.println("Carro Parado");
				return true;
			}else {
				System.out.println("Não Finalizou outro serviço");
				return false;
			}
			
	   }
	   
	   public void Finalizar(Cliente clien) {//conferindo se cliente tem algum servico em aberto e se pode finalizar
		   
		if(clien.servAberto != null) {
			if(clien.FinalizarServico(Vagas) == false) {
				System.out.println("Não Pode Tirar o Carro no momento está lavando");
			}else {
				calcTotal(clien.getHistorico().get(clien.getHistorico().size()-1).getValorTotal());
				System.out.println("Carro retirado");
				System.out.println("Valor a Pagar: "+ clien.historico.get(clien.getHistorico().size() - 1).getValorTotal());
			}

		}else {
			System.out.println("Não a Serviço aberto");
		}
					 
	   }
	   
	   private void calcTotal(double valor) {//pegando valor arrecadado neste mes e armazenando em posição de vetor que representa cada mês
		   
		   LocalDateTime aux = LocalDateTime.now();
		   
		   this.valorRecadado[aux.getMonthValue() - 1] += valor;
		   
	   }
	   
	   public void clienteMaisRecado() {//passando pela lista de clientes e retornando oq mais gastou nos ultimos 30 dias
		   
		   for(int cont = 1; this.clientes.size() > cont;cont++) {
			   if(this.clientes.get(cont).getValGasto() > this.clienteMaisRecadado.getValGasto()) {
				   
				   this.clienteMaisRecadado = this.clientes.get(cont);
			   }
			   
		   }
		    
	   }
	   
	   public void calcMedia() {//pegando valor de vaga de cada serviço e fazendo media
		  
		   double aux = 0;
		   int aux2 = 0;
		   
		   for(int cont = 1; this.clientes.size() > cont; cont++) {
			   for(int cont2 = 0; this.clientes.get(cont).historico.size() > cont2;cont2++) {
				   
				   aux +=this.clientes.get(cont).getHistorico().get(cont2).getValvaga();
				   aux2++;
			   }
			   
		   }
		   this.valorMedio = aux / aux2;
		   
	   }
	   
	   public String mostrarClie() {//clientes na lista
		   StringBuilder clie = new StringBuilder();
		   
		   clie.append("===========Clientes===============\n");
		   for(Cliente aux : this.clientes) {
			   clie.append("Nome:"+ aux.getNome()+" ||Placa:"+aux.getPlaca_carro() + " Tipo:" + aux.getDescricao() + "\n");
			   clie.append("================================\n");
		   }
		   
		   return clie.toString();
	   }
	   
	   //---------------------------- gets/sets----------------
	   
	   
	   
		public Vaga getVagas() {
			return Vagas;
		}


		public void setVagas(Vaga vagas) {
			Vagas = vagas;
		}

		
		public Cliente getClienteMaisRecadado() {
			clienteMaisRecado();
			return clienteMaisRecadado;
		}


		public void setClienteMaisRecadado(Cliente clienteMaisRecadado) {
			this.clienteMaisRecadado = clienteMaisRecadado;
		}


		public ArrayList<Cliente> getClientes() {
			return clientes;
		}


		public void setClientes(ArrayList<Cliente> clientes) {
			this.clientes = clientes;
		}


		public double getValorMedio() {
			calcMedia();
			return valorMedio;
		}


		public void setValorMedio(double valorMedio) {
			this.valorMedio = valorMedio;
		}


		public int getUltservi() {
			return ultservi;
		}


		public void setUltservi(int ultservi) {
			this.ultservi = ultservi;
		}
		
		public double getValorRecadado() {
			LocalDateTime aux = LocalDateTime.now();
			
			return valorRecadado[aux.getMonthValue() - 1];
		}


		public void setValorRecadado(double valorRecadado) {
			LocalDateTime aux = LocalDateTime.now();
			
			this.valorRecadado[aux.getMonthValue() - 1] = valorRecadado;
		}



}



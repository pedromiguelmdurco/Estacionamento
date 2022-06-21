import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class app {

	
	static String arq = "tudo.txt";
	
	public static String limitaString(String texto){//limitando tamanho da placa para 8 caracteres
		int maximo = 7;
		   return texto.length() <= maximo ? texto : texto.substring(0, maximo);
		}
	
	@SuppressWarnings("resource")
	public static Estacionamento lerArq() {
		
		FileInputStream dados;
		
		try {
			dados = new FileInputStream(arq);
			ObjectInputStream obj = new ObjectInputStream(dados);
			Estacionamento novo = (Estacionamento)obj.readObject();
			
			obj.close();
			dados.close();
			return novo;  
		}
		catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. \n Criando...\n Criado");
            new File("tudo.txt");
            Estacionamento novo = new Estacionamento();
            
            return novo;
        }catch(IOException ex){
            System.out.println("Arquivo impossivel de ler Criando outro");
            Estacionamento novo = new Estacionamento();

            return novo;
        }catch(ClassNotFoundException cex){
            System.out.println("Classe não encontrada: avise ao suporte.");
        }
		
		return null;
		
		
	}

	public static int menu(Scanner teclado){
        System.out.println("XULAMB");
        System.out.println("==========================");
        System.out.println("Selecione uma opção");
        System.out.println("1 - Novo Cliente");
        System.out.println("2 - Parar Carro");
        System.out.println("3 - Retirar Carro");
        System.out.println("4 - Estacionamento");
        System.out.println("5 - Cliente");
        System.out.println("");
        System.out.println("Para Sair Precisa ir no Estacionamento e fechar");
       
        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }
	
	
	public static Cliente menuCriarCliente(Scanner teclado, Vaga vaga){
		Cliente novo = null;
		String nome,placa;
		
        System.out.println("XULAMB");
        System.out.println("==========================");
        System.out.println("Qual Tipo?");
        System.out.println("1 - Regularar");
        System.out.println("2 - Mensalista");
        System.out.println("3 - De_Turno");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        //Coletando dados necessarios para criacao do cliente
        System.out.println("Nome:");
        nome = teclado.nextLine();
        System.out.println("Placa do carro:");
        placa = teclado.nextLine();
        placa = limitaString(placa);
        
        switch (opcao) {
		case 1: novo = new Regulares(nome, placa);
			break;
			
		case 2: 
			if(vaga.conferirFixa() == -1) {//conferindo se tem vaga fixa para poder ser mensalista
				System.out.println("Não Tem como ser Mensalista");
			}else {
				novo = new Mensalistas(nome, placa,vaga.conferirFixa());
			}
			
			break;	
			
		case 3: 
			System.out.println("Qual Turno?");
			System.out.println("1 - Manha(6 as 12)");
			System.out.println("2 - Tarde (13 as 19");
			opcao = teclado.nextInt();
			
			switch (opcao) {//setando turno
			
				case 1: novo = new De_turno(nome, placa, turno.MANHA);
					break;
				
				case 2: novo = new De_turno(nome, placa, turno.TARDE);
					break;
				}
        }
		
        
        System.out.println("Criado");
        return novo;
    }
	
	
	public static void menuParaCarro(Scanner teclado, Estacionamento estac) {
		
		Cliente client = menuProcura(teclado,estac);
		boolean aux;
		
		if(client != null) {//conferindo se cliente existe e se achar cliente n ter nenhum carro parado ja
			aux = estac.NovoServicos(client);
			
			if(aux == true) {
				
				for(adicionais adicional : menuAdicionais(teclado,client.getServAberto())) {
					if(adicional != null) {
						client.servAberto.addAdicionais(adicional);
					}
					
				}
				
			}
			
		}
		
	}
	
	
	public static List<adicionais> menuAdicionais(Scanner teclado, Servicos serv){
		
		int opcao;
        List<adicionais> adic = new LinkedList<>();
		do {
			System.out.println("XULAMB");
	        System.out.println("==========================");
	        System.out.println("Gostaria de algum adicional?");
	        System.out.println("1 - Manobrista");
	        System.out.println("2 - Lavagem");
	        System.out.println("3 - Lavagem Completa");
	        System.out.println("0 - Nao");
	
	        opcao = teclado.nextInt();
	        teclado.nextLine();
	        
	     //recebendo adicionais e adicionando no serviço
	        adicionais novo = null;
	        	
	        switch (opcao) {
	       	
				case 1: novo = new Manobrista();
				break;
				
				case 2: novo = new Lavagem(serv);
				break;
					
				case 3: novo = new Lavagem_Completa(serv);
				break;
					
			}
	        adic.add(novo);
	     
		}while(opcao != 0);
        
        return adic;
    }
	
	
	public static void menuRetirada(Scanner teclado, Estacionamento estac){
		
		Cliente client = menuProcura(teclado,estac);
        
        if(client != null) {//conferindo se a cliente
			estac.Finalizar(client);
			
		}
		
    }
	
	
	public static void menuEstacionamento(Scanner teclado, Estacionamento estac) {
		int opcao;
		
		System.out.println("XULAMB");
        System.out.println("==========================");
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Valor Arrecadado Mensal");
        System.out.println("2 - Valor Médio de uso de vaga");
        System.out.println("3 - Cliente que mais arrecadou ult 30 dias");
        System.out.println("4 - Fechar Estacionamento");
        System.out.println("5 - Mostrar Clientes");
        System.out.println("6 - Voltar");
        opcao = teclado.nextInt();
        teclado.nextLine();
        
        switch (opcao) {
		case 1: System.out.println("Valor: R$" + estac.getValorRecadado());
			break;
			
		case 2: System.out.println("Média vagas: R$" + estac.getValorMedio());
			break;
			
		case 3: System.out.println("Cliente: " + estac.getClienteMaisRecadado().getNome() + 
				" | Placa: " + estac.getClienteMaisRecadado().getPlaca_carro());
				System.out.println("Valor: R$" + estac.getClienteMaisRecadado().getValGasto());
			break;
			
		case 4: try {//pegando dados salvando em arquivo e finalizando programa
				estac.fecharEstacionamento(estac);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println("Até amanhã :D");
			System.exit(0);
			break;
		
		case 5:
			System.out.println(estac.mostrarClie());
			break;
			
		}
        
        
	}
	
	
	public static void menuCliente(Scanner teclado, Estacionamento estac) {
		
		int dt1,mes1,ano1,dt2,mes2,ano2,opcao;
		
		Cliente client = menuProcura(teclado,estac);
		
		System.out.println("XULAMB");
	    System.out.println("==========================");
		System.out.println("1 -Olhar Historico com filtro");
		System.out.println("2 -Olhar Historico sem filtro");
		opcao = teclado.nextInt();
        teclado.nextLine();
        
        switch (opcao) {
		case 1: System.out.println("Filtro de datas:");
				System.out.println("De");
				System.out.println("Dia: ");
				dt1 = teclado.nextInt();
				System.out.println("Mes: ");
				mes1 = teclado.nextInt();
				System.out.println("Ano: ");
				ano1 = teclado.nextInt();
				
				System.out.println("Até");
				System.out.println("Dia: ");
				dt2 = teclado.nextInt();
				System.out.println("Mes: ");
				mes2 = teclado.nextInt();
				System.out.println("Ano: ");
				ano2 = teclado.nextInt();
				
				LocalDate data1 = LocalDate.of(ano1, mes1, dt1);
				LocalDate data2 = LocalDate.of(ano2, mes2, dt2);
				
				System.out.println(client.relatorio(data1, data2));
			break;
			
		case 2: System.out.println(client.toString(null, null));
		 	break;
		}
		
	}
	
	
	public static Cliente menuProcura(Scanner teclado, Estacionamento estac) {
		String placa;
		
		 System.out.println("XULAMB");
	     System.out.println("==========================");
	     System.out.println("Informe sua Placa?");
	     placa = teclado.next();
	     placa = limitaString(placa);
	     //procurando cliente atravez da placa
	     if(estac.procurarCliente(placa) == null) {
			System.out.println("Cliente não encontrado");
			return null;
		}else if(estac.procurarCliente(placa).getPlaca_carro().equals(placa)){
			return estac.procurarCliente(placa);
		}
	
		return null;
	}
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in, "UTF-8");
		
		Estacionamento estacionamento = new Estacionamento();
		int opcao;
		estacionamento = lerArq();

		do {
			
	    	opcao = menu(teclado);
	    	switch (opcao) {
			case 1:Cliente aux = menuCriarCliente(teclado, estacionamento.getVagas());
				if(aux != null){
				
					estacionamento.addCliente(aux);
				}
				break;
				
			case 2:menuParaCarro(teclado, estacionamento);
				break;
				
			case 3:menuRetirada(teclado, estacionamento);
				break;
				
			case 4:menuEstacionamento(teclado, estacionamento);
				break;
				
			case 5:menuCliente(teclado, estacionamento);
				break;
			
				
			}
	    
	    }while(opcao != 0);

	}
		
}



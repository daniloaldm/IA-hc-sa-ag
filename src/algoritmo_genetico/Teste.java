package algoritmo_genetico;

public class Teste {

	public static void main(String[] args) {
		
		int [][] inicial ={{0, 0, 0, 0, 0, 0, 0 ,0},
						   {0, 0, 0, 0, 0, 0, 0 ,0},
						   {0, 0, 0, 0, 0, 0, 0 ,0},
						   {0, 0, 0, 0, 0, 0, 0 ,0},
						   {0, 0, 0, 0, 0, 0, 0 ,0},
						   {0, 0, 0, 0, 0, 0, 0 ,0},
						   {0, 0, 0, 0, 0, 0, 0 ,0},
						   {0, 0, 0, 0, 0, 0, 0 ,0} };
		
		No estadoInicial = new No(inicial);
		
		// int cont=0;
		
		// for (int i = 0; i < 100; i++) {
		// 	Genetic algoritmo = new Genetic(estadoInicial, 40, 20000);
		// 	if(estadoInicial.ataquesTabuleiro(algoritmo.getIndividuo().getEstado()) == 0) {
		// 		cont++;
		// 		System.out.println("Achou ! Total: "+cont+" de "+(i+1)+" tentativas.");
		// 	}else {
		// 		System.out.println("Falhou !");
		// 	}
		// }
		
		// System.out.println(cont+"/100");
		
		int cont = 0;
		while(cont<10){
			Genetic algoritmo = new Genetic(estadoInicial, 60, 2000);
			algoritmo.getIndividuo().mostraEstado();
			System.out.println("Ataques: "+estadoInicial.ataquesTabuleiro(algoritmo.getIndividuo().getEstado()));
			cont++;	
		}
		// Genetic algoritmo = new Genetic(estadoInicial, 60, 2000);
		// algoritmo.getIndividuo().mostraEstado();
		// System.out.println("Ataques: "+estadoInicial.ataquesTabuleiro(algoritmo.getIndividuo().getEstado()));
		
	}

}

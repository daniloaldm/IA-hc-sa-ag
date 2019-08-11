package recozimento_simulado;

public class Teste {

	public static void main(String[] args) {
		
		int[][] estado = { { 1, 0, 0, 1, 0, 0, 0, 0 }, 
						   { 0, 0, 0, 0, 0, 0, 0, 0 },
						   { 0, 0, 1, 0, 0, 1, 0, 0 },
						   { 0, 1, 0, 0, 0, 0, 0, 0 },
				 		   { 0, 0, 0, 0, 0, 0, 0, 1 },
				   		   { 0, 0, 0, 0, 0, 0, 0, 0 },
						   { 0, 0, 0, 0, 1, 0, 1, 0 },
				 		   { 0, 0, 0, 0, 0, 0, 0, 0 } };

		No no = new No(estado);

		/*
		APLICANDO O ALGORITMO COM O ESTADO INICIAL ACIMA
		
		Estado Inicial/ 
		Numero de vezes que irei diminuir a temperatura/
		Numero de vezes que vou tentar encontrar um resultado melhor do que eu encontrei/
		Quantos resultados melhores(ou nao) eu devo saltar/
		Velocidade que a temperatura vai diminuir 
		
		*/
		
		SimulatedAnnealing algoritm = new SimulatedAnnealing(no, 2000, 2000, 2, 0.9);
				
		//MOSTRA O MELHOR ESTADO QUE O ALGORITMO ACHOU
		algoritm.getMelhorEstado().mostraEstado();
				
		//MOSTRO O NUMERO DE ATAQUES QUE ELE OBTEVE
		System.out.println("Ataques: " + algoritm.getMelhorEstado().ataquesTabuleiro(algoritm.getMelhorEstado().getEstado()));
		
	}

}

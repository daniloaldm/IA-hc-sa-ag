package algoritmo_subida_da_encosta;

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
		
		int count = 0;
		int sucesso = 0;
		while (count != 10) {
			No no = new No(estado);

			// APLICANDO O ALGORITMO COM O ESTADO INICIAL ACIMA
			HillClimbing algoritm = new HillClimbing(no);

			// MOSTRA O MELHOR ESTADO QUE O ALGORITMO ACHOU
			algoritm.getMelhorEstado().mostraEstado();

			// MOSTRO O NUMERO DE ATAQUES QUE ELE OBTEVE
			System.out.println(
					"Ataques: " + algoritm.getMelhorEstado().ataquesTabuleiro(algoritm.getMelhorEstado().getEstado()));
			System.out.println("====================================================================================");
			if(algoritm.getMelhorEstado().ataquesTabuleiro(algoritm.getMelhorEstado().getEstado())==0){
				sucesso++;
			}
			count++;
		}

		System.out.println("Taxa de acerto de " + (sucesso/((float)count))*100 + "%");
	}

}

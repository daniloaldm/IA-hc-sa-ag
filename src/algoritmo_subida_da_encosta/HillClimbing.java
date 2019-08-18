package algoritmo_subida_da_encosta;

public class HillClimbing {

	private No melhorEstado;

	// CONSTRUTOR DA CLASSE QUE RECEBE UM NO (ESTADO INICIAL)
	HillClimbing(No no) {

		long inicio = System.currentTimeMillis();
		for (int i = 0; i < 3000; i++) {

			// INSTANCIO UM NÓ vizinho QUE RECEBERÁ A REFERÊNCIA DO MELHOR VIZINHO DE no
			No vizinho = new No(no.getSucessorMax());

			// SE O NÓ vizinho FOR PIOR QUE O no, ENTÃO JÁ ESTOU NO MELHOR ESTADO QUE É O no
			// OU ENTÃO SE O NÓ ATUAL FOR O OTIMO (0 ATAQUES)
			if (vizinho.getCusto() > no.getCusto() || no.getCusto() == 0) {
				melhorEstado = no;
				break;
			}

			// SE NAO, EU MIGRO PRO vizinho POIS ELE Ó MELHOR
			no = vizinho;

			// ATUALIZO O ATUAL MELHOR ESTADO
			this.melhorEstado = no;

		}
		long fim = System.currentTimeMillis();
		System.out.println("Tempo: " + (fim - inicio) / 1000.0 + " segundos");
	}

	public No getMelhorEstado() {
		return this.melhorEstado;
	}

}

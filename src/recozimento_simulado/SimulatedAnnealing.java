package recozimento_simulado;

import java.util.Random;

public class SimulatedAnnealing {

	private No melhorEstado;

	/*
	 * 
	 * Estado Inicial/ 
	 * Numero de vezes que irei diminuir a temperatura/
	 * Numero de vezes que vou tentar encontrar um resultado melhor do que eu encontrei/
	 * Quantos resultados melhores(ou nao) eu devo saltar/
	 * Velocidade que a temperatura vai diminuir/
	 * 
	 */

	SimulatedAnnealing(No s0, int M, int P, int L, double alpha) {
		long inicio = System.currentTimeMillis();
		Random gerador = new Random();

		double T = 5000;
		int nSucesso;
		int delta;

		// For externo para diminuir temperatura
		for (int j = 1;; j++) {
			// System.out.println("Ataques Atual: " + s0.getCusto());

			nSucesso = 0;

			 
			for (int i = 1;; i++) {

				No Si = new No(s0.sucessorAleatorio());

				// System.out.println("Ataques Sucessor: " + Si.getCusto());

				delta = Si.getCusto() - s0.getCusto();

				if (delta <= 0 || Math.exp(-delta / T) > gerador.nextDouble()) {
					s0 = Si;
					nSucesso++;
				}

				if (nSucesso >= L || i > P) {
					break;
				}
				// System.out.println("Choacolhadas: " + i);
			}

			T = alpha * T;

			if (nSucesso == 0 || j > M) {
				System.out.println("Temperatura: " + T);
				this.melhorEstado = s0;
				break;
			}

		}
		long fim = System.currentTimeMillis();
		System.out.println("Tempo: " + (fim - inicio) / 1000.0 + " segundos");
	}

	// GET melhorEstado
	public No getMelhorEstado() {
		return this.melhorEstado;
	}

}

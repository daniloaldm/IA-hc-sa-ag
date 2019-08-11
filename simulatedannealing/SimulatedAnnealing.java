package recozimento_simulado;

import java.util.Random;

public class SimulatedAnnealing {
	
	private No melhorEstado;
	
	//CONSTRUTOR DA CLASSE QUE RECEBE UM NO (ESTADO INICIAL) E UM INTEIRO (TEMPERATURA)
	SimulatedAnnealing(No s0, int M, int P, int L, double alpha){
		
		Random gerador= new Random();
		
		double T = 5000;
		int nSucesso;
		int delta;
		
		for (int j = 1; ; j++) {
			
			System.out.println("Ataques Atual: "+s0.getCusto());
			
			nSucesso = 0;		
			
			for (int i = 1; ; i++) {
				
				No Si = new No(s0.sucessorAleatorio());
				
				System.out.println("Ataques Sucessor: "+Si.getCusto());
				
				delta = Si.getCusto() - s0.getCusto();
				
				if(delta <= 0 || Math.exp(-delta/T) > gerador.nextDouble()) {				
					s0 = Si;
					nSucesso++;
				}
				
				if(nSucesso >= L || i > P) {
					break;
				}
				
			}
			
			T = alpha * T;
			
			if(nSucesso == 0 || j > M) {
				System.out.println("Temperatura: "+T);
				this.melhorEstado = s0;
				break;
			}
			
		}
		
	}
	
	//GET melhorEstado
	public No getMelhorEstado() {
		return this.melhorEstado;
	}

}

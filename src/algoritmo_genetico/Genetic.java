package algoritmo_genetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genetic {
	
	private No melhorIndividuo;

	//CONTRUTOR DA CLASSE
	Genetic(No estadoInicial, int numIndividuo, int geracoes){
		long inicio = System.currentTimeMillis();
		//GERO UMA POPULACAO
		List<No> populacao = geraPopulacao(numIndividuo, estadoInicial.getEstado());
		
		//A POPULACAO SERA FORMADA POR
		double elitismo = 0.1;
		double reproducao = 0.8;
		double mutacao = 0.1;
		
		//LACO QUE RODA AS GERACOES
		for (int g = 0; g < geracoes; g++) {
			
			//VERIFICO SE TEM ALGUM INDIVIDUO BOM O SUFICIENTE PARA PARAR
			if(melhorIndividuo(populacao).getCusto() == 0) {
				this.melhorIndividuo = melhorIndividuo(populacao);
				break;
			}
			
			//NOVA POPULACAO A SER CRIADA
			List<No> novaPopulacao = new ArrayList<No>();
			
			//POR ELITISMO
			for (int i = 0; i < (numIndividuo*elitismo); i++) {
				
				//PEGANDO O MELHOR INDIVIDUO
				No melhor = melhorIndividuo(populacao);
				
				//ADICIONO ESSE INDIVIDUO A NOVA POPULACAO
				novaPopulacao.add(melhor);
				
			}
			
			//POR REPRODUCAO
			for(int i = 0; i < (numIndividuo*reproducao); i++) {
				
				//CASAL SELECIONADO POR RODEIO DA POPULACAO
				No pai = rodeio(populacao);
				No mae = rodeio(populacao);
				
				//GERANDO O FILHO
				No filho = new No(reproducao(pai, mae));
				
				//ADICIONO O FILHO GERADO A NOVA POPULACAO
				novaPopulacao.add(filho);
		
			}
			
			//POR MUTACAO
			for (int i = 0; i < (numIndividuo*mutacao); i++) {
				Random gerador = new Random();
				
				//INSTANCIO UM NO QUE RECEBE A MUTACAO DO NO DA POPULACAO SORTEADO POR RODEIO
				No mutante = new No(mutacao(rodeio(populacao)));
				
				//ADICIONO O MUTANTE NA NOVA POPULACAO
				novaPopulacao.add(mutante);
				
			}
			
			//NOVA POPULACAO PASSA A SER OS INDIVIDUOS QUE FORAM GERADOS
			populacao = novaPopulacao;
			
			//MELHOR INDIVIDUO GERADO ATE AGORA
			this.melhorIndividuo = melhorIndividuo(populacao);
			
		}
		long fim = System.currentTimeMillis();
		System.out.println("Tempo: " + (fim - inicio) / 1000.0 + " segundos");
	}
	
	//METODOS DA CLASSE------------------------------------------------------------------
	
	//PEGA O ATRIBUTO melhorIndividuo DA CLASSE
	public No getIndividuo() {
		return this.melhorIndividuo;
	}
	
	//GERA A POPULACAO
	public List<No> geraPopulacao(int numIndividuo, int[][] estadoInicial){
		
		Random gerador =new Random();
		
		List<No> populacao = new ArrayList<No>();
		
		for (int i = 0; i < numIndividuo; i++) {
			//COPIANDO O ESTADO QUE VEIO DO PARAMETRO PARA NAO PERDE-LO
			int[][] estadoCopia = new int[estadoInicial.length][estadoInicial.length];
			
			for (int l = 0; l < estadoInicial.length; l++) {
				for (int c = 0; c < estadoInicial.length; c++) {
					estadoCopia[l][c] = estadoInicial[l][c];
				}
			}
			
			//GERO UM INDIVIDUO ALEATORIO A PARTIR DO ESTADO INICIAL(matriz de 0)
			for (int coluna = 0; coluna < estadoInicial.length; coluna++) {
				estadoCopia[gerador.nextInt(estadoCopia.length)][coluna] = 1;
			}
			
			//ADICIONO ELE NA POPULACAO
			No aux = new No(estadoCopia);
			populacao.add(populacao.size(), aux);
			
		}
		
//		System.out.println("Verificando o tamanho da populacao gerada: "+populacao.size());
		
		//RETORNO A POPULACAO PREENCHIDA COM A QUANTIDADE DE INDIVIDUO SOLICITADA
		return populacao;
		
	}
	
	//ESCOLHE O CASAL ALEATORIAMENTE NO MEIO DA POPULACAO
	public No rodeio(List<No> populacao) {
		
		//RANDOMIZARA A ESCOLHA
		Random gerador = new Random();
		
		//GUARDARA TRES NÓS SORTEADOS
		List<No> aleatorios = new ArrayList<No>(3);
		
		//GUARDARA O MELHOR NÓ DOS TRES SORTEADOS
		No no;
		
		//ESCOLHENDO OS TRES NOS ALEATORIOS
		for (int i = 0; i < 3; i++) {
			aleatorios.add(populacao.get(gerador.nextInt(populacao.size())));
		}
		
		//MELHOR DOS TRES ESCOLHIDOS
		no = melhorIndividuo(aleatorios);
		
		return no;
	}
	
	//INDICA QUANTOS ATAQUES O ESTADO TEM (CUSTO)
	//RETORNA O CUSTO (NUMERO DE ATAQUES DO TABULEIRO)
	public int fnAdapta(No no) {
		int custo = no.getCusto();
		return custo;
	}
	
	//ALTERA UM ESTADO
	//FAZ A MUTACAO DE UM ESTADO (INDIVIDUO)
	public int[][] mutacao(No no) {
		
		return no.sucessorAleatorio();
		
	}
	
	//RECEBE DOIS INDIVIDUOS E GERA UM NOVO COM PARTES DE UM E DE OUTRO
	//REPRODUCAO
	public int[][] reproducao(No pai, No mae){
		
		//GUARDARA UM VALOR QUE REPRESENTARA A DIVISAO DA MATRIZ QUE SERA USADA PARA O PAI E PARA A MAE
		int n = (int)(1 + Math.random() * (pai.getEstado().length - 1));
		
		//GUARDARA A MATRIZ DO NO FILHO
		int[][] filho = new int[pai.getEstado().length][pai.getEstado().length];
		
		//COPIANDO A PARTE DO PAI
		for (int c = 0; c < n; c++) {
			for (int l = 0; l < filho.length; l++) {
				filho[l][c] = pai.getEstado()[l][c];
			}
		}
		
		//COPIANDO A PARTE DA MÃE
		for (int c = n; c < filho.length; c++) {
			for (int l = 0; l < filho.length; l++) {
				filho[l][c] = mae.getEstado()[l][c];
			}
		}
		
		//RETORNO O FILHO
		return filho;
		
	}
	
	//ANALISO O MENOR CUSTO DE UMA POPULACAO E RETORNO O INDIVIDUO
	public No melhorIndividuo(List<No> populacao) {
		
		Random gerador = new Random();
		
		//NO AUXILIAR PARA EFETUARMOS A TROCA
		No aux;
		
		//GUARDARA OS NOS COM MELHORES CUSTOS
		List<No> melhores = new ArrayList<No>();
		
		for (int i = 0; i < populacao.size(); i++) {
			
			for (int j = i; j < populacao.size(); j++) {
				
				if(populacao.get(i).getCusto() > populacao.get(j).getCusto()) {
					
					//GUARDO A REFERENCIA PARA NAO PERDER O VALOR
					aux = populacao.get(i);
					
					//COLOCO O MAIOR NO LUGAR DO MENOR
					populacao.set(i,populacao.get(j));
					
					//COLOCO O MENOR NO LUGAR DO MAIOR
					populacao.set(j, aux);
					
				}
				
			}
			
		}
		
		for (int i = 0; i < populacao.size(); i++) {
			
			if(populacao.get(i).getCusto() == populacao.get(0).getCusto()) {
				melhores.add(populacao.get(i));
			}
			
		}
		
		return melhores.get(gerador.nextInt(melhores.size()));
		
	}
	
}

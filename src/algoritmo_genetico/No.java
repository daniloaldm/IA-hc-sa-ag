package algoritmo_genetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class No {
	
	//ESTADO DO NÓ
	private int[][] estado;
	
	//CUSTO DO NÓ
	private int custo;
	
	//CONSTRUTOR DA CLASSE
	No(int[][] estadoDoNo){
		this.estado = estadoDoNo;
		custo();
		sucessorAleatorio();
	}
	
	//RETORNA O ATRIBUTO ESTADO DA CLASSE
	public int[][] getEstado(){
		return estado;	
	}
	
	//RETORNA O ATRIBUTO CUSTO DA CLASSE
	public int getCusto(){	
		return custo;	
	}
	
	//SETTA O CUSTO NO ATRIBUTO CUSTO DA CLASSE
	public void setCusto(int custo){	
		this.custo = custo;	
	}
	
	//RETORNA O ESTADO DO NO EM FORMA DE MATRIZ
	public void mostraEstado() {
		
		for (int i = 0; i < estado.length; i++) {
			
			for (int j = 0; j < estado.length; j++) {
				
				System.out.print(this.estado[i][j]);
				
			}
			
			System.out.println();
			
		}
		
		System.out.println();
		
	}
	
	//CALCULO O CUSTO DO ESTADO ATUAL (NUMERO DE ATAQUES)
	private void custo() {
		
		//FIXO AS COLUNAS PARA PROCURAR A RAINHA CONTIDA NA COLUNA
		for(int coluna = 0; coluna < this.estado.length; coluna++) {
			for (int linha = 0; linha < this.estado.length; linha++) {
				if(this.estado[linha][coluna] == 1) {
					//ACHADO A RAINHA, CALCULO SEMPRE OS ATAQUES DA POSICAO DELA PARA A DIREITA DO TABULEIRO
					this.custo = this.custo + ataques(this.estado, linha, coluna);
				}
			}
		}
		
	}
	
	//PROCURA UM SUCESSOR ALEATORIO PARA ESSE ESTADO (ESTADO COM MAIOR E MENOR NUMERO DE ATAQUES)
	public int[][] sucessorAleatorio() {
		
		//ARRAY DE SUCESSORES DO ESTADO ATUAL
		List<int[][]> sucessores = new ArrayList<int[][]>();
		
		//GUARDA A COORDENADA DA RAINHA DA ATUAL COLUNA
		int linRainha = 0;
		int colRainha = 0;
		
		//FIXO AS COLUNAS PARA PROCURAR LUGARES ONDE NÃO POSSUEM RAINHAS
		for(int coluna = 0; coluna < this.estado.length; coluna++) {
			
			//PROCURO A RAINHA DESSA COLUNA E SALVO SUA POSICAO
			for (int linha = 0; linha < this.estado.length; linha++) {
				if(this.estado[linha][coluna] == 1) {
					linRainha = linha;
					colRainha = coluna;
					//ZERO ELA PARA COMECAR A FAZER OS SUCESSORES ABAIXO
					this.estado[linha][coluna] = 0;
				}
			}
			
			//MONTANDO OS SUCESSORES COM AS ALTERACOES DESSA COLUNA
			for (int linha = 0; linha < this.estado.length; linha++) {
				if(linha != linRainha || coluna != colRainha) {
					this.estado[linha][coluna] = 1;
					
					int[][] copia = new int[estado.length][estado.length];
					for (int i = 0; i < this.estado.length; i++) {
						for (int j = 0; j < this.estado.length; j++) {	
							copia[i][j] = this.estado[i][j];
						}			
					}	
					
					sucessores.add(sucessores.size(), copia);
					
					this.estado[linha][coluna] = 0;
				}
			}
			
			//VOLTO A RAINHA PARA ONDE ESTAVA
			this.estado[linRainha][colRainha] = 1;
			
		}
		
		Random gerador = new Random();
		
		int[][] sucessorAleatorio = sucessores.get(gerador.nextInt(sucessores.size()));
		
		//ATRIBUTO SUCESSOR RECEBE A REFERENCIA DE UM SUCESSOR ALEATORIO
		return sucessorAleatorio;
		
	}
	
	//-------MÉTODOS AUXILIARES----------------------------------------------------------------------------------------------
	
	//MÉTODO QUE RECEBE UMA MATRIZ E UMA COORDENADA DESSA MATRIZ E RETORNA O NUMERO DE ATAQUES NA LINHA, COLUNA, DIAGONAL PRINCIPAL E SECUNDARIA
	//MATRIZ DEVE SER QUADRADA nxn
	public int ataques(int[][] matriz, int linha, int coluna) {
		
		//GUARDARÁ O NÚMERO DE ATAQUES
		int ataque = 0;
		
		//VERIFICO NA LINHA (só verifico da coluna em diante, evitando que calcule dois ataques de um mesmo par de rainhas )
		for (int i = coluna; i < matriz.length; i++) {
			if(matriz[linha][coluna] == matriz[linha][i]) {
				ataque++;
			}
		}
		
		//VERIFICO NA COLUNA
		for (int i = 0; i < matriz.length; i++) {
			if(matriz[linha][coluna] == matriz[i][coluna]) {
				ataque++;
			}
		}
		
		//COPIO OS VALORES DA LINHA E DA COLUNA PARA NAO PERDER OS ORIGINAIS NAS INCREMENTACOES FEITAS ABAIXO
		int coplin = linha;
		int copcol = coluna;
		
		//VERIFICO NA DIAGONA PRINCIPAL/BAIXO
		while(coplin <= matriz.length-1 && copcol <= matriz.length-1) {
			if(matriz[linha][coluna] == matriz[coplin][copcol]) {
				ataque++;
			}
			coplin++;
			copcol++;
		}
		
		//RESETO AS COPIAS
		coplin = linha;
		copcol = coluna;
		
		//VERIFICO A DIAGONA SECUNDÁRIA/CIMA
		while(coplin >= 0 && copcol <= matriz.length-1) {
			if(matriz[linha][coluna] == matriz[coplin][copcol]) {
				ataque++;
			}
			coplin--;
			copcol++;
		}
		
		//DECREMENTO 4, POIS VERIFICO 4 LUGARES (LINHA, COLUNA, DIAGONAL PRINCIPAL BAIXO, DIAGONAL SECUNDARIA CIMA) E EM TODAS A INCREMENTACAO CONTA A PROPRIA RAINHA
		ataque = ataque - 4;
		
		//RETORNO O NUMERO DE ATAQUES
		return ataque;
		
	}
	
	//CALCULO O NUMERO DE ATAQUE DE UM TABULEIRO
	public int ataquesTabuleiro(int[][] matriz) {
		
		//NUMERO DE ATAQUES DO TABULEIRO
		int ataqueTab = 0;
		
		//FIXO AS COLUNAS PARA PROCURAR A RAINHA CONTIDA NA COLUNA
		for(int coluna = 0; coluna < matriz.length; coluna++) {
			for (int linha = 0; linha < matriz.length; linha++) {
				if(matriz[linha][coluna] == 1) {
					//ACHADO A RAINHA, CALCULO SEMPRE OS ATAQUES DA POSICAO DELA PARA A DIREITA DO TABULEIRO
					ataqueTab = ataqueTab + ataques(matriz, linha, coluna);
				}
			}
		}
		
		return ataqueTab;
		
	}
	
	public List<No> sucessor() {
		
		//ARRAY DE SUCESSORES DO ESTADO ATUAL
		List<int[][]> sucessoresMatriz = new ArrayList<int[][]>();
		
		//ARRAY SUCESSORES EM FORMA DE NO
		List<No> sucessores = new ArrayList<No>();
		
		//GUARDA A COORDENADA DA RAINHA DA ATUAL COLUNA
		int linRainha = 0;
		int colRainha = 0;
		
		//FIXO AS COLUNAS PARA PROCURAR LUGARES ONDE NÃO POSSUEM RAINHAS
		for(int coluna = 0; coluna < this.estado.length; coluna++) {
			
			//PROCURO A RAINHA DESSA COLUNA E SALVO SUA POSICAO
			for (int linha = 0; linha < this.estado.length; linha++) {
				if(this.estado[linha][coluna] == 1) {
					linRainha = linha;
					colRainha = coluna;
					//ZERO ELA PARA COMECAR A FAZER OS SUCESSORES ABAIXO
					this.estado[linha][coluna] = 0;
				}
			}
			
			//MONTANDO OS SUCESSORES COM AS ALTERACOES DESSA COLUNA
			for (int linha = 0; linha < this.estado.length; linha++) {
				if(linha != linRainha || coluna != colRainha) {
					this.estado[linha][coluna] = 1;
					
					int[][] copia = new int[estado.length][estado.length];
					for (int i = 0; i < this.estado.length; i++) {
						for (int j = 0; j < this.estado.length; j++) {	
							copia[i][j] = this.estado[i][j];
						}			
					}	
					
					sucessoresMatriz.add(sucessoresMatriz.size(), copia);
					
					this.estado[linha][coluna] = 0;
				}
			}
			
			//VOLTO A RAINHA PARA ONDE ESTAVA
			this.estado[linRainha][colRainha] = 1;
			
		}
		
		//INSTANCIANDO OS NOS
		for (int i = 0; i < sucessoresMatriz.size(); i++) {
			No no = new No(sucessoresMatriz.get(i));
			sucessores.add(no);
		}
		
		return sucessores;
		
	}
	
	//MÉTODO QUE ORDENA UMA LISTA DE FORMA DECRESCENTE PELO CUSTO (PARA QUE QUANDO SEJA INSERIDA NA BORDA, FIQUE DE FORMA CRESCENTE)
	public List<int[][]> ordenaCrescenteAtaque(List<int[][]> lista){
		
		//NO AUXILIAR PARA EFETUARMOS A TROCA
		int[][] aux;
		
		for (int i = 0; i < lista.size(); i++) {
			
			for (int j = i; j < lista.size(); j++) {
				
				if(ataquesTabuleiro(lista.get(i)) > ataquesTabuleiro(lista.get(j))) {
					
					//GUARDO A REFERENCIA PARA NAO PERDER O VALOR
					aux = lista.get(i);
					
					//COLOCO O MAIOR NO LUGAR DO MENOR
					lista.set(i,lista.get(j));
					
					//COLOCO O MENOR NO LUGAR DO MAIOR
					lista.set(j, aux);
					
				}
				
			}
			
		}
		
		//RETORNO A LISTA ORDENADA DESCRESCENTEMENTE
		return lista;
		
	}
	
}

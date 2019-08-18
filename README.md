**![](https://lh4.googleusercontent.com/9HfMMaJKhJFAzHk69xospiwYgBNNLCYnoAyjT2Hfpowl0FIomjjvduzXuQqg6Os7mTbMCFumE63w_I9qn7mtrnm_N0vLOpo9AS_ls9dIBRWhqQrPAvubNiiKfXfXqZuqBfJImCAh)**


# Inteligência Artificial - Agente baseado no algoritmo Subida da Encosta (Hill Climbing), Recozimento Simulado (Simulated Annealing, SA) e Algoritmos Genéticos para resolver o problema das 8 rainhas.

Danilo Alexandrino de Miranda  
Antônio Carlos S. F. Júnior


#  1. Introdução

*O problema escolhido para a realização deste trabalho foi o das 8 rainhas, que se resume, basicamente, em distribuir 8 rainhas em um tabuleiro de xadrez de forma que não seja possível um ataque entre elas. O desafio é resolver utilizando os algoritmos: Subida da Encosta (Hill Climbing, SA), Recozimento Simulado (Simulated Annealing, SA) e Algoritmos Genéticos. No tópico 2, descrevemos o problema detalhadamente, fazendo uma analogia de como pensamos para resolver na implementação. Logo após, no tópico 3 descreveremos um pouco de como os algoritmos funcionam e como os implementamos, já no tópico 4 mostraremos os resultados dos testes dos algoritmos com o problema, e finalmente no tópico 5, concluiremos o relatório analisando os resultados obtidos pelos algoritmos.*

## Problema das Oito Rainhas

*O problema das oito rainhas é um problema matemático de dispor oito rainhas em um tabuleiro de xadrez de dimensão 8x8, de forma que nenhuma delas seja atacada por outra. Para tanto, é necessário que duas rainhas quaisquer não estejam numa mesma linha, coluna, ou diagonal (Figura 1).*

![](https://lh5.googleusercontent.com/Ke1QxvLNN854eWoVnS-NKDsmPsNgjaHUFAj_kaq2eOHFMYMvyOCohfOiSCiEDeJtwxH_LDCDPclolIBNWwQgANqtrLnAv-HOtIQ1zFoTPn3PZU4uAtCmX9tDWC-1CKoFsxiXCb8o)

***Figura 1. Tipos de ataque***

Dito isso, temos que:
-   **Estados:** qualquer disposição de 0 a 8 rainhas no tabuleiro é um estado.
    
-   **Estado Inicial:** nenhuma rainha no tabuleiro ou 8 rainhas distribuídas de uma forma determinada.
    
-   **Função Sucessora:** retorna estados possíveis dos quais respeitam as regras do problema ( posições das quais podem colocar uma rainha ).
    
-   **Teste Objetivo:** 8 rainhas estão no tabuleiro sem que seja possível uma atacar a outra.

Desta forma, o raciocínio será partir do estado inicial, acrescentar as rainhas, seguindo as regras do problema, até que as 8 estejam no tabuleiro (Figura 2).

![](https://lh6.googleusercontent.com/IN1qhP4qb6L-_TbsBJmbBy_6azSaBsgYYAgS0x_Y7msOxi9Kal2N1Ft8ScP-YULk0gyjr6E--zSnHuiucJBz5Jv6pI_3vOCePfrv-v5TjfQnG41Nf4opTlmsWHZRAhSQalMgQfiG)

***Figura 2.***

# 2. Algoritmos

Os algoritmos foram implementados usando a linguagem Java e tem as seguintes características fundamentais para o entendimento de como implementamos.

## 2.1. Subida da Encosta (Hill Climbing)

1 - Um nó possui um estado, um custo (número de ataques do estado desse nó) e um sucessor máximo (que recebe a referência do melhor vizinho desse nó).

2 - A Subida da Encosta recebe um estado inicial, que é um nó matriz 8x8 com as rainhas distribuídas, e com isso mostra o melhor estado que o algoritmo encontrou. Com a informação de melhor estado, também é possível, usando o método “ataquesTabuleiro” (classe No) recebendo como parâmetro esse melhor estado, fornecer quantos ataques aquele estado obteve.

Há duas variações do método: a **Subida de Encosta  simples** e a **Subida de Encosta  pela trilha mais íngreme**. Segue uma breve descrição da diferença dos dois:

-   **Subida de encosta simples:** vai examinando os sucessores do estado atual e segue para o primeiro estado que for melhor que o atual.
    
-   **Subida de encosta pela trilha mais íngreme:** Examina todos os sucessores do estado atual e escolhe entre estes sucessores qual é o que está mais próximo da solução.
    

No método implementado, funciona basicamente da seguinte forma, instanciamos um nó vizinho que recebe a referência do melhor vizinho do nó ou seja, usamos a função “sucessorMax” da classe nó que nos retorna o melhor vizinho, depois avaliamos se o nó vizinho é pior que meu nó atual, se for, então já estamos no melhor estado, se não aquele nó vizinho se torna o nó atual e repetimos todo o processo até que o nó vizinho seja pior que o meu nó atual, ou meu nó atual tenha o custo igual a zero.

Vale observar que o algoritmo de busca de subida de encosta é a técnica de busca local mais básica. Em cada passo, o nó atual é substituído pelo melhor vizinho, esse é o vizinho com o valor mais alto, porém, se fosse usada uma estimativa de custo de heurística h que é o caso da nossa implementação, encontramos o vizinho com o menor h, ou seja o custo mais baixo.

## 2.2. Recozimento Simulado (Simulated Annealing, SA)

1 - Um nó possui um estado, um custo (número de ataques do estado desse nó) e o método sucessorAleatorio que basicamente busca um sucessor aleatório para esse estado (estado com maior e menor número de ataques), dentre outros métodos auxiliares.

2 - O Recozimento Simulado recebe um estado inicial, que é um nó matriz 8x8 com as rainhas distribuídas, o número de vezes que diminuímos a temperatura, o número de vezes que tentamos encontrar o resultado melhor do que já encontramos, a quantidade de resultados melhores (ou não) que devemos saltar e a velocidade que a temperatura irá diminuir.

O algoritmo é inspirado no processo de têmpera do aço, fazendo alusão às temperaturas, que em ambos os casos, tanto no nosso algoritmo quanto no processo de têmpera do aço são gradativamente abaixadas, até que a estrutura molecular se torne suficientemente uniforme, ou no caso do nosso algoritmo seja encontrado uma solução ótima. Implementamos de forma que fique mais adequado a problemas nos quais a subida de encosta encontra muitos máximos locais, mas não garante que a solução encontrada seja a melhor possível. Quando a temperatura decresce devagar o suficiente, a busca pode achar uma solução ótima.

O que o algoritmo de têmpera simulada faz é atribuir uma certa temperatura inicial ao processo de busca, permitindo que, além de subir encostas, o algoritmo seja capaz de descer encostas e percorrer planaltos. A temperatura é diminuída ao longo do tempo, o que faz com que o processo vá se estabilizando em algum máximo que tem maior chance de ser solução do problema.

## 2.3. Algoritmos Genéticos

1 - Um nó possui um estado, um custo (número de ataques do estado desse nó) e o método sucessorAleatorio que basicamente busca um sucessor aleatório para esse estado (estado com maior e menor número de ataques) dentre outros métodos auxiliares.

2 - Os Algoritmos Genéticos recebem um estado inicial diferente dos demais algoritmos, que é um nó matriz 8x8 com 0 rainhas distribuídas, pois geramos a população com este estado inicial. Basicamente a classe da busca recebe como parâmetro o estado inicial, o número de indivíduos e a quantidade de gerações, para assim encontrar o melhor indivíduo dentre a população gerada.

Quando falamos de Algoritmos Genéticos os aspectos diferem dos métodos tradicionais de busca, principalmente porque trabalham com uma codificação do conjunto de parâmetros e não com os próprios parâmetros, com uma população e não com um único ponto, utilizam informações de custo e por fim utilizam regras de transição probabilísticas e não determinísticas.

São muito eficientes para busca de soluções ótimas, ou aproximadamente ótimas em uma grande variedade de problemas, pois não impõem muitas das limitações encontradas nos métodos de busca tradicionais. Além de ser uma estratégia de gerar e testar muito elegante, por serem baseados na evolução biológica, são capazes de identificar e explorar fatores ambientais e convergir para soluções ótimas, ou aproximadamente ótimas em níveis globais. "Quanto melhor um indivíduo se adaptar ao seu meio ambiente, maior será sua chance de sobreviver e gerar descendentes": este é o conceito básico da evolução genética biológica. Nos referimos a “Algoritmos Genéticos” (no plural) pois são uma classe de procedimentos com muitos passos separados, e cada uma destes passos possui muitas variações possíveis.


# 3.  Experimentos


Nos experimentos, executamos os algoritmos implementados em uma máquina com as seguintes configurações:

-   Processador Intel® Core™ i5-5200U CPU @ 2.20GHz × 4.
    
-   Memória RAM DDR3 com 4GB e 1300MHZ de frequência.
    
-   Placa de Vídeo: Intel® HD Graphics 5500 (Broadwell GT2).

![](https://lh4.googleusercontent.com/QD6qGlU_JUq-bd8E8nBjJ70blvREhSlHpFMGne0YO1GvT7S2yKbkViljfGxlYk27oVZmlfNZGr5hbjB1AtEhXIRfRu1njpqYy2hgrBJN54m4JQtbns9oUSWF94le36vXcMmc7ofg)

***Figura 3. Estado Inicial***

Executamos cada algoritmo um total de dez vezes para o problema. As tabelas a seguir, mostram a eficácia dos algoritmos. Para o problema das rainhas, foi usado como estado inicial um tabuleiro igual ao da figura 3, e o objetivo é o distribuimento das 8 rainhas, obviamente, respeitando as regras.

# 4. Conclusão

Com os testes realizados e dados coletados, podemos concluir que nem todas os algoritmos obtiveram 100% de aproveitamento.

O Subida da Encosta, curiosamente, obteve a taxa de acerto 20%, a mais divergente das demais e o que ajudou nesse resultado foi seu próprio funcionamento, pois em cada passo, o nó atual é substituído pelo melhor vizinho, depois encontramos o vizinho com o menor h, ou seja o  custo mais baixo, mas se o custo do meu nó atual for menor que o meu vizinho então esse já é o melhor estado, isso faz com que ele vença no quesito tempo de execução, mas perde em precisão pros demais algoritmos, este algoritmo só faz modificações que melhoram o estado atual, o que pode acarretar em não encontrar uma solução ótima pois as vezes o meu estado atual pode ser melhor que o vizinho mas não é ótimo, o que nos leva para o próximo algoritmo.

O Recozimento Simulado, que por sua vez tem o mesmo funcionamento do Subida da Encosta, porém com algumas modificações que foram de extrema importância para a taxa de acerto atingir 100%, pois além dele pegar o melhor vizinho ele também pode fazer modificações que pioram o estado temporariamente para possivelmente melhorá-lo no futuro. Apesar de aumentar o tempo de busca, esse método consegue fugir dos máximos locais.

Os Algoritmos Genéticos também obtiveram uma taxa de acerto de 100%, provando assim sua eficiência diante os outros algoritmos, mas vale observar que isso poderia variar, dependendo da quantidade de indivíduos, das gerações, e de como é dividida a população. No caso do nosso problema atribuímos os parâmetros para que fosse atingido um resultado ótimo, esse algoritmo perdeu no quesito tempo para o Recozimento Simulado e Subida da Encosta, pois a variação da velocidade é muito maior, em compensação em algumas tentativas ele resolveu bem mais rápido que todos os tempos do Recozimento Simulado e com uma precisão bem maior que o Subida da Encosta.


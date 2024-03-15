### Memory Manager

Este é um projeto em Java que implementa um simulador de gerenciador de memória do sistema operacional. Ele inclui algoritmos de alocação de memória Best Fit, First Fit e Worst Fit para alocar processos na memória.

## Visão Geral
O gerenciador de memória é uma parte essencial do sistema operacional que controla e gerencia o uso da memória do sistema. Este projeto fornece uma implementação básica de um simulador de gerenciador de memória, permitindo a alocação e liberação de memória para processos.

## Funcionalidades
Alocação de memória para processos utilizando os algoritmos Best Fit, First Fit e Worst Fit.
Liberação de memória alocada para processos.
Visualização do estado atual da memória, incluindo espaços livres e ocupados.
Métricas para avaliar o desempenho dos diferentes algoritmos de alocação de memória.

## Algoritmos de Alocação de Memória Implementados
Best Fit:

Este algoritmo procura pelo espaço de memória mais próximo do tamanho necessário para o processo. Ele busca encontrar o menor espaço livre que ainda é suficientemente grande para alocar o processo.
First Fit:

O algoritmo First Fit aloca o primeiro espaço livre na memória que é grande o suficiente para o processo. Ele percorre a lista de espaços livres até encontrar o primeiro espaço que atenda aos requisitos do processo.
Worst Fit:

O algoritmo Worst Fit aloca o maior espaço livre disponível para o processo. Ele percorre a lista de espaços livres e seleciona o maior espaço disponível, mesmo que seja maior do que o necessário para o processo.
### Como Usar
Clone o repositório para o seu ambiente local: git clone https://github.com/kamilaqz/MemoryManager.git

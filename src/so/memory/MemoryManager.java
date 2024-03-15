package so.memory;

import so.Process;

public class MemoryManager {
	private String[] physicalMemory;
	private Strategy strategy;

	public MemoryManager(Strategy strategy) {
		this.physicalMemory = new String[128];
		this.strategy = strategy;
	}

	public void write(Process p) {
		if (this.strategy.equals(Strategy.FIRST_FIT)) {
			this.writeWhithFirstFit(p);
		} else if (this.strategy.equals(Strategy.BEST_FIT)) {
			this.writeWhithBestFit(p);
		} else if (this.strategy.equals(Strategy.WORST_FIT)) {
			this.writeWhithWorstFit(p);
		}
	}

	public void delete(Process p) {
		boolean processNotFound = true;
		for (int i = 0; i < physicalMemory.length; i++) {
			if (physicalMemory[i] != null && physicalMemory[i].equals(p.getId())) {
				processNotFound = false;
				physicalMemory[i] = null;
			}
		}
		if(processNotFound) {
			System.out.println("Processo " + p.getId() + " nao encontrado na memoria!");

		} else {
			System.out.println("Processo " + p.getId() + " removido da memoria!");
			printMemoryStatus();
			
		}
	}
	
	public void read(Process p) {
	    boolean processFound = false;
	    for (int i = 0; i < physicalMemory.length; i++) {
	        if (physicalMemory[i] != null && physicalMemory[i].equals(p.getId())) {
	            processFound = true;
	            System.out.println("Processo " + p.getId() + " encontrado na memoria!");
	            break;
	        }
	    }
	    if (!processFound) {
	        System.out.println("Processo " + p.getId() + " nao encontrado na memoria!");
	    }
	}

	private void writeWhithFirstFit(Process p) {
		System.out.println("Escrevendo o processo na memoria...");
		int actualSize = 0;
		AddressMemory bestPage = null;
		for (int i = 0; i < this.physicalMemory.length; i++) {
			if (i == (this.physicalMemory.length - 1)) {
				if (actualSize > 0) {
					int start = i - actualSize;
					int end = i;
					AddressMemory address = new AddressMemory(start, end);
					if (p.getSizeInMemory() <= address.size()) {
						bestPage = new AddressMemory(start, end);
					} else {
						actualSize = 0;
					}
					
				}
			} else if (this.physicalMemory[i] == null) {
				actualSize++;
			} else {
				if (actualSize > 0) {
					int start = i - actualSize;
					int end = i - 1;
					AddressMemory address = new AddressMemory(start, end);
					if (bestPage == null || p.getSizeInMemory() <= address.size()) {
						bestPage = new AddressMemory(start, end);
					}
					actualSize = 0;
				}
			}
		}

		if (actualSize == 0) {
			System.out.println("Nao ha espaço na memoria");
		} else {
			for (int i = bestPage.getStart(); i < (bestPage.getStart() + p.getSizeInMemory()); i++) {
				this.physicalMemory[i] = p.getId();
			}
			System.out.println("Processo inserido com sucesso!");
		}

		printMemoryStatus();
	}

	private void writeWhithBestFit(Process p) {
		/*int smallestAvailableBlockSize = Integer.MAX_VALUE;
	    int bestFitIndex = -1;

	    for (int i = 0; i < physicalMemory.length; i++) {
	        int blockSize = 0;

	        System.out.println("Procurando espaco livre");
	        if (physicalMemory[i] == null) {
	        	System.out.println("Calculando tamanho do bloco livre");
	            for (int j = i; j < physicalMemory.length; j++) {
	                if (physicalMemory[j] == null) {
	                    blockSize++;
	                } else {
	                    break;
	                }
	            }
	            
	            if (blockSize >= p.getSizeInMemory() && blockSize < smallestAvailableBlockSize) {
	                smallestAvailableBlockSize = blockSize;
	                bestFitIndex = i;
	                System.out.println("Melhor ajuste encontrado no indice: " + bestFitIndex + " com tamanho do bloco: " + blockSize);
	            } else if (blockSize == p.getSizeInMemory()) {
	                bestFitIndex = i;
	                System.out.println("Alocando no indice: " + bestFitIndex + " com tamanho do bloco exato: " + blockSize);
	                break;
	            }

	        }
	    }*/
	    System.out.println("Escrevendo processo na memoria...");
	    int bestFitIndex = -1;
	    int smallestBlockSize = Integer.MAX_VALUE;

	    for (int i = 0; i < physicalMemory.length; i++) {
	        int j = i;
	        while (j < physicalMemory.length && physicalMemory[j] == null) {
	            j++;
	        }
	        int tamanho = j - i;
	        if (tamanho >= p.getSizeInMemory() && tamanho < smallestBlockSize) {
	            bestFitIndex = i;
	            smallestBlockSize = tamanho;
	            i = j - 1;
	        }
	    }

	    if (bestFitIndex == -1) {
	        System.out.println("Nao ha espaco na memoria.");
	    } else {
	    	System.out.println("Alocando processo ao bloco encontrado");
	        for (int i = bestFitIndex; i < bestFitIndex + p.getSizeInMemory(); i++) {
	            physicalMemory[i] = p.getId();
	        }
	        System.out.println("Processo alocado com sucesso!");
	    }

	    printMemoryStatus();
	}
	
	

	private void writeWhithWorstFit(Process p) {
		System.out.println("Escrevendo processo na memoria...");
	    int largestBlockSize = 0;
	    int worstFitIndex = -1;

	    for (int i = 0; i < physicalMemory.length; i++) {
	        int blockSize = 0;

	        if (physicalMemory[i] == null) {
	            for (int j = i; j < physicalMemory.length; j++) {
	                if (physicalMemory[j] == null) {
	                    blockSize++;
	                } else {
	                    break;
	                }
	            }

	            if (blockSize > largestBlockSize) {
	                largestBlockSize = blockSize;
	                worstFitIndex = i;
	            }
	        }
	    }

	    if (worstFitIndex == -1) {
	        System.out.println("Não há espaço na memória.");
	    } else {
	        System.out.println("Bloco de pior ajuste encontrado no índice: " + worstFitIndex + " com tamanho do bloco: " + largestBlockSize);
	        for (int i = worstFitIndex; i < worstFitIndex + p.getSizeInMemory(); i++) {
	            physicalMemory[i] = p.getId();
	        }
	        System.out.println("Processo alocado com sucesso.");
	    }

	    printMemoryStatus();
	}

	private void printMemoryStatus() {
		System.out.println("-----------------GERENCIAMENTO DA MEMORIA-----------------------");
		for (int i = 0; i < physicalMemory.length; i++) {
			System.out.println("INDEX:" + i + " ID:" + physicalMemory[i] + " | ");
		}
	}
}

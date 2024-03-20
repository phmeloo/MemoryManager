package so.memory;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

import so.Process;

public class MemoryManager {
    private String[] physicMemory;
    private Hashtable<String, List<FrameMemory>> logicalMemory;
    private int pageSize;
    private Strategy strategy;
    private List<AddressMemory> blocksMemory;

    public MemoryManager(Strategy strategy, int pageSize) {
        physicMemory = new String[128];
        this.strategy = strategy;
        logicalMemory = new Hashtable<>();
        this.pageSize = pageSize;

        this.blocksMemory = new ArrayList<>();
        this.blocksMemory.add(new AddressMemory(0, 127));
    }

    public MemoryManager(Strategy strategy) {
        this(strategy, 2);
    }

    public void write(Process p) {
        if (strategy.equals(Strategy.FIRST_FIT)) {
            this.writeUsingFirstFit(p);
        } else if (strategy.equals(Strategy.BEST_FIT)) {
            this.writeUsingBestFit(p);
        } else if (strategy.equals(Strategy.WORST_FIT)) {
            this.writeUsingWorstFit(p);
        } else if (strategy.equals(Strategy.PAGING)) {
            this.writeUsingPaging(p);
        }
    }

    private void writeUsingPaging(Process p) {
        int numPages = (int) Math.ceil((double) p.getSizeInMemory() / pageSize);
        List<FrameMemory> frames = new ArrayList<>();

        for (int i = 0; i < numPages; i++) {
            int frameIndex = findFreeFrame();
            if (frameIndex != -1) {
                FrameMemory frame = new FrameMemory(frameIndex, p.getId());
                frames.add(frame);
                int start = i * pageSize;
                int end = Math.min(start + pageSize, p.getSizeInMemory());
                String pageData = p.getData().substring(start, end);
                for (int j = frameIndex * pageSize, k = 0; j < frameIndex * pageSize + pageData.length(); j++, k++) {
                    physicMemory[j] = pageData.substring(k, k + 1);
                }
            } else {
                System.out.println("Memory full: Unable to allocate page for process " + p.getId());
                return;
            }
        }

        logicalMemory.put(p.getId(), frames);
    }

    private int findFreeFrame() {
        for (int i = 0; i < physicMemory.length; i++) {
            if (physicMemory[i] == null) {
                return i;
            }
        }
        return -1; // No free frame available
    }

    public Hashtable<String, List<FrameMemory>> getLogicalMemory() {
        return logicalMemory;
    }

    public void delete(Process p) {
        // Remove o processo da memória física
        for (int i = 0; i < physicMemory.length; i++) {
            if (Objects.equals(physicMemory[i], p.getId())) {
                physicMemory[i] = null;
            }
        }
        
        System.out.println("Processo " + p.getId() + " deletado da memória.");
    }

    private void writeUsingWorstFit(Process p) {
        System.out.println("Você está usando o método WorstFit");
        int worstFitIndex = -1;
        int largestSize = Integer.MIN_VALUE;

        // Itera sobre os blocos livres para encontrar o pior ajuste
        for (int i = 0; i < physicMemory.length; i++) {
            if (physicMemory[i] == null) {
                int start = i;
                int end = i;
                while (end < physicMemory.length && physicMemory[end] == null) {
                    end++;
                }
                int blockSize = end - start;
                if (p.getSizeInMemory() <= blockSize && blockSize > largestSize) {
                    largestSize = blockSize;
                    worstFitIndex = start;
                }
                i = end;
            }
        }

        // Se foi encontrado um bloco adequado
        if (worstFitIndex != -1) {
            int start = worstFitIndex;
            int end = start + p.getSizeInMemory() - 1;
            for (int i = start; i <= end; i++) {
                physicMemory[i] = p.getId();
            }
            System.out.println("Processo " + p.getId() + " adicionado na memória.");
        } else {
            System.out.println("Espaço insuficiente na memória para alocar o processo ");
        }
    
        printMemoryStatus();
    }

    private void writeUsingBestFit(Process p) {
        System.out.println("Você está usando o método BestFit");
        int bestFitIndex = -1;
        int smallestSize = Integer.MAX_VALUE;

        // Itera sobre os blocos livres para encontrar o melhor ajuste
        for (int i = 0; i < physicMemory.length; i++) {
            if (physicMemory[i] == null) {
                int start = i;
                int end = i;
                while (end < physicMemory.length && physicMemory[end] == null) {
                    end++;
                }
                int blockSize = end - start;
                if (p.getSizeInMemory() <= blockSize && blockSize < smallestSize) {
                    smallestSize = blockSize;
                    bestFitIndex = start;
                }
                i = end;
            }
        }
        
        // Se foi encontrado um bloco adequado
        if (bestFitIndex != -1) {
            int start = bestFitIndex;
            int end = start + p.getSizeInMemory() - 1;
            for (int i = start; i <= end; i++) {
                physicMemory[i] = p.getId();
            }
            System.out.println("Processo " + p.getId() + " adicionado na memória.");
        } else {
            System.out.println("Espaço insuficiente na memória para alocar o processo " );
        }
        printMemoryStatus();
    }

    private void writeUsingFirstFit(Process p) {
        System.out.println("Você está usando o método FirstFit");
        int currentSize = 0;
        boolean allocated = false;
        // Percorre a memoria fisica para encontrar um espaco adequado para o processo
        for (int i = 0; i < physicMemory.length; i++) {
            if (physicMemory[i] == null) {
                currentSize++;
                if (currentSize == p.getSizeInMemory()) {
                    int start = i - currentSize + 1;
                    int end = i;
                    for (int j = start; j <= end; j++) {
                        physicMemory[j] = p.getId();
                    }
                    allocated = true;
                    System.out.println("Processo " + p.getId() + " adicionado na memória.");
                    break;
                }
            } else {
                currentSize = 0;
            }
        }
        // Se o processo nao pode ser alocado
        if (!allocated) {
            System.out.println("Espaço insuficiente na memória para alocar o processo ");
        }
        printMemoryStatus();
    }


    private void printMemoryStatus() {
        for (int i = 0; i < physicMemory.length; i++) {
            System.out.print(physicMemory[i] + " | ");          
        }
        System.out.println("Memory updated");
    }
}
